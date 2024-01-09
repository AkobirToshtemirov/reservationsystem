package com.pdp.reservationsystem.exception;

import com.pdp.reservationsystem.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> error404(NotFoundException e, HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .errorBody(e.getMessage())
                        .build());
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ErrorResponseDTO> operationExceptionHandler(OperationException e, HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorBody(e.getMessage())
                        .build());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> validationExceptionHandler(ValidationException e, HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorBody(e.getMessage())
                        .build());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> notValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest req) {
        Map<String, List<String>> errorBody = new HashMap<>();

        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errorBody.compute(field, (s, strings) -> {
                strings = Objects.requireNonNullElse(strings, new ArrayList<>());
                strings.add(message);
                return strings;
            });
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorBody(errorBody)
                        .build());
    }
}
