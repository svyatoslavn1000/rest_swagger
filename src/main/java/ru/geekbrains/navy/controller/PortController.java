package ru.geekbrains.navy.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.navy.model.entity.Port;
import ru.geekbrains.navy.service.PortService;

import java.util.List;

@RestController
@RequestMapping(value = "/ports", produces = "application/json")
@Api(value = "/ports", tags = {"Порты"})
public class PortController {

    private PortService portService;

    @Autowired
    public PortController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping
    @ApiOperation(
            value = "Получить сведения о портах",
            httpMethod = "GET",
            produces = "application/json",
            response = Port.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")})
    public ResponseEntity<List<Port>> getAllPorts() {
        return portService.readAllPorts();
    }

    @GetMapping("/{id}/capacity")
    @ApiOperation(
            value = "Получить текущую загрузку порта",
            httpMethod = "GET",
            produces = "application/json",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Порт не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")})
    public ResponseEntity<String> getPortCapacityInfo(
            @ApiParam(
                    value = "id порта",
                    name = "id",
                    required = true,
                    example = "3"
            )
            @PathVariable(value = "id") final long id
    ) {
        return portService.readPortCapacityInfo(id);
    }
}
