package com.example.demosupermercado.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiError> handleNotFound(RecursoNoEncontradoException exception, WebRequest request) {
        return error(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler({ConflictoDeRecursoException.class, StockInsuficienteException.class})
    public ResponseEntity<ApiError> handleConflict(RuntimeException exception, WebRequest request) {
        return error(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException exception, WebRequest request) {
        return error(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataConflict(WebRequest request) {
        return error(HttpStatus.CONFLICT, "La operación viola una restricción de datos.", request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiValidationError> handleValidation(
            MethodArgumentNotValidException exception,
            WebRequest request) {
        Map<String, String> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() == null ? "Valor inválido" : error.getDefaultMessage(),
                        (first, second) -> first));
        ApiValidationError body = new ApiValidationError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "La solicitud contiene campos inválidos.",
                request.getDescription(false).replace("uri=", ""),
                fieldErrors);
        return ResponseEntity.badRequest().body(body);
    }

    private ResponseEntity<ApiError> error(HttpStatus status, String message, WebRequest request) {
        ApiError body = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(status).body(body);
    }

    public record ApiError(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            String path) {
    }

    public record ApiValidationError(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            String path,
            Map<String, String> fieldErrors) {
    }
}
