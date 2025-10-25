package org.ues.api.plataformacursos.dto;

import lombok.Data;
import java.util.List;

@Data
public class InstructorDTO {
    // Datos principales del instructor
    private Long id;
    private String nombre;
    private String contrasena;
    private String email;
    //private String especialidad;

    // Opcional: para que muestre los cursos que imparte el instructor
    private List<Long> cursosIds;
}
