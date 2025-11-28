package org.ues.api.plataformacursos.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

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