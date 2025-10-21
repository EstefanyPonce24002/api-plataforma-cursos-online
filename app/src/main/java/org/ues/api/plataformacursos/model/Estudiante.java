package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;  // Genera los métodos y constructor

@Entity
@DiscriminatorValue("ESTUDIANTE")
@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper = true) // Incluye los campos de Usuario al imprimir

/* Entidad Estudiante, que hereda de la clase Usuario y representa
a los estudiantes en la base de datos*/
public class Estudiante extends Usuario {

    // Constructor personalizado (para crear instancias sin usar el ID)
    public Estudiante(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, "ESTUDIANTE");
    }
}
