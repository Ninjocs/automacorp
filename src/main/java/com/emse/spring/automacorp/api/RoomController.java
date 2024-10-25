package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Room Controller", description = "APIs for managing rooms")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private List<RoomEntity> roomList = new ArrayList<>();

    @Operation(summary = "Retrieve a list of rooms", description = "Fetch all rooms in the system")
    @GetMapping
    public List<RoomEntity> getRooms() {
        return roomList;
    }

    @Operation(summary = "Retrieve a room by ID", description = "Find a room using its ID")
    @GetMapping("/{room_id}")
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long room_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(room_id)).findFirst();
        return room.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create or update a room", description = "Create or update a room and add it to the system")
    @PostMapping
    public ResponseEntity<RoomEntity> createOrUpdateRoom(@RequestBody RoomEntity roomEntity) {
        roomEntity.setId((long) (roomList.size() + 1));
        roomList.add(roomEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomEntity);
    }

    @Operation(summary = "Delete a room by ID", description = "Delete a room and all associated windows and heaters")
    @DeleteMapping("/{room_id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long room_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(room_id)).findFirst();
        if (room.isPresent()) {
            roomList.remove(room.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Open all windows in a room", description = "Switch all windows in the room to OPEN")
    @PutMapping("/{room_id}/openWindows")
    public ResponseEntity<Void> openWindows(@PathVariable Long room_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(room_id)).findFirst();
        if (room.isPresent()) {
            RoomEntity existingRoom = room.get();
            for (WindowEntity window : existingRoom.getWindows()) {
                window.setWindowStatus(1.);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Close all windows in a room", description = "Switch all windows in the room to CLOSED")
    @PutMapping("/{room_id}/closeWindows")
    public ResponseEntity<Void> closeWindows(@PathVariable Long room_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(room_id)).findFirst();
        if (room.isPresent()) {
            RoomEntity existingRoom = room.get();
            for (WindowEntity window : existingRoom.getWindows()) {
                window.setWindowStatus(0.);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
