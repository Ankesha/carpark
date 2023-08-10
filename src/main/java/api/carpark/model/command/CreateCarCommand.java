package api.carpark.model.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCarCommand {

    @NotNull(message = "brand cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "brand can only contain alphabets and space")
    private String brand;
    @NotNull(message = "model cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "model can only contain alphabets and space")
    private String model;
    @Min(value = 1900, message = "production must be greater than 1900")
    private int productionYear;
    @NotNull(message = "fuelType cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "fuelType can only contain alphabets")
    private String fuelType;
    @NotNull
    @Min(value = 0, message = "garageId must be greater than or equal to 0")
    private Long garageId;
}
