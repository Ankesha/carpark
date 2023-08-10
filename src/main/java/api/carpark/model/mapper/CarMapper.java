package api.carpark.model.mapper;

import api.carpark.model.Car;
import api.carpark.model.command.CreateCarCommand;
import api.carpark.model.command.UpdateCarCommand;
import api.carpark.model.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toDto(Car car);
    // Car toEntity(CreateCarCommand command);
    //void updateEntityFromCommand(UpdateCarCommand command, @MappingTarget Car car);
}
