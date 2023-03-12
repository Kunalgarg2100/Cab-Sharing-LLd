package org.example.cabbookingsystem;

import java.util.ArrayList;
import java.util.List;

public class Rider {
    private String id;
    private String name;
    private Location location;
    private List<Ride> rides;

    public Rider(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rides = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
//                ", location=" + location +
                ", rides=" + rides +
                '}';
    }
}