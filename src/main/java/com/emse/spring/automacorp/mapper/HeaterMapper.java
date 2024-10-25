package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.record.Heater;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;

public class HeaterMapper {

    public static Heater toRecord(HeaterEntity heaterEntity) {
        if (heaterEntity == null) {
            return null;
        }

        return new Heater(
                heaterEntity.getId(),
                heaterEntity.getName(),
                heaterEntity.getRoom().getId(),
                heaterEntity.getSensor().getId()
        );
    }

    public static HeaterEntity toEntity(Heater heaterRecord, RoomEntity room, SensorEntity sensor) {
        if (heaterRecord == null) {
            return null;
        }

        HeaterEntity entity = new HeaterEntity();
        entity.setId(heaterRecord.id());
        entity.setName(heaterRecord.name());
        entity.setRoom(room);
        entity.setSensor(sensor);

        return entity;
    }
}
