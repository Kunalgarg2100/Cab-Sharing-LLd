package org.example.cabbookingsystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RideService {
    private Map<String, Ride> rides = new ConcurrentHashMap<>();
    private int totalCabs = 5;
    private Object cabLock = new Object();
    public static RideService instance;

    public static synchronized RideService getInstance() {
        if (instance == null) {
            instance = new RideService();
        }
        return instance;
    }

    public Ride getRide(String rideId){
        return rides.get(rideId);
    }
    public synchronized void createRide(Rider rider, Location pickupLocation, Location dropLocation) throws InterruptedException {
        synchronized (cabLock) {
            while (totalCabs <= 0) {
                System.out.println(rider + " is waiting for a cab.");
                cabLock.wait();
            }
            Driver driver = findNearestDriver(pickupLocation);
            if (driver != null) {
                Cab cab = driver.getCab();
                Ride ride = new Ride(rider, driver, cab, pickupLocation, dropLocation, new Date());
                rides.put(ride.getRideId(), ride);
                driver.setAvailable(false);
                rider.addRide(ride);
                System.out.println(rider + " booked " + cab);
            } else {
                throw new RuntimeException("Cabs not available");
            }
            totalCabs--;
        }
    }

    public void endRide(String rideId) {
        Ride ride = rides.get(rideId);
        double distance = ride.getPickupLocation().distanceTo(ride.getDropLocation());
        double fare = distance * 10;
        ride.setEndTime(new Date());
        ride.setFare(fare);
        ride.getDriver().setAvailable(true);
        ride.getDriver().setLocation(ride.getDropLocation());
        synchronized (cabLock) {
            totalCabs++;
            cabLock.notifyAll();
        }
    }

    private synchronized Driver findNearestDriver(Location pickupLocation) {
        List<Driver> availableDrivers = DriverService.getInstance().getAvailableDrivers();
        Driver nearestDriver = null;
        double shortestDistance = Double.MAX_VALUE;
        for (Driver driver : availableDrivers) {
            double distance = driver.getLocation().distanceTo(pickupLocation);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestDriver = driver;
            }
        }
        return nearestDriver;
    }
}
