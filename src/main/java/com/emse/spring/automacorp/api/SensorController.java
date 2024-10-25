package com.emse.spring.automacorp.api;


import com.emse.spring.automacorp.model.SensorEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Sensor Controller", description = "APIs for managing sensors")
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private List<SensorEntity> sensorList = new ArrayList<>();

    @Operation(summary = "Retrieve a list of sensors", description = "Fetch all sensors in the system")
    @GetMapping
    public List<SensorEntity> getSensors() {
        return sensorList;
    }

    @Operation(summary = "Retrieve a sensor by ID", description = "Find a sensor using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<SensorEntity> getSensorById(@PathVariable Long id) {
        Optional<SensorEntity> sensor = sensorList.stream().filter(s -> s.getId().equals(id)).findFirst();
        return sensor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create a new sensor", description = "Create a new sensor and add it to the system")
    @PostMapping
    public ResponseEntity<SensorEntity> createSensor(@RequestBody SensorEntity sensorEntity) {
        sensorEntity.setId((long) (sensorList.size() + 1));
        sensorList.add(sensorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensorEntity);
    }

    @Operation(summary = "Update an existing sensor", description = "Update the details of an existing sensor")
    @PutMapping("/{id}")
    public ResponseEntity<SensorEntity> updateSensor(@PathVariable Long id, @RequestBody SensorEntity updatedSensor) {
        Optional<SensorEntity> existingSensor = sensorList.stream().filter(s -> s.getId().equals(id)).findFirst();
        if (existingSensor.isPresent()) {
            SensorEntity sensor = existingSensor.get();
            sensor.setName(updatedSensor.getName());
            sensor.setType(updatedSensor.getSensorType());
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Delete a sensor by ID", description = "Delete a sensor by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        Optional<SensorEntity> sensor = sensorList.stream().filter(s -> s.getId().equals(id)).findFirst();
        if (sensor.isPresent()) {
            sensorList.remove(sensor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}