package services;

import model.Cab;
import model.Location;
import model.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class CabServiceImplementation implements CabService {
    private final List<Cab> cabs = new ArrayList<>();

    public Cab registerCab(String id, String name, Location location, VehicleType vehicleType) {
        Cab cab = new Cab(id, name, vehicleType, location);
        cabs.add(cab) ;
        return cab;
    }

    public Cab getCabById(int id) {
        return cabs.get(id);
    }

    public Cab findNearestAvailableCab(Location location, VehicleType vehicleType) {
        Cab nearestCab = null;
        double minDistance = Double.MAX_VALUE;
        for(Cab cab : cabs) {
            if(cab.isAvaliable() == true && cab.getVehicleType() == vehicleType) {
                double distance = cab.getLocation().distanceTo(location);
                if(distance < minDistance) {
                    minDistance = distance;
                    nearestCab = cab;
                }
            }
        }
        return nearestCab;
    }

    public List<Cab> findNearestAvailableCabs(Location location, VehicleType vehicleType) {
        List<Cab> nearestCabs = new ArrayList<>();
        double minDistance = Double.MAX_VALUE;
        for(Cab cab : cabs) {
            if(cab.isAvaliable() == true && cab.getVehicleType() == vehicleType) {
                double distance = cab.getLocation().distanceTo(location);
                if(distance < minDistance) {
                    minDistance = distance;
                    nearestCabs.add(cab);
                }
            }
        }
        return nearestCabs;
    }
}
