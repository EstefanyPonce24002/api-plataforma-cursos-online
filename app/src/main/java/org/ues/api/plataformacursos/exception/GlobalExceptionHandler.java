package org.ues.api.plataformacursos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler; //Permite capturar y manejar excepciones específicas.
import org.springframework.web.bind.annotation.RestControllerAdvice; // Convierte la clase en un manejador global de
// errores para todos los controladores REST.

/* Permiten devolver mensajes de error o datos estructurados
en las respuestas de la API*/
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. MANEJO DE ERRORES DE VALIDACIÓN (400 BAD REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 2. MANEJO DE RECURSO NO ENCONTRADO (404 NOT FOUND)
    // Este handler atrapa tus NotFoundException y devuelve el código HTTP correcto.
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Recurso no encontrado");
        error.put("message", ex.getMessage());
        // 404 Not Found
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 3. MANEJO DE ERRORES DE LÓGICA DE NEGOCIO (409 CONFLICT)
    // Usa esto para errores como "El email ya existe" o "No puedes cancelar una inscripción cancelada".
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Conflicto de lógica de negocio");
        error.put("message", ex.getMessage());
        // 409 Conflict (ideal para errores de negocio)
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 4. MANEJO GENERAL DE OTROS ERRORES (500 INTERNAL SERVER ERROR)
    // Captura las RuntimeException que no son 404 o 409 y otros errores inesperados.
    // Lo ideal es devolver 500 para errores internos que no el cliente no puede corregir.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno del servidor");
        error.put("message", ex.getMessage());
        // 500 Internal Server Error (para errores no previstos)
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}