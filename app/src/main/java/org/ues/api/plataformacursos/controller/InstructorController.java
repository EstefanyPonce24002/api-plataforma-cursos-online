package org.ues.api.plataformacursos.controller;

import org.ues.api.plataformacursos.dto.InstructorDTO;
import org.ues.api.plataformacursos.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // CREAR un nuevo instructor
    @PostMapping
    public ResponseEntity<InstructorDTO> crearInstructor(@Valid @RequestBody InstructorDTO dto) {
        InstructorDTO nuevoInstructor = instructorService.crearInstructor(dto);
        // Retorna 201 CREATED
        return new ResponseEntity<>(nuevoInstructor, HttpStatus.CREATED);
    }

    // OBTENER instructor por ID
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> obtenerInstructorPorId(@PathVariable Long id) {
        InstructorDTO instructor = instructorService.obtenerInstructorPorId(id);
        return ResponseEntity.ok(instructor); // Retorna 200 OK
    }

    // LISTAR todos los instructores y BUSCAR por email (Endpoint unificado)
    // Permite: GET /api/instructores o GET /api/instructores?email=profesor@ues.sv
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> listarInstructores(
            @RequestParam(required = false) String email) {

        List<InstructorDTO> instructores;

        if (email != null && !email.isEmpty()) {
            // Si se proporciona email, busca por email
            InstructorDTO instructor = instructorService.buscarPorEmail(email);
            // Envuelve el resultado en una lista para mantener la consistencia del endpoint
            instructores = List.of(instructor);
        } else {
            // Si no hay parámetros, lista todos
            instructores = instructorService.listarInstructores();
        }

        return ResponseEntity.ok(instructores); // Retorna 200 OK
    }

    // ACTUALIZAR instructor existente
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> actualizarInstructor(@PathVariable Long id,
                                                              @Valid @RequestBody InstructorDTO dto) {
        InstructorDTO instructorActualizado = instructorService.actualizarInstructor(id, dto);
        return ResponseEntity.ok(instructorActualizado); // Retorna 200 OK
    }

    // ELIMINAR instructor
    // Devuelve 204 No Content.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInstructor(@PathVariable Long id) {
        instructorService.eliminarInstructor(id);
        return ResponseEntity.noContent().build(); // Retorna 204 NO CONTENT
    }
}