package jcrud_serialization;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String licensePlate;
    private final String model;
    private final String brand;
    private final float speed;

    public Car(String licensePlate, String model, String brand, float speed) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.speed = speed;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public float getSpeed() {
        return speed;
    }

}
