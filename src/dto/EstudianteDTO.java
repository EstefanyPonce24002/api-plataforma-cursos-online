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
    private String especialidad;

    // Opcional: lista de inscripciones o cursos inscritos
    private List<Long> cursosIds;
}
