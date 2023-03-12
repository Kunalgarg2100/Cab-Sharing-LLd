package org.example.cabbookingsystem;

import java.util.Date;
import java.util.UUID;

public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private Cab cab;
    private Location pickupLocation;
    private Location dropLocation;
    private Date startTime;
    private Date endTime;
    private double fare;

    public Ride(Rider rider, Driver driver, Cab cab, Location pickupLocation, Location dropLocation, Date startTime) {
        this.rideId = UUID.randomUUID().toString();
        this.rider = rider;
        this.driver = driver;
        this.cab = cab;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.startTime = startTime;
    }

    public String getRideId() {
        return rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Ride{" +
//                "rideId='" + rideId + '\'' +
//                ", driver=" + driver +
                ", cab=" + cab +
//                ", pickupLocation=" + pickupLocation +
//                ", dropLocation=" + dropLocation +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", fare=" + fare +
                '}';
    }
}