package api.carpark.exception.model;

public class GarageNotFoundException extends RuntimeException {
    public GarageNotFoundException(Long id) {
        super("Garage with id " + id + " not found.");
    }
}
