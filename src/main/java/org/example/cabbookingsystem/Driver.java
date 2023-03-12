package org.example.cabbookingsystem;

public class Driver {
    private String id;
    private String name;
    private Cab cab;
    private Location location;
    private boolean available;

    public Driver(String id, String name, Cab cab, Location location) {
        this.id = id;
        this.name = name;
        this.cab = cab;
        this.location = location;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
