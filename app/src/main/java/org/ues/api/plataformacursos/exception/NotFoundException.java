package org.ues.api.plataformacursos.exception;

// Indica que un recurso no fue encontrado
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
