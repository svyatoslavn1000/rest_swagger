package ru.geekbrains.navy.service;

import org.springframework.http.ResponseEntity;
import ru.geekbrains.navy.model.entity.Port;

import java.util.List;

public interface PortService {

    ResponseEntity<List<Port>> readAllPorts();
    ResponseEntity<String> readPortCapacityInfo(long id);
}
