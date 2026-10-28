package pricing;

import model.Location;
import model.VehicleType;

public class SurgePricingStrategy implements PricingStrategy {
    private final int demandCount;
    //private final DemandService demandService;

    public SurgePricingStrategy(int demandCount) {
        this.demandCount = demandCount;
    }

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

        double surgeMultipler = 1 + (demandCount/10.0);
        //demandService.getSurgeMultiplier(from, to)
        double distance = from.distanceTo(to);
        return baseFare * distance * surgeMultipler;
    }
}
