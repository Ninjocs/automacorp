package com.emse.spring.automacorp.record;

public record Heater(
        Long id,
        String name,
        Long roomId,
        Long sensorId
) {
}
