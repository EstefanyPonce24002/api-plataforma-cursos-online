package org.ues.api.plataformacursos.repository;

import org.ues.api.plataformacursos.model.Estudiante;
// Proporciona métodos listos para usar e interactuar con la base de datos
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//marca la interfaz como un componente de acceso a datos de Spring
@Repository
// Hereda todos los métodos CRUD
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Método personalizado para buscar un estudiante por su email
    Estudiante findByEmail(String email);

}

