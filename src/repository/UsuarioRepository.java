package org.ues.api.plataformacursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ues.api.plataformacursos.model.Usuario;

//
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Ejemplo de query personalizada
    boolean existsByNombre(String nombre);
}
