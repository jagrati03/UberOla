package services;

import model.*;
import java.util.List;

public interface BookingService {

    List<VehicleFareEstimate> showFareEstimates(Location from, Location to);

    void requestCab(Rider rider, VehicleType vehicleType, Location destination);

    Booking bookCab(Cab acceptedCab, Rider rider, VehicleType vehicleType, Location destination);

    void driverStartRide(Booking booking, String enteredOtp);

    void endRide(Booking booking);
}
