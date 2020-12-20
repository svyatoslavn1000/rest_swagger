package ru.geekbrains.navy.dao;

import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.enums.ShipStatusType;

import java.util.List;

public interface ShipDao {

    List<Ship> selectAllShips();
    List<Ship> selectShipsByStatus(ShipStatusType status);

    int selectShipsCountByPortId(long id);

    Ship selectShipById(long id);
    void insertShip(Ship ship);
    void deleteShipById(long id);

    void updateShipStatusById(long id, ShipStatusType status);
    void updateShipPortIdById(long id, Long portId);
}
