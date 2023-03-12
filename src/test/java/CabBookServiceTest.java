import org.example.CabBookService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CabBookServiceTest {

        private static final int NUM_CARS = 3;
        private static final int NUM_RIDERS = 10;

        @Test
        public void testSingleRider() throws InterruptedException {
            CabBookService rideService = new CabBookService(NUM_CARS, 1);
            rideService.startRides();
            Thread riderThread = new Thread(() -> rideService.requestRide(rideService.riders.get(0)));
            riderThread.start();
            riderThread.join();
        }

        @Test
        public void testMultipleRiders() throws InterruptedException {
            CabBookService rideService = new CabBookService(NUM_CARS, NUM_RIDERS);
            rideService.startRides();
            List<Thread> riderThreads = new ArrayList<>();
            for (CabBookService.Rider rider : rideService.riders) {
                Thread riderThread = new Thread(() -> rideService.requestRide(rider));
                riderThread.start();
                riderThreads.add(riderThread);
            }
            for (Thread riderThread : riderThreads) {
                riderThread.join();
            }
        }

        @Test
        public void testMoreRidersThanCars() throws InterruptedException {
            CabBookService rideService = new CabBookService(NUM_CARS, NUM_RIDERS);
            rideService.startRides();
            List<Thread> riderThreads = new ArrayList<>();
            for (CabBookService.Rider rider : rideService.riders) {
                Thread riderThread = new Thread(() -> rideService.requestRide(rider));
                riderThread.start();
                riderThreads.add(riderThread);
            }
            for (Thread riderThread : riderThreads) {
                riderThread.join();
            }
        }

        @Test
        public void testMoreCarsThanRiders() throws InterruptedException {
            CabBookService rideService = new CabBookService(NUM_RIDERS, NUM_CARS);
            rideService.startRides();
            List<Thread> riderThreads = new ArrayList<>();
            for (CabBookService.Rider rider : rideService.riders) {
                Thread riderThread = new Thread(() -> rideService.requestRide(rider));
                riderThread.start();
                riderThreads.add(riderThread);
            }
            for (Thread riderThread : riderThreads) {
                riderThread.join();
            }
        }
    }

