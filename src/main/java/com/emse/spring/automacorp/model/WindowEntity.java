package com.emse.spring.automacorp.model;

import jakarta.persistence.*; // or use javax.persistence.* depending on your JPA setup
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SP_WINDOW")
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @OneToOne
    @JoinColumn(name = "window_status_id")
    private SensorEntity windowStatus;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    public WindowEntity() {
    }

    public WindowEntity(String name, SensorEntity sensor, RoomEntity room) { // Updated constructor
        this.name = name;
        this.windowStatus = sensor;
        this.room = room;
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

    public @NotNull SensorEntity getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(SensorEntity windowStatus) {
        this.windowStatus = windowStatus;
    }

    public void setWindowStatus(double valeur) {
        this.windowStatus = new SensorEntity();
        this.windowStatus.setValue(valeur);
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
