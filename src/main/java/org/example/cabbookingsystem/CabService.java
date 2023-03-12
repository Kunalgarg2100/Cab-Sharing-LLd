package org.example.cabbookingsystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CabService {
    private static CabService instance;
    private Map<String, Cab> cabs = new ConcurrentHashMap<>();

    public Cab registerCab(String id, String model, String color) {
        Cab cab = new Cab(id, model, color);
        cabs.put(id, cab);
        return cab;
    }

    public static synchronized CabService getInstance() {
        if (instance == null) {
            instance = new CabService();
        }
        return instance;
    }

}
