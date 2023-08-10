package api.carpark.exception;

import api.carpark.exception.model.*;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleCarNotFoundException(CarNotFoundException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ExceptionHandler(GarageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleGarageNotFoundException(GarageNotFoundException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ExceptionHandler(GarageFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleGarageFullException(GarageFullException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ExceptionHandler(LpgNotAcceptedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleLpgNotAcceptedException(LpgNotAcceptedException ex) {
        return new ExceptionDto(ex.getMessage());
    }
}