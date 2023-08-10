package api.carpark.controller;

import api.carpark.model.command.CreateGarageCommand;
import api.carpark.model.command.UpdateGarageCommand;
import api.carpark.model.dto.GarageDto;
import api.carpark.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;


    @GetMapping
    public ResponseEntity<List<GarageDto>> getAllGarages() {
        return new ResponseEntity<>(garageService.getAllGarages(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageDto> getGarage(@PathVariable Long id) {
        return new ResponseEntity<>(garageService.getGarage(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GarageDto> createGarage(@RequestBody CreateGarageCommand command) {
        return new ResponseEntity<>(garageService.createGarage(command), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageDto> updateGarage(@PathVariable Long id, @RequestBody UpdateGarageCommand command) {
        return new ResponseEntity<>(garageService.updateGarage(id, command), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
