package com.emse.spring.automacorp.record;


import com.emse.spring.automacorp.model.SensorEntity;

public record Window(Long id, String name, SensorEntity windowStatus, Long roomId) {
}
