//INSCRIPCION CONTROLLER
package org.ues.api.plataformacursos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ues.api.plataformacursos.dto.InscripcionDTO;
import org.ues.api.plataformacursos.service.InscripcionService;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones") //Ruta base del controlador,
@RequiredArgsConstructor

public class InscripcionController {

    private final InscripcionService inscripcionService;

    // CREAR inscripción (estudiante se inscribe en un curso)
    // Se usa 201 Created para indicar la creación exitosa de un recurso.
    @PostMapping
    public ResponseEntity<InscripcionDTO> crearInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO nuevaInscripcion = inscripcionService.crearInscripcionDesdeDTO(inscripcionDTO);
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    // OBTENER inscripción por ID (método estándar añadido)
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtenerInscripcionPorId(
            @PathVariable Long id) {
        InscripcionDTO inscripcion = inscripcionService.obtenerInscripcionPorId(id);
        return ResponseEntity.ok(inscripcion); // Retorna 200 OK
    }

    // LISTAR todas las inscripciones
    // Devuelve DTOs.
    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.listarInscripciones()); // Retorna 200 OK
    }

    // CANCELAR inscripción (actualiza el estado)
    // Devuelve el objeto actualizado.
    // Retorna 200 OK
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<InscripcionDTO> cancelarInscripcion(
            @PathVariable Long id) {
        // El servicio maneja la lógica de negocio (cambiar estado a CANCELADA)
        InscripcionDTO inscripcionActualizada = inscripcionService.cancelarInscripcion(id);
        return ResponseEntity.ok(inscripcionActualizada);
    }

    // ELIMINAR inscripción (para eliminar físicamente, no solo cancelar)
    // Retorna 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}