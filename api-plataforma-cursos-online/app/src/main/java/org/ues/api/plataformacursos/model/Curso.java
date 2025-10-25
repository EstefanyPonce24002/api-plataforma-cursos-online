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
}
