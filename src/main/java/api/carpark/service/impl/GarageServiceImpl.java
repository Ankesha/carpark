package api.carpark.service.impl;

import api.carpark.exception.model.GarageNotFoundException;
import api.carpark.model.Garage;
import api.carpark.model.command.CreateGarageCommand;
import api.carpark.model.command.UpdateGarageCommand;
import api.carpark.model.dto.GarageDto;
import api.carpark.model.mapper.GarageMapper;
import api.carpark.repository.GarageRepository;
import api.carpark.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;
    private final GarageMapper garageMapper;


    @Override
    public List<GarageDto> getAllGarages() {
        return garageRepository.findAll().stream()
                .map(garageMapper::toDto)
                .toList();
    }

    @Override
    public GarageDto getGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new GarageNotFoundException(id));
        return garageMapper.toDto(garage);
    }

    @Override
    @Transactional
    public GarageDto createGarage(CreateGarageCommand command) {
        Garage garage = new Garage();
        garage.setAddress(command.getAddress());
        garage.setCapacity(command.getCapacity());
        garage.setAcceptsLpg(command.isAcceptsLpg());
        Garage savedGarage = garageRepository.save(garage);
        return garageMapper.toDto(savedGarage);
    }

    @Override
    @Transactional
    public GarageDto updateGarage(Long id, UpdateGarageCommand command) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new GarageNotFoundException(id));
        garage.setAddress(command.getAddress());
        garage.setCapacity(command.getCapacity());
        garage.setAcceptsLpg(command.isAcceptsLpg());
        Garage updatedGarage = garageRepository.save(garage);
        return garageMapper.toDto(updatedGarage);
    }

    @Override
    @Transactional
    public void deleteGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new GarageNotFoundException(id));
        garageRepository.delete(garage);
    }
}
