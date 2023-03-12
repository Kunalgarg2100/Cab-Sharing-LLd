package org.example;


import java.util.ArrayList;
import java.util.List;

public class CabBookService {
    private int numCars;
    private int numRiders;
    public List<Rider> riders;
    private List<Car> cars;

    public CabBookService(int numCars, int numRiders) {
        this.numCars = numCars;
        this.numRiders = numRiders;
        riders = new ArrayList<>(numRiders);
        cars = new ArrayList<>(numCars);
        for (int i = 0; i < numCars; i++) {
            cars.add(new Car(i));
        }
        for (int i = 0; i < numRiders; i++) {
            riders.add(new Rider(i));
        }
    }

    public void startRides() {
        for (Rider rider : riders) {
            new Thread(rider).start();
        }
    }

    public class Rider implements Runnable {
        private int id;

        public Rider(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                synchronized (this) {
                    try {
                        System.out.println("Rider " + id + " is waiting for a car");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Rider " + id + " has boarded a car");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Rider " + id + " has been dropped off by a car");
            }
        }

        public synchronized void notifyCar() {
            notifyAll();
        }
    }

    private class Car implements Runnable {
        private int id;
        private Rider rider;

        public Car(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                synchronized (this) {
                    try {
                        System.out.println("Car " + id + " is waiting for a rider");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Car " + id + " is carrying rider " + rider.id);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rider.notifyCar();
                System.out.println("Car " + id + " has dropped off rider " + rider.id);
                rider = null;
                synchronized (cars) {
                    cars.add(this);
                }
            }
        }

        public synchronized void assignRider(Rider rider) {
            this.rider = rider;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public synchronized void requestRide(Rider rider) {
        while (true) {
            synchronized (cars) {
                if (!cars.isEmpty()) {
                    Car car = cars.remove(0);
                    car.assignRider(rider);
                    break;
                }
            }
            try {
                System.out.println("Rider " + rider.id + " is waiting for a car to be available");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
    }
}
