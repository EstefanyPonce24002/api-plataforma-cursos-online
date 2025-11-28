package org.ues.api.plataformacursos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor; //Inyecta dependencias sin escribir el constructor manualmente
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ues.api.plataformacursos.dto.EstudianteDTO;
import org.ues.api.plataformacursos.service.EstudianteService;

import java.util.List;

/*Este controlador expone los endpoints REST para gestionar estudiantes: crear, listar, buscar por email, obtener por ID,
 actualizar y eliminar. Recibe y devuelve datos mediante EstudianteDTO y responde con los estados HTTP correctos.
 Usa @RequiredArgsConstructor para inyectar automáticamente el servicio que realiza la lógica de negocio.*/

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    // CREAR un nuevo estudiante
    // Devuelve EstudianteDTO y usa 201 Created.
    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteDTO dto) {
        EstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(dto);

        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    // LISTAR todos los estudiantes y BUSCAR por email
    // Unifica el listado y la búsqueda.
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes(
            @RequestParam(required = false) String email) {

        List<EstudianteDTO> estudiantes;

        if (email != null && !email.isEmpty()) {
            // Si se proporciona email, busca por email y devuelve una lista con 0 o 1 elemento
            EstudianteDTO estudiante = estudianteService.buscarPorEmail(email);
            estudiantes = List.of(estudiante);
        } else {
            // Si no hay parámetros, lista todos los estudiantes (devuelve DTOs)
            estudiantes = estudianteService.listarEstudiantes();
        }

        return ResponseEntity.ok(estudiantes); // Retorna 200 OK
    }

    // OBTENER un estudiante específico por su ID
    // Devuelve EstudianteDTO y usa 200 OK.
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiante(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerEstudiante(id);
        return ResponseEntity.ok(estudiante);
    }

    // ACTUALIZAR los datos de un estudiante existente
    // Devuelve EstudianteDTO y usa 200 OK.
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id,
                                                              @Valid @RequestBody EstudianteDTO dto) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, dto);
        return ResponseEntity.ok(estudianteActualizado);
    }

    // ELIMINAR un estudiante por su ID
    // Devuelve 204 No Content para indicar éxito sin cuerpo de respuesta.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}