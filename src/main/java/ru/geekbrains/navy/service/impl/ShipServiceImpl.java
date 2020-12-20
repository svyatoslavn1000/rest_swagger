package ru.geekbrains.navy.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.navy.dao.PortDao;
import ru.geekbrains.navy.dao.ShipDao;
import ru.geekbrains.navy.model.entity.Port;
import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.entity.ShipStatus;
import ru.geekbrains.navy.model.enums.ShipStatusType;
import ru.geekbrains.navy.service.ShipService;

import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipDao shipDao;
    private final PortDao portDao;

    @Autowired
    public ShipServiceImpl(ShipDao shipDao, PortDao portDao) {
        this.shipDao = shipDao;
        this.portDao = portDao;
    }

    @Override
    public ResponseEntity<List<Ship>> readAllShips(String status) {
        if (status == null) {
            return ResponseEntity.ok(shipDao.selectAllShips());
        }
        ShipStatusType shipStatusType = ShipStatusType.getStatusType(status);
        if (shipStatusType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(shipDao.selectShipsByStatus(shipStatusType));
    }

    @Override
    public ResponseEntity<String> createShip(Ship ship) {
        if (ship == null || ship.getName() == null || ship.getPortId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        final Port port = portDao.selectPortById(ship.getPortId());
        if (port == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        final int shipsInPortCount = shipDao.selectShipsCountByPortId(ship.getPortId());
        if (shipsInPortCount < port.getCapacity()) {
            shipDao.insertShip(ship);
            final Optional<Ship> lastInsertShip = shipDao.selectAllShips().stream().max((s1, s2) -> (int) (s1.getId() - s2.getId()));
            if (lastInsertShip.isPresent()) {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", lastInsertShip.get().getId());
                return ResponseEntity.ok(jsonObject.toString());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @Override
    public ResponseEntity<String> deleteShip(long id) {
        final Ship ship = shipDao.selectShipById(id);
        if (ship == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        shipDao.deleteShipById(id);
        return ResponseEntity.ok("");
    }

    @Override
    public ResponseEntity<ShipStatus> readShipStatus(long id) {
        final Ship ship = shipDao.selectShipById(id);
        if (ship == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        final ShipStatus shipStatus = new ShipStatus();
        shipStatus.setStatus(ship.getStatus().name());
        return ResponseEntity.ok(shipStatus);
    }

    @Override
    public ResponseEntity<ShipStatus> updateShipStatus(long id, Long portId, ShipStatus shipStatus) {
        final Ship ship = shipDao.selectShipById(id);
        if (ship == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ShipStatusType shipStatusType = ShipStatusType.getStatusType(shipStatus.getStatus());
        if (shipStatusType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        switch (shipStatusType) {
            case SEA:
                if (ship.getStatus() != ShipStatusType.SEA) {
                    ship.setStatus(ShipStatusType.SEA);
                    shipDao.updateShipStatusById(id, shipStatusType);
                    shipDao.updateShipPortIdById(id, null);
                }
                break;
            case PORT:
                if (ship.getStatus() != ShipStatusType.PORT) {
                    if (portId == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    }
                    final Port port = portDao.selectPortById(portId);
                    if (port == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                    }
                    final int shipsInPortCount = shipDao.selectShipsCountByPortId(portId);
                    if (shipsInPortCount < port.getCapacity()) {
                        ship.setStatus(ShipStatusType.PORT);
                        shipDao.updateShipStatusById(id, shipStatusType);
                        shipDao.updateShipPortIdById(id, portId);
                    } else {
                        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
                    }
                }
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ShipStatus newShipStatus = new ShipStatus();
        newShipStatus.setStatus(ship.getStatus().name());
        return ResponseEntity.ok(newShipStatus);
    }
}
