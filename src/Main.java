import Repository.MockRiderStore;
import model.*;
import pricing.PricingStrategy;
import pricing.VehicleTypePricing;
import services.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RiderService riderService = new RiderService(new MockRiderStore()); //can be fetched from any database
        CabService cabService = new CabServiceImplementation();
        DriverService driverService = new DriverServiceImplementation();

        PricingStrategy pricingStrategy = new VehicleTypePricing();
        BookingService bookingService = new BookingServiceImplementation(cabService, driverService, pricingStrategy);

        Rider rider = riderService.registerRider("1", "Sanjay");

        Location from = new Location(10, 10);

        rider.setLocation(from);
        Location destination = new Location(20, 20);

        driverService.registerDriver("d1", "D1");
        driverService.registerDriver("d2", "D2");
        driverService.registerDriver("d3", "D3");
        driverService.registerDriver("d4", "D4");
        driverService.registerDriver("d5", "D5");

        cabService.registerCab("c1", driverService.getDrivers("d1").getName(), new Location(10, 20), VehicleType.AUTO);
        cabService.registerCab("c2", "D2", new Location(20, 20), VehicleType.AUTO);
        cabService.registerCab("c2", "D3", new Location(14, 20), VehicleType.BIKE);
        cabService.registerCab("c4", "D4", new Location(12, 10), VehicleType.SEDAN);
        cabService.registerCab("c5", "D5", new Location(60, 20), VehicleType.SUV);

        //bookingService.bookCab(rider, VehicleType.AUTO, destination);

        List<VehicleFareEstimate> estimateList = bookingService.showFareEstimates(from, destination);
        for (VehicleFareEstimate vehicleFareEstimate : estimateList) {
            System.out.println(" - " +vehicleFareEstimate);
        }

        VehicleType chosenType = VehicleType.SEDAN;

        Booking booking;
        try {
            bookingService.requestCab(rider, chosenType, destination);
            booking = bookingService.bookCab(cabService.getCabById(0), rider, chosenType, destination);
            System.out.println("Booking confirmed. OTP: " + booking.getOtp());

        } catch (RuntimeException e) {
            System.err.println("Error booking: " + e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter OTP");
        String otp = scanner.nextLine();

        bookingService.driverStartRide(booking, otp);

        if(booking.getStatus() != Status.STARTED) return;

        for(int i = 1; i <= 5; i++) {
            try{
                Thread.sleep(1000);
                System.out.println("Take left from " + i +" kilometers");
            }
            catch (InterruptedException e){
                System.err.println("ride ended before completing: " + e.getMessage());
                return;
            }
        }

        bookingService.endRide(booking);

        booking.summary();
    }
}