package model;

public class Cab {

    private final String id;
    private final String driverName;
    private boolean isAvaliable;

    private Location location;

    private final VehicleType  vehicleType;

    public Cab(String id, String driverName, VehicleType vehicleType, Location location) {
        this.id = id;
        this.driverName = driverName;
        this.vehicleType = vehicleType;
        this.isAvaliable = true;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    public void assignToRide() {
        isAvaliable = false;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
