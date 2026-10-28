package services;

import model.*;
import pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImplementation implements BookingService {
    //for one booking you will need it's respective cabService and rider information
    //so let's do dependency injection
    private final CabServiceImplementation cabService;
    private final DriverServiceImplementation driverService;
    private final PricingStrategy pricingStrategy;

    public BookingServiceImplementation(CabServiceImplementation cab, DriverServiceImplementation driverService, PricingStrategy pricingStrategy) {
        this.cabService = cab;
        this.driverService = driverService;
        this.pricingStrategy = pricingStrategy;
    }

    //functions like List all Vehicle type fares
    public List<VehicleFareEstimate> showFareEstimates(Location from, Location to) {
        List<VehicleFareEstimate> estimates = new ArrayList<>();
        for(VehicleType vehicleType : VehicleType.values()){
            double fare = pricingStrategy.calculatePrice(from, to, vehicleType);
            double roundedFare = Math.ceil(fare * 100.0) / 100.0;
            estimates.add(new VehicleFareEstimate(vehicleType, roundedFare));
        }
        return estimates;
    }
    //book cabService

    public void requestCab(Rider rider, VehicleType vehicleType, Location destination) {
        double fare = pricingStrategy.calculatePrice(rider.getLocation(), destination, vehicleType);
        //send this fare to all nearest available drivers and see if they want to accept the ride

        List<Cab> nearestAvailableCabs = cabService.findNearestAvailableCabs(destination, vehicleType);
        for (Cab cab : nearestAvailableCabs) {
            driverService.triggerRideNotification(cab.getDriverName(), fare, destination);
        }
    }

    //@AcceptListener
    public Booking bookCab(Cab acceptedCab, Rider rider, VehicleType vehicleType, Location destination) {
        //so at this point user has selected the vehicle type all i have to do is change the isAvailable and booking status
        //need to create a new booking
        double fare = pricingStrategy.calculatePrice(rider.getLocation(), destination, vehicleType);

        acceptedCab.assignToRide();
        Booking newBooking = new Booking(rider, acceptedCab, rider.getLocation(), destination, fare);
        newBooking.setStatus(Status.CREATED);

        return newBooking;
    }

    public void driverStartRide(Booking booking, String enteredOtp) {
        if(!booking.getOtp().equals(enteredOtp)){
            System.out.println("Invalid OTP, Ride cannot be booked");
            return;
        }

        booking.setStatus(Status.STARTED);
        booking.setStartTime(LocalDateTime.now());
        System.out.println("RIDE STARTED");
    }

    public void endRide(Booking booking) {
        if(booking.getStatus() != Status.STARTED) {
            System.out.println("Ride not started");
            return;
        }
        booking.setStatus(Status.ENDED);
        double fare = booking.getFare();
        booking.setEndTime(LocalDateTime.now());
        System.out.println("RIDE ENDED");
    }
}
