package api.carpark.service;

import api.carpark.model.command.CreateGarageCommand;
import api.carpark.model.command.UpdateGarageCommand;
import api.carpark.model.dto.GarageDto;

import java.util.List;

public interface GarageService {
    List<GarageDto> getAllGarages();

    GarageDto getGarage(Long id);

    GarageDto createGarage(CreateGarageCommand command);

    GarageDto updateGarage(Long id, UpdateGarageCommand command);

    void deleteGarage(Long id);


}
