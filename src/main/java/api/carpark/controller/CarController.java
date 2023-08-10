package api.carpark.controller;

import api.carpark.model.command.CreateCarCommand;
import api.carpark.model.command.UpdateCarCommand;
import api.carpark.model.dto.CarDto;
import api.carpark.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;


    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CreateCarCommand command) {
        return new ResponseEntity<>(carService.createCar(command), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody UpdateCarCommand command) {
        return new ResponseEntity<>(carService.updateCar(id, command), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/garages/{garageId}")
    public ResponseEntity<CarDto> addCarToGarage(@PathVariable Long id, @PathVariable Long garageId) {
        CarDto updatedCar = carService.addCarToGarage(id, garageId);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<CarDto> removeCarFromGarage(@PathVariable Long id) {
        CarDto updatedCar = carService.removeCarFromGarage(id);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
}
