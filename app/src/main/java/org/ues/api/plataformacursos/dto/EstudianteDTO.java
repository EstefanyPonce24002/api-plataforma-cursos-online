package org.ues.api.plataformacursos.dto;

import lombok.Data;
/* Para hacer uso de la clase list
  Permite manejar colecciones de elementos ordenados*/
import java.util.List;

@Data
// Contiene los campos principales del estudiante
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    //private String especialidad;

    // Opcional: lista de inscripciones o cursos inscritos
    private List<Long> cursosIds;

    /*Este DTO representa la información del estudiante que se intercambia con el cliente, incluyendo id, nombre, email
     y contraseña. También puede contener los cursos asociados mediante una lista de IDs. La anotación `@Data` genera
     automáticamente los métodos necesarios, haciendo el código más limpio y fácil de mantener.*/
}
