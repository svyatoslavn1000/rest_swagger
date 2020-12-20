package ru.geekbrains.navy.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.navy.dao.PortDao;
import ru.geekbrains.navy.dao.ShipDao;
import ru.geekbrains.navy.model.entity.Port;
import ru.geekbrains.navy.service.PortService;

import java.util.List;


@Service
public class PortServiceImpl implements PortService {

    private final PortDao portDao;
    private final ShipDao shipDao;

    @Autowired
    public PortServiceImpl(PortDao portDao, ShipDao shipDao) {
        this.portDao = portDao;
        this.shipDao = shipDao;
    }

    @Override
    public ResponseEntity<List<Port>> readAllPorts() {
        return ResponseEntity.ok(portDao.selectAllPorts());
    }

    @Override
    public ResponseEntity<String> readPortCapacityInfo(long id) {
        final Port port = portDao.selectPortById(id);
        if (port == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        final int shipsInPortCount = shipDao.selectShipsCountByPortId(id);
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", port.getCapacity());
        jsonObject.put("used", shipsInPortCount);
        jsonObject.put("free", port.getCapacity() - shipsInPortCount);
        return ResponseEntity.ok(jsonObject.toString());
    }
}
