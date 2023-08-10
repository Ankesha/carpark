package api.carpark.exception.model;

public class GarageFullException extends RuntimeException {
    public GarageFullException() {
        super("The garage is full.");
    }

}
