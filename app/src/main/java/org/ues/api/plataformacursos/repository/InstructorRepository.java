package org.ues.api.plataformacursos.repository;

import org.ues.api.plataformacursos.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// La interfaz hereda todos los métodos CRUD básicos
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // Método personalizado para buscar un Instructor por nombre
    Instructor findByNombre(String nombre);
}
/*Esta interfaz es la encargada de manejar las operaciones con la base de datos para la entidad Instructor.
Hereda de JpaRepository<Instructor, Long>, por lo que Spring Data JPA genera automáticamente los métodos CRUD
más comunes*/