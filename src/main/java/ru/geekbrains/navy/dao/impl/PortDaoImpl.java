package ru.geekbrains.navy.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import ru.geekbrains.navy.dao.BaseDao;
import ru.geekbrains.navy.dao.PortDao;
import ru.geekbrains.navy.model.entity.Port;
import ru.geekbrains.navy.model.enums.ShipStatusType;
import ru.geekbrains.navy.model.mapper.PortMapper;

import java.util.List;

@Repository
public class PortDaoImpl extends BaseDao implements PortDao {

    @Override
    public List<Port> selectAllPorts() {
        final String sql = "SELECT id, name, capacity FROM ports";
        return jdbcTemplate.query(sql, new PortMapper());
    }

    @Override
    public Port selectPortById(long id) {
        final String sql = "SELECT id, name, capacity FROM ports WHERE id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql, new PortMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }
}
