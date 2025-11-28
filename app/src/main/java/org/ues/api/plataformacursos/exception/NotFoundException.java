package org.ues.api.plataformacursos.exception;

// Indica que un recurso no fue encontrado
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);

    /*Este código define una excepción personalizada llamada NotFoundException.
    Sirve para cuando algo no existe en la base de datos o en el sistema (por ejemplo, buscar un estudiante por ID y no
    encontrarlo).*/

    }
}
