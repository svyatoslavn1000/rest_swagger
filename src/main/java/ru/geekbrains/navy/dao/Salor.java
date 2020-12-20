package ru.geekbrains.navy.dao;

import ru.geekbrains.navy.model.entity.Sailor;
import ru.geekbrains.navy.model.entity.Ship;
import ru.geekbrains.navy.model.enums.SailorStatusType;
import ru.geekbrains.navy.model.enums.ShipStatusType;

import java.util.List;

public interface Salor {
    List<Ship> selectAllSailors();
    List<Ship> selectSailorsByStatus(SailorStatusType status);

    int selectSailorsCountBySkipperId(long id);

    Ship selectSailorById(long id);
    void insertSailor(Sailor sailor);
    void deleteSailorById(long id);

    void updateSailorStatusById(long id, SailorStatusType status);
    void updateSailorSkipperIdById(long id, Long skipperId);
}
