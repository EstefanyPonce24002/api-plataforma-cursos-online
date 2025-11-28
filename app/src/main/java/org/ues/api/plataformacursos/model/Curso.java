package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cursos")  // Especifica el nombre de la tabla.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Define la entidad Curso, que representa la tabla cursos en la base de datos.
public class Curso {

    // Define la clave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private String descripcion;

    @Column(nullable = false)
    private String estado = "ACTIVO";

    // Establece la relación de muchos cursos con un instructor
    @ManyToOne
    @JoinColumn(name = "id_instructor")
    private Instructor instructor;
    /*Esta clase representa la entidad Curso, es decir, el modelo que se almacena directamente en la base de datos
    dentro de la tabla cursos. Se usa @Entity y @Table para mapearla, y @Id junto con @GeneratedValue para definir una
    clave primaria auto-incremental. Incluye datos como título, descripción y estado del curso. Además, establece una
    relación ManyToOne con la entidad Instructor, indicando que un instructor puede tener varios cursos.
    Lombok simplifica el código generando constructores, getters y setters automáticamente.*/

}
