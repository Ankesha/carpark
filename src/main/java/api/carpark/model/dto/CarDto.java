package api.carpark.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private String fuelType;
    private Long garageId;


}
