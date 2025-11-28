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

    /*Este DTO maneja la información principal de un instructor, como id, nombre, email y contraseña. También puede
    incluir la lista de cursos que imparte, representados por sus IDs. Se utiliza para enviar y recibir datos del
    instructor en la API sin exponer directamente la entidad de la base de datos.*/
}
