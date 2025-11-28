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

    /*Este DTO se usa para transferir la información esencial del curso entre el cliente y el servidor. Incluye el id,
    título, descripción, estado y el instructor relacionado. Gracias a @Data, se generan automáticamente los métodos
    necesarios sin escribir código extra.*/
}
