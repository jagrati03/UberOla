package services;

import model.Driver;
import model.Location;

import java.util.HashMap;
import java.util.Map;

public class DriverServiceImplementation implements DriverService{
    private final Map<String, Driver> drivers = new HashMap<>();

    public Driver registerDriver(String id, String name) {
        Driver driver = new Driver(id, name);
        drivers.put(id, driver);
        return driver;
    }

    public Driver getDrivers(String id) {
        return drivers.get(id);
    }

    public void triggerRideNotification(String driverName, double fare, Location destination) {

    }
}
