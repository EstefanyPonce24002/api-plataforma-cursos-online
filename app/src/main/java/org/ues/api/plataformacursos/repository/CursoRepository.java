package org.ues.api.plataformacursos.repository;

import org.ues.api.plataformacursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository; //Hereda los métodos CRUD básicos
import java.util.List;

// Maneja las operaciones de base de datos para la entidad Curso.
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByInstructorId(Long instructorId); // Obtiene todos los cursos de un instructor específico
    List<Curso> findByTituloContainingIgnoreCase(String titulo); // Busca cursos cuyo título contenga una palabra
    List<Curso> findByEstado(String estado); // Lista cursos según su estado

    /*“CursoRepository encapsula toda la comunicación con la base de datos para la entidad Curso. Gracias a JpaRepository
    obtenemos CRUD sin código adicional, y los métodos de búsqueda personalizados se generan automáticamente a partir
    del nombre del método, lo que hace el código más limpio y fácil de mantener.*/

}
