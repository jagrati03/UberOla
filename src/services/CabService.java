package services;

import model.Cab;
import model.Location;
import model.VehicleType;

import java.util.List;

public interface CabService{
    public Cab registerCab(String id, String name, Location location, VehicleType vehicleType);
    public Cab getCabById(int id);
    public Cab findNearestAvailableCab(Location location, VehicleType vehicleType);
    public List<Cab> findNearestAvailableCabs(Location location, VehicleType vehicleType);

}
