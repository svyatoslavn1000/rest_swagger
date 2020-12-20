package ru.geekbrains.navy.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.navy.model.entity.Sailor;
import ru.geekbrains.navy.model.enums.SailorStatusType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SailorMapper implements RowMapper<Sailor> {
    @Override
    public Sailor mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Sailor sailor = new Sailor();
        sailor.setId(rs.getLong("id"));
        sailor.setName(rs.getString("name"));
        sailor.setStatus(SailorStatusType.getStatusType(rs.getString("status")));
        final Long portId = rs.getLong("skipper_id");
        if (!rs.wasNull()) {
            sailor.setSkipperId(portId);
        }
        return sailor;
    }
}
