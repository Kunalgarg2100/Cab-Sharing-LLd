package org.example;

import org.example.cabbookingsystem.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RiderService riderService = RiderService.getInstance();
        List<Rider> riders = new ArrayList<>();
        for (int i = 1; i <=3; i++) {
            final int riderNumber = i;
            Rider r = riderService.registerRider("r"+riderNumber, "Rider "+ riderNumber,
                                                new Location(Math.random() * 100, Math.random() * 100));
            riders.add(r);
        };

        CabService cabService = CabService.getInstance();
        Cab c1 = cabService.registerCab("c1","M1", "Blue");
        Cab c2 = cabService.registerCab("c2","M2", "Red");

        DriverService driverService = DriverService.getInstance();
        Driver d1 = driverService.registerDriver("d1", "Driver1", c1, new Location(Math.random() * 100, Math.random() * 100) );
        Driver d2 = driverService.registerDriver("d2", "Driver1", c2, new Location(Math.random() * 100, Math.random() * 100) );

        /*Thread thread1 = new Thread(() -> {
            riderService.bookCab("r1", new Location(Math.random() * 100, Math.random() * 100), new Location(Math.random() * 100, Math.random() * 100) );
        });

        Thread thread2 = new Thread(() -> {
            riderService.bookCab("r2", new Location(Math.random() * 100, Math.random() * 100), new Location(Math.random() * 100, Math.random() * 100) );
        });

        Thread thread3 = new Thread(() -> {
            riderService.bookCab("r3", new Location(Math.random() * 100, Math.random() * 100), new Location(Math.random() * 100, Math.random() * 100) );
        });*/

        // register 50 riders
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <=3; i++) {
            final int riderNumber = i;
            threads.add(
                    new Thread(() -> {
                        try {
                            riderService.bookCab("r" + riderNumber,
                                    new Location(Math.random() * 100, Math.random() * 100),
                                    new Location(Math.random() * 100, Math.random() * 100) );
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    })
            );
        };

        threads.forEach(Thread::start);
        System.out.println("After ride has been ended");
        System.out.println(riderService.getRiders());
        threads.get(0).sleep(3000);
        riderService.endRide(riders.get(0).getRides().get(0).getRideId());
        threads.get(1).sleep(10000);


        System.out.println("After ride has been ended");
        System.out.println(riderService.getRiders());
//
//        riderService.bookCab(,
//                        new Location(Math.random() * 100, Math.random() * 100),
//                        new Location(Math.random() * 100, Math.random() * 100) );


        System.out.println("After ride and been rebooked");
        System.out.println(riderService.getRiders());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        // book 50 rides
//        for (int i = 0; i < 50; i++) {
//            final int riderNumber = i;
//            executorService.submit(() -> {
//                Rider rider = riderService.getRider("rider" + riderNumber);
//                Location pickupLocation = new Location(Math.random() * 100, Math.random() * 100);
//                Location dropLocation = new Location(Math.random() * 100, Math.random() * 100);
//                Ride ride = cabService.bookRide(rider, pickupLocation, dropLocation, new Date());
//            });
//        }
//
//        // get ride history for each rider
//        for (int i = 0; i < 50; i++) {
//            final int riderNumber = i;
//            executorService.submit(() -> {
//                Rider rider = riderService.getRider("rider" + riderNumber);
//                riderService.getRideHistory(rider);
//            });
//        }
//
//        // shut down the thread pool
//        executorService.shutdown();
    }
}