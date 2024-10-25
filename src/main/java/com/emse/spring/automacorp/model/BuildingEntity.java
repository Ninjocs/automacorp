package com.emse.spring.automacorp.model;


import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SP_BUILDING")
public class BuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outside_temp_sensor_id", nullable = false)
    private SensorEntity outsideTemperatureSensor;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomEntity> rooms = new HashSet<>();

    public BuildingEntity() {
    }

    public BuildingEntity(@NotNull String name, SensorEntity outsideTemperatureSensor) {
        this.name = name;
        this.outsideTemperatureSensor = outsideTemperatureSensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getOutsideTemperatureSensor() {
        return outsideTemperatureSensor;
    }

    public void setOutsideTemperatureSensor(SensorEntity outsideTemperatureSensor) {
        this.outsideTemperatureSensor = outsideTemperatureSensor;
    }

    public Set<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(RoomEntity room) {
        rooms.add(room);
        room.setBuilding(this);
    }

    public void removeRoom(RoomEntity room) {
        rooms.remove(room);
        room.setBuilding(null);
    }
}

