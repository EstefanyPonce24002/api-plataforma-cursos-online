package org.ues.api.plataformacursos;

/* Importa la clase SpringApplication, que es esencial para arrancar nuestra aplicación
   Importa la anotación @SpringBootApplication, la cual se coloca sobre la clase principal.*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// La anotación le dice a Spring que aquí empieza la aplicación.
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); //Esta es la línea que inicia la aplicación Spring Boot

        //Imprime un mensaje informativo en la consola o terminal una vez que la aplicación ha arrancado correctamente.
        System.out.println("-----------------------------------------------------------------");
        System.out.println("--- Plataforma de Cursos API ha iniciado correctamente.       ---");
        System.out.println("--- Acceso principal: http://localhost:8080/api/              ---");
        System.out.println("-----------------------------------------------------------------");

    /*Esta es la clase principal que arranca toda la API. La anotación `@SpringBootApplication` indica el punto de inicio
    del proyecto, y `SpringApplication.run()` pone en marcha el servidor de Spring Boot. Además, muestra un mensaje en
    consola confirmando que la plataforma está lista para usarse.*/
    }
}

