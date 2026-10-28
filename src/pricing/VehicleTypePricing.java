package pricing;

import model.Location;
import model.VehicleType;

public class VehicleTypePricing implements PricingStrategy {
    @Override
    public double calculatePrice(Location to, Location from, VehicleType vehicleType) {
        double baseFare;
        switch (vehicleType) {
            case BIKE: {
                baseFare = 8.0;
                break;
            }
            case SEDAN: {
                baseFare = 15.0;
                break;
            }
            case SUV: {
                baseFare = 18.0;
                break;
            }
            case AUTO: {
                baseFare = 6.0;
                break;
            }
            default:
                baseFare = 0.0;

        }
        double distance = from.distanceTo(to);
        return baseFare * distance;
    }
}
