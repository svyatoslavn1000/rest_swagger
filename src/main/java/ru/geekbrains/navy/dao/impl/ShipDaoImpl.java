package ru.geekbrains.navy.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.geekbrains.navy.dao.BaseDao;
import ru.geekbrains.navy.dao.ShipDao;
import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.enums.ShipStatusType;
import ru.geekbrains.navy.model.mapper.ShipMapper;

import java.util.Collections;
import java.util.List;

@Repository
public class ShipDaoImpl extends BaseDao implements ShipDao {

    @Override
    public List<Ship> selectAllShips() {
        final String sql = "SELECT id, name, port_id, status FROM ships";
        return jdbcTemplate.query(sql, new ShipMapper());
    }

    @Override
    public List<Ship> selectShipsByStatus(ShipStatusType status) {
        if (status == null) {
            return Collections.emptyList();
        }
        final String sql = "SELECT id, name, port_id, status FROM ships WHERE status='" + status.name() + "'";
        return jdbcTemplate.query(sql, new ShipMapper());
    }

    @Override
    public int selectShipsCountByPortId(long id) {
        final String sql = String.format("SELECT COUNT(id) FROM ships WHERE port_id=%s AND status='%s'", id, ShipStatusType.PORT.name());
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Ship selectShipById(long id) {
        final String sql = "SELECT id, name, port_id, status FROM ships WHERE id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql, new ShipMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void insertShip(Ship ship) {
        if (ship == null || ship.getName() == null || ship.getPortId() == null) {
            return;
        }
        final String sql = String.format("INSERT INTO ships (name, port_id, status) VALUES ('%s', %s, '%s')",
                ship.getName(), ship.getPortId(), ShipStatusType.PORT.name());
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateShipStatusById(long id, ShipStatusType status) {
        if (status == null) {
            return;
        }
        final String sql = String.format("UPDATE ships SET status='%s' WHERE id=%s", status.name(), id);
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateShipPortIdById(long id, Long portId) {
        final String sql = String.format("UPDATE ships SET port_id=%s WHERE id=%s", portId, id);
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteShipById(long id) {
        final String sql = "DELETE FROM ships WHERE id=" + id;
        jdbcTemplate.update(sql);
    }
}
