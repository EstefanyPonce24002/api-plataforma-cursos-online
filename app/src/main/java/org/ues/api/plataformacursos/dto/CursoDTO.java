package org.ues.api.plataformacursos.dto;

import lombok.Data;

@Data //Genera automáticamente los getters, setters, equals, hashCode y toString.
public class CursoDTO {
    //Información básica del curso que se intercambia entre el cliente y el servidor.
    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Long idInstructor;
}
