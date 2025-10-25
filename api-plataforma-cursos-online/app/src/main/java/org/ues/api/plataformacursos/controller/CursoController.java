package org.ues.api.plataformacursos.controller;

import org.ues.api.plataformacursos.dto.CursoDTO;
import org.ues.api.plataformacursos.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; //validar automáticamente los datos recibidos

import java.util.List;

@RestController         //Indica que esta clase responde a peticiones HTTP y devuelve datos en formato JSON
@RequestMapping("/api/cursos") //Define la ruta base para todas las operaciones relacionadas con cursos.
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // CREAR un nuevo curso
    // Se usa @Valid para activar las validaciones del DTO.
    // Devuelve 201 Created para indicar que el recurso fue creado exitosamente.
    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@Valid @RequestBody CursoDTO dto) {
        CursoDTO nuevoCurso = cursoService.crearCurso(dto);

        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // OBTENER un curso por ID
    // Método necesario para obtener un curso individualmente (GET /api/cursos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerCursoPorId(@PathVariable Long id) {
        CursoDTO curso = cursoService.obtenerCursoPorId(id);
        return ResponseEntity.ok(curso); // Retorna 200 OK
    }

    // LISTAR y BUSCAR cursos (Endpoint unificado)
    // Este endpoint maneja: GET /api/cursos, ?idInstructor=X, ?titulo=Y, ? Estado=Z
    @GetMapping
    public ResponseEntity<List<CursoDTO>> obtenerCursos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long idInstructor) {

        List<CursoDTO> cursos;

        // Se usa la lógica de filtrado implementada en el Service
        if (titulo != null && !titulo.isEmpty()) {
            cursos = cursoService.buscarPorTitulo(titulo);
        } else if (estado != null && !estado.isEmpty()) {
            cursos = cursoService.buscarPorEstado(estado);
        } else if (idInstructor != null) {
            // Listar cursos por instructor
            cursos = cursoService.listarCursosPorInstructor(idInstructor);
        } else {
            // Si no hay parámetros de búsqueda, lista todos
            cursos = cursoService.listarCursos();
        }

        if (cursos.isEmpty()) {
            return ResponseEntity.ok(cursos);
        }

        return ResponseEntity.ok(cursos);
    }


    // ACTUALIZAR curso existente
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoDTO dto) {
        CursoDTO cursoActualizado = cursoService.actualizarCurso(id, dto);
        return ResponseEntity.ok(cursoActualizado); // Retorna 200 OK
    }

    // ELIMINAR curso
    // Devuelve 204 No Content para indicar una eliminación exitosa sin cuerpo de respuesta.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}