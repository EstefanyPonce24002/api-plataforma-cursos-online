package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
// Indica el valor que se guardará en la columna discriminadora cuando se use herencia en JPA
@DiscriminatorValue("INSTRUCTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/* Entidad Instructor, que hereda de la clase Usuario y representa
a los instructores en la base de datos*/
public class Instructor extends Usuario {


    // Indica que un instructor puede tener muchos cursos.
    // Si se elimina un Instructor, también se eliminan sus cursos
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    // Para evitar duplicados
    private Set<Curso> cursos = new HashSet<>();
}