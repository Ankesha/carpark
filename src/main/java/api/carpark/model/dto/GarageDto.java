package api.carpark.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GarageDto {

    private Long id;
    private String adress;
    private int capacity;
    private boolean acceptsLpg;
    private List<Long> carIds;
}
