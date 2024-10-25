package com.emse.spring.automacorp.record;

import com.emse.spring.automacorp.model.BuildingEntity;

import java.util.List;

public record Room(
        Long id,
        Long floor,
        String name,
        Double targetTemperature,
        Long currentTemperatureId,
        Long buildingId,
        List<Long> windowIds
) {}