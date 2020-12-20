package ru.geekbrains.navy.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.navy.model.entity.Port;
import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.enums.ShipStatusType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipMapper implements RowMapper<Ship> {
    @Override
    public Ship mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Ship ship = new Ship();
        ship.setId(rs.getLong("id"));
        ship.setName(rs.getString("name"));
        ship.setStatus(ShipStatusType.getStatusType(rs.getString("status")));
        final Long portId = rs.getLong("port_id");
        if (!rs.wasNull()) {
            ship.setPortId(portId);
        }
        return ship;
    }
}
