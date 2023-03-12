package org.example.cabbookingsystem;

public class Cab {
    private String id;
    private String model;
    private String color;

    public Cab(String id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
