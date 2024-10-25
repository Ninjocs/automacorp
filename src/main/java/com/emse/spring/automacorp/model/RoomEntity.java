package com.emse.spring.automacorp.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SP_ROOM")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Non-nullable floor
    private Long floor;

    @NotNull // Non-nullable name
    private String name;

    @Column(name = "current_temperature_id")
    private Long currentTemperatureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;  // Room belongs to a building

    @OneToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity currentTemperature;

    private Double targetTemperature;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<WindowEntity> windows;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HeaterEntity> heaters = new ArrayList<>();

    public RoomEntity() {}

    public RoomEntity(Long floor, String name, SensorEntity currentTemperature, Double targetTemperature, List<WindowEntity> windows, List<HeaterEntity> heaters, BuildingEntity building) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.windows = windows;
        this.building = building;

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
    }

    public RoomEntity(String name, SensorEntity currentTemperature, double temperature) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.setTargetTemperature(temperature);

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public void setHeaters(List<HeaterEntity> heaters) {
        this.heaters = heaters;
    }

    public Long getCurrentTemperatureId() {
        return currentTemperatureId;
    }

    public void setCurrentTemperatureId(Long currentTemperatureId) {
        this.currentTemperatureId = currentTemperatureId;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
    }
}

