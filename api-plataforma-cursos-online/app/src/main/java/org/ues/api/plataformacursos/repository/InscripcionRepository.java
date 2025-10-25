package org.ues.api.plataformacursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ues.api.plataformacursos.model.Inscripcion;

// Permite manejar las inscripciones directamente con los métodos estándar de Spring Data JPA.
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
