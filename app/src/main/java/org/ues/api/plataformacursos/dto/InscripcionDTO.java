package org.ues.api.plataformacursos.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*Este DTO representa los datos de una inscripción entre estudiante y curso. Incluye el ID, estado, fecha de inscripción
y los identificadores del estudiante y del curso. Aplica validaciones para asegurar que ambos IDs sean obligatorios
antes de procesar la solicitud. Sirve como objeto seguro para la comunicación entre el cliente y la API.*/

@Data
public class InscripcionDTO {

    //private LocalDateTime fechaInscripcion;
    private Long id;
    private String estado;

    // ID del estudiante involucrado
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long idEstudiante;

    // ID del curso involucrado
    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;

    // Guarda la fecha en que el estudiante se registró o fue inscrito
    private LocalDateTime fechaInscripcion;
}