package ru.geekbrains.navy.service;

import org.springframework.http.ResponseEntity;
import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.entity.ShipStatus;

import java.util.List;

public interface ShipService {

    ResponseEntity<List<Ship>> readAllShips(String status);
    ResponseEntity<String> createShip(Ship ship);
    ResponseEntity<String> deleteShip(long id);
    ResponseEntity<ShipStatus> readShipStatus(long id);
    ResponseEntity<ShipStatus> updateShipStatus(long id, Long portId, ShipStatus shipStatus);

}
