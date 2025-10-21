package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripciones") // Especifica el nombre de la tabla.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Estudiante
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    // Relación con Curso
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    // Estado de la inscripción
    @Column(nullable = false)
    private String estado; // Ej: "ACTIVA", "CANCELADA", "COMPLETADA"
}
