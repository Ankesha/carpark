package api.carpark.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import api.carpark.exception.model.GarageNotFoundException;
import api.carpark.model.Garage;
import api.carpark.model.command.CreateGarageCommand;
import api.carpark.model.command.UpdateGarageCommand;
import api.carpark.model.dto.GarageDto;
import api.carpark.model.mapper.GarageMapper;
import api.carpark.repository.GarageRepository;
import api.carpark.service.impl.GarageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GarageServiceImplTest {

    @Mock
    private GarageRepository garageRepository;

    @Mock
    private GarageMapper garageMapper;

    @InjectMocks
    private GarageServiceImpl garageService;

    @Test
    void shouldReturnGarageWhenGetGarageWithValidId() {
        Garage garage = new Garage();
        GarageDto garageDto = new GarageDto();

        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));
        when(garageMapper.toDto(garage)).thenReturn(garageDto);

        GarageDto result = garageService.getGarage(1L);

        assertEquals(garageDto, result);
    }

    @Test
    void shouldThrowExceptionWhenGetGarageWithInvalidId() {
        when(garageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GarageNotFoundException.class, () -> garageService.getGarage(1L));
    }

    @Test
    void shouldReturnAllGaragesWhenGetAllGarages() {
        Garage garage1 = new Garage();
        Garage garage2 = new Garage();

        GarageDto garageDto1 = new GarageDto();
        GarageDto garageDto2 = new GarageDto();

        when(garageRepository.findAll()).thenReturn(Arrays.asList(garage1, garage2));
        when(garageMapper.toDto(garage1)).thenReturn(garageDto1);
        when(garageMapper.toDto(garage2)).thenReturn(garageDto2);

        List<GarageDto> result = garageService.getAllGarages();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(garageDto1, garageDto2)));
    }

    @Test
    void shouldCreateGarage() {
        CreateGarageCommand command = CreateGarageCommand.builder()
                .address("Address")
                .capacity(10)
                .acceptsLpg(true)
                .build();

        Garage garage = new Garage();
        GarageDto garageDto = new GarageDto();

        when(garageRepository.save(any(Garage.class))).thenReturn(garage);
        when(garageMapper.toDto(garage)).thenReturn(garageDto);

        GarageDto result = garageService.createGarage(command);

        assertEquals(garageDto, result);
    }

    @Test
    void shouldUpdateGarage() {
        UpdateGarageCommand command = UpdateGarageCommand.builder()
                .address("Updated address")
                .capacity(15)
                .acceptsLpg(false)
                .build();

        Garage garage = new Garage();
        GarageDto garageDto = new GarageDto();

        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));
        when(garageRepository.save(any(Garage.class))).thenReturn(garage);
        when(garageMapper.toDto(garage)).thenReturn(garageDto);

        GarageDto result = garageService.updateGarage(1L, command);

        assertEquals(garageDto, result);
    }

    @Test
    void shouldDeleteGarage() {
        Garage garage = new Garage();

        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));

        assertDoesNotThrow(() -> garageService.deleteGarage(1L));

        verify(garageRepository).delete(garage);
    }

    @Test
    void shouldThrowExceptionWhenDeleteGarageWithInvalidId() {
        when(garageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GarageNotFoundException.class, () -> garageService.deleteGarage(1L));
    }
}
