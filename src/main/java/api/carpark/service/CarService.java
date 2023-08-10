

package api.carpark.service;

import api.carpark.model.command.CreateCarCommand;
import api.carpark.model.command.UpdateCarCommand;
import api.carpark.model.dto.CarDto;

import java.util.List;

public interface CarService {

    List<CarDto> getAllCars();

    CarDto getCar(Long id);

    CarDto createCar(CreateCarCommand command);

    CarDto updateCar(Long id, UpdateCarCommand command);

    void deleteCar(Long id);

    CarDto addCarToGarage(Long carId, Long garageId);

    CarDto removeCarFromGarage(Long carId);
}
