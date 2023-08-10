package api.carpark.model.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGarageCommand {
    @NotNull(message = "address cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "address can only contain alphabets and space")
    private String address;
    @Min(value = 1, message = "capacity must be greater than 1")
    private int capacity;
    @NotNull
    private boolean acceptsLpg;
}
