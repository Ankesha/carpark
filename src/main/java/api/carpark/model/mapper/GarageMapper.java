package api.carpark.model.mapper;


import api.carpark.model.Garage;
import api.carpark.model.command.CreateGarageCommand;
import api.carpark.model.command.UpdateGarageCommand;
import api.carpark.model.dto.GarageDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface GarageMapper {
    GarageDto toDto(Garage garage);
    //Garage toEntity(CreateGarageCommand command);
    //void updateEntityFromCommand(UpdateGarageCommand command, @MappingTarget Garage garage);
}
