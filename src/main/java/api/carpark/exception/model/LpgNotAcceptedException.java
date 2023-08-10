package api.carpark.exception.model;

public class LpgNotAcceptedException extends RuntimeException {
    public LpgNotAcceptedException() {
        super("LPG not accepted in the garage.");
    }
}
