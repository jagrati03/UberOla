package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
    //private final int id;
    private final Rider rider;
    private final Cab cab;
    private final Location pickupLocation;
    private final Location dropOffLocation;
    private final LocalTime bookingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private double fare;
    private Status status;

    //so otp needs to be generated at the time of booking
    private final String otp;

    public Booking(Rider rider, Cab cab, Location pickupLocation, Location dropOffLocation, double fare) {
        this.rider = rider;
        this.cab = cab;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.bookingTime = LocalTime.now();
        this.fare = fare;
        this.otp = generateOtp();
    }

    public Rider getRider() {
        return rider;
    }

    public Cab getCab() {
        return cab;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String generateOtp() {
        int otp = 1000 * (int) (Math.random() * 9000);
        return String.valueOf(otp);
    }

    public String getOtp() {
        return otp;
    }

    public void summary() {
        System.out.println("Rider ID: " + rider.getId());
        System.out.println("Rider Name: " + rider.getName());
        System.out.println("Start Location: " + rider.getLocation());
        System.out.println("End Location: " + cab.getLocation());
        System.out.println("Booking time" + bookingTime);
        System.out.println("TOTAL FARE: " + fare);
    }
}
