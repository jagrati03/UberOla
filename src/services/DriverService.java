package services;

import model.Driver;
import model.Location;

public interface DriverService {
    public Driver registerDriver(String id, String name);
    public Driver getDrivers(String id);
    public void triggerRideNotification(String driverName, double fare, Location destination);
}
