package pricing;

import model.Location;
import model.VehicleType;


public interface PricingStrategy {
    double calculatePrice(Location to, Location from, VehicleType vehicleType);
}
