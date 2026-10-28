package model;

public class Location {
    private final double x;
    private final double y;

    public Location(double y, double x) {
        this.y = y;
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Location location) {
        double dx = this.x - location.x;
        double dy = this.y - location.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        return (x + "," + y);
    }
}
