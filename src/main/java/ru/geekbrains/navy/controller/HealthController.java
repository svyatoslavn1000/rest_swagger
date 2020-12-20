package ru.geekbrains.navy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/health", tags = {"Проверка состояния сервиса"})
public class HealthController {

    @GetMapping("/health")
    @ApiOperation(
            value = "Полученить состояние сервиса",
            httpMethod = "GET",
            produces = "application/json",
            response = HealthResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")})
    public HealthResponse getHealth() {
        final HealthResponse healthResponse = new HealthResponse();
        healthResponse.setStarted(true);
        return healthResponse;
    }

    private class HealthResponse {
        private boolean started;

        public void setStarted(boolean started) {
            this.started = started;
        }

        public boolean isStarted() {
            return started;
        }
    }
}
