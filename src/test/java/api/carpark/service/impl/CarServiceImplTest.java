package api.carpark.service.impl;

import api.carpark.exception.model.CarNotFoundException;
import api.carpark.model.Car;
import api.carpark.model.command.CreateCarCommand;
import api.carpark.model.command.UpdateCarCommand;
import api.carpark.model.dto.CarDto;
import api.carpark.model.mapper.CarMapper;
import api.carpark.repository.CarRepository;
import api.carpark.repository.GarageRepository;
import api.carpark.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private GarageRepository garageRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void shouldReturnCarWhenGetCarWithValidId() {
        Car car = new Car();
        CarDto carDto = new CarDto();

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carService.getCar(1L);

        assertEquals(carDto, result);
    }
    @Test
    void shouldThrowExceptionWhenGetCarWithInvalidId() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.getCar(1L));
    }
    @Test
    void shouldReturnAllCarsWhenGetAllCars() {
        Car car1 = new Car();
        Car car2 = new Car();

        CarDto carDto1 = new CarDto();
        CarDto carDto2 = new CarDto();

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));
        when(carMapper.toDto(car1)).thenReturn(carDto1);
        when(carMapper.toDto(car2)).thenReturn(carDto2);

        List<CarDto> result = carService.getAllCars();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(carDto1, carDto2)));
    }

    @Test
    void shouldCreateCar() {
        CreateCarCommand command = CreateCarCommand.builder()
                .brand("Brand")
                .model("Model")
                .productionYear(2022)
                .fuelType("Diesel")
                .garageId(1L)
                .build();

        Car car = new Car();
        CarDto carDto = new CarDto();

        when(carRepository.save(any(Car.class))).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carService.createCar(command);

        assertEquals(carDto, result);
    }

    @Test
    void shouldDeleteCar() {
        Car car = new Car();

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        assertDoesNotThrow(() -> carService.deleteCar(1L));

        verify(carRepository).delete(car);
    }

    @Test
    void shouldThrowExceptionWhenDeleteCarWithInvalidId() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.deleteCar(1L));
    }

}