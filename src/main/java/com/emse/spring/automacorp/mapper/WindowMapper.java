package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.record.Window;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.record.Sensor;



public class WindowMapper {

    public static Window toWindowDTO(WindowEntity windowEntity) {
        if (windowEntity == null) {
            return null;
        }

        return new Window(
                windowEntity.getId(),
                windowEntity.getName(),
                windowEntity.getWindowStatus() != null ? windowEntity.getWindowStatus() : null,
                windowEntity.getRoom() != null ? windowEntity.getRoom().getId() : null
        );
    }

    public static WindowEntity toWindowEntity(Window windowDTO) {
        if (windowDTO == null) {
            return null;
        }

        WindowEntity windowEntity = new WindowEntity();
        windowEntity.setId(windowDTO.id());
        windowEntity.setName(windowDTO.name());
        windowEntity.setWindowStatus(windowDTO.windowStatus());


        return windowEntity;
    }
}
