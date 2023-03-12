package org.example.cabbookingsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiderService {
    private Map<String, Rider> riders = new ConcurrentHashMap<>();
    public static RiderService instance;

    public static synchronized RiderService getInstance() {
        if (instance == null) {
            instance = new RiderService();
        }
        return instance;
    }

    public Map<String, Rider> getRiders() {
        return new HashMap<String, Rider>(riders);
    }

    public Rider registerRider(String id, String name, Location location) {
        Rider rider = new Rider(id, name, location);
        riders.put(id, rider);
        return rider;
    }

    public List<Ride> getRideHistory(String riderId) {
        if (!riders.containsKey(riderId)) {
            throw new RuntimeException("Rider with id " + riderId + " does not exist");
        }
        Rider rider = riders.get(riderId);
        return rider.getRides();
    }

    public void bookCab(String riderId, Location pickupLocation, Location dropLocation) throws InterruptedException {
        if (!riders.containsKey(riderId)) {
            throw new RuntimeException("Rider with id " + riderId + " does not exist");
        }
        Rider rider = riders.get(riderId);
        RideService.getInstance().createRide(rider, pickupLocation, dropLocation);
    }

    public void endRide(String rideId) {
        RideService.getInstance().endRide(rideId);
    }
}
