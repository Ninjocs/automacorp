package com.emse.spring.automacorp.utils;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.BuildingEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import java.util.List;

import static com.emse.spring.automacorp.model.SensorType.TEMPERATURE;


public class FakeEntityBuilder {

    public static RoomEntity createRoomEntity(Long id, String name, BuildingEntity building) {
        RoomEntity entity = new RoomEntity(
                name,
                createSensorEntity(1L, "Temp", TEMPERATURE, 23.2),
                1);

        entity.setBuilding(building);
        entity.setTargetTemperature(26.4);
        entity.setId(id);
        entity.setWindows(List.of(
                createWindowEntity(id * 10 + 1L, "Window1" + name, entity),
                createWindowEntity(id * 10 + 2L, "Window2" + name, entity)
        ));
        entity.setHeaters(List.of(
                createHeaterEntity(id * 10 + 1L, "Heater1" + name, entity),
                createHeaterEntity(id * 10 + 2L, "Heater2" + name, entity)
        ));
        return entity;
    }

    public static WindowEntity createWindowEntity(Long id, String name, RoomEntity roomEntity) {
        WindowEntity windowEntity = new WindowEntity(
                name,
                createSensorEntity(id * 10 + 1L, "Status" + id, SensorType.STATUS, 0.0),
                roomEntity
        );
        windowEntity.setId(id);
        return windowEntity;
    }

    public static HeaterEntity createHeaterEntity(Long id, String name, RoomEntity roomEntity) {
        HeaterEntity heaterEntity = new HeaterEntity(
                name,
                roomEntity,
                createSensorEntity(id * 10 + 1L, "Status" + id, SensorType.STATUS, 0.0)

        );
        heaterEntity.setId(id);
        return heaterEntity;
    }

    public static SensorEntity createSensorEntity(Long id, String name, SensorType type, Double value) {
        SensorEntity sensorEntity = new SensorEntity(type, name);
        sensorEntity.setId(id);
        sensorEntity.setValue(value);
        return sensorEntity;
    }
}
