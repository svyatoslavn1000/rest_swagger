package ru.geekbrains.navy.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.navy.model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PortMapper implements RowMapper<Port> {
    @Override
    public Port mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Port port = new Port();
        port.setId(rs.getLong("id"));
        port.setCapacity(rs.getInt("capacity"));
        port.setName(rs.getString("name"));
        return port;
    }
}
