package api.carpark.exception.model;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("Car with id " + id + " not found.");
    }
}
