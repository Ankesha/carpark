package api.carpark.service.impl;

import api.carpark.exception.model.CarNotFoundException;
import api.carpark.exception.model.GarageFullException;
import api.carpark.exception.model.GarageNotFoundException;
import api.carpark.exception.model.LpgNotAcceptedException;
import api.carpark.model.Car;
import api.carpark.model.Garage;
import api.carpark.model.command.CreateCarCommand;
import api.carpark.model.command.UpdateCarCommand;
import api.carpark.model.dto.CarDto;
import api.carpark.model.mapper.CarMapper;
import api.carpark.repository.CarRepository;
import api.carpark.repository.GarageRepository;
import api.carpark.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final GarageRepository garageRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .toList();
    }

    @Override
    public CarDto getCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        return carMapper.toDto(car);
    }

    @Override
    @Transactional
    public CarDto createCar(CreateCarCommand command) {
        Car car = new Car();
        car.setBrand(command.getBrand());
        car.setModel(command.getModel());
        car.setProductionYear(command.getProductionYear());
        car.setFuelType(command.getFuelType());
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }

    @Transactional
    public CarDto updateCar(Long id, UpdateCarCommand command) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        car.setBrand(command.getBrand());
        car.setModel(command.getModel());
        car.setProductionYear(command.getProductionYear());
        car.setFuelType(command.getFuelType());
        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    @Override
    @Transactional
    public CarDto addCarToGarage(Long carId, Long garageId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new GarageNotFoundException(garageId));
        if (garage.getCars().size() >= garage.getCapacity()) {
            throw new GarageFullException();
        }
        if (car.getFuelType().equals("LPG") && !garage.isAcceptsLpg()) {
            throw new LpgNotAcceptedException();
        }
        car.setGarage(garage);
        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }

    @Override
    @Transactional
    public CarDto removeCarFromGarage(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        car.setGarage(null);
        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }
}

