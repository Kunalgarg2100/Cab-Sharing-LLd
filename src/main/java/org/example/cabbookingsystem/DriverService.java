package org.example.cabbookingsystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DriverService {
    private Map<String, Driver> drivers = new ConcurrentHashMap<>();

    private static DriverService instance;

    private DriverService() {}

    public static synchronized DriverService getInstance() {
        if (instance == null) {
            synchronized (DriverService.class) {
                if (instance == null) {
                    instance = new DriverService();
                }
            }
        }
        return instance;
    }

    public Driver registerDriver(String id, String name, Cab cab, Location location) {
        Driver driver = new Driver(id, name, cab, location);
        drivers.put(id, driver);
        return driver;
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.values().stream()
                .filter(Driver::isAvailable)
                .collect(Collectors.toList());
    }

}

