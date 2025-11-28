package org.ues.api.plataformacursos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios") // Especifica el nombre de la tabla
@Getter
@Setter
@ToString // Permite imprimir el objeto (útil para debugging)
@NoArgsConstructor
@AllArgsConstructor
// HERENCIA: Todos los datos (Usuario, Estudiante, Instructor) irán a una sola tabla 'usuarios'.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Columna que distingue los tipos (Estudiante, Instructor)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)

/*Define la clase abstracta Usuario, que sirve como superclase
 para Estudiante e Instructor dentro del modelo*/
public abstract class Usuario {

    // Se indica cuál será el ID de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    // Almacena el valor del DiscriminatorValue ("ESTUDIANTE" o "INSTRUCTOR")
    @Column(name = "tipo_usuario", insertable = false, updatable = false, nullable = false)
    private String rol;

    // Constructor que las subclases usarán para inicializar campos.
    public Usuario(String nombre, String email, String contrasena, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol; // El rol se establece aquí para el constructor completo.

    /*Usuario es una entidad abstracta que centraliza los atributos comunes de todos los usuarios. Se usa herencia en una
    sola tabla para optimizar consultas, y el tipo_usuario se controla con un discriminador que garantiza que cada
    registro esté asociado al tipo correcto.*/
    }
}