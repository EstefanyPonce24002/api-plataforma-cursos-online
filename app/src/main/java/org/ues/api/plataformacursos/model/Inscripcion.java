package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones") // Especifica el nombre de la tabla.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"estudiante", "curso"}) // Excluir relaciones para evitar errores de pila

/*La entidad Inscripción funciona como una tabla intermedia que relaciona cursos y estudiantes. Lleva su propio estado
y fecha de registro, y usa carga diferida para optimizar las consultas.*/

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Estudiante
    @ManyToOne(fetch = FetchType.LAZY) // Usar LAZY para mejorar rendimiento
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    // Relación con Curso
    @ManyToOne(fetch = FetchType.LAZY) // Usar LAZY para mejorar rendimiento
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    // Estado de la inscripción
    @Column(nullable = false)
    private String estado; // Ej: "ACTIVA", "CANCELADA", "COMPLETADA"

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;

    @PrePersist
    protected void onCreate() {
        this.fechaInscripcion = LocalDateTime.now();
    }
}