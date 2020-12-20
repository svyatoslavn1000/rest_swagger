package ru.geekbrains.navy.dao;

import ru.geekbrains.navy.model.entity.Port;

import java.util.List;

public interface PortDao {

    List<Port> selectAllPorts();
    Port selectPortById(long id);
}
