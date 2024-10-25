package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity // .
@Table(name = "SP_SENSOR") // .
public class SensorEntity {
    @Id // .
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)  // .
    private String name;

    @Column(name = "sensor_value") //
    private Double value;

    @Column(name = "sensor_type") // .
    @Enumerated(EnumType.STRING) //
    // .
    private SensorType sensorType;

    public SensorEntity() { // .
    }

    public SensorEntity(SensorType sensorType, String name) { // .
        this.name = name;
        this.sensorType = sensorType;
    }

    public SensorEntity(SensorType sensorType, String name, double value) { // .
        this.name = name;
        this.sensorType = sensorType;
        this.value=value;
    }

    public Long getId() { // .
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    // Setter method to set the sensorType using a string
    public void setType(String type) {
        try {
            // Convert the string to the enum type
            this.sensorType = SensorType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided string is not a valid enum constant
            System.out.println("Invalid sensor type: " + type);
        }
    }

    // Setter method using the enum directly (optional)
    public void setType(SensorType sensorType) {
        this.sensorType = sensorType;
    }


    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }
}
