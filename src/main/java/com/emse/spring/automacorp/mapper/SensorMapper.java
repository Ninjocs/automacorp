package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.record.Sensor;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;

public class SensorMapper {

    public static Sensor toSensor(SensorEntity sensorEntity) {
        if (sensorEntity == null) {
            return null;
        }

        return new Sensor(
                sensorEntity.getId(),
                sensorEntity.getName(),
                sensorEntity.getValue(),
                sensorEntity.getSensorType() != null ? sensorEntity.getSensorType().name() : null
        );
    }

    public static SensorEntity toSensorEntity(Sensor sensorDTO) {
        if (sensorDTO == null) {
            return null;
        }

        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(sensorDTO.id());
        sensorEntity.setName(sensorDTO.name());
        sensorEntity.setValue(sensorDTO.value());

        if (sensorDTO.sensorType() != null) {
            sensorEntity.setSensorType(SensorType.valueOf(sensorDTO.sensorType()));
        }

        return sensorEntity;
    }
}

