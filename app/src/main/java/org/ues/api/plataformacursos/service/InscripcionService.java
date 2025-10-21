package org.ues.api.plataformacursos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ues.api.plataformacursos.dto.InscripcionDTO; // Importación del DTO
import org.ues.api.plataformacursos.model.*;
import org.ues.api.plataformacursos.repository.*;
import org.ues.api.plataformacursos.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // Aplica transaccionalidad a todas las operaciones
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    // ===========================================
    // MÉTODOS DE MAPEO (Entidad -> DTO)
    // ===========================================

    /**
     * Convierte una entidad Inscripcion a su DTO.
     */
    private InscripcionDTO convertirADTO(Inscripcion inscripcion) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setId(inscripcion.getId());
        dto.setEstado(inscripcion.getEstado());
        // Se asume que Estudiante y Curso existen en la Inscripción
        dto.setIdEstudiante(inscripcion.getEstudiante().getId());
        dto.setIdCurso(inscripcion.getCurso().getId());

        // Si tienes campos de enriquecimiento en el DTO (como nombre/título), los mapeas aquí.

        return dto;
    }

    // ===========================================
    // OPERACIONES (Ahora devuelven DTOs)
    // ===========================================

    /**
     * Crear una nueva inscripción y devolver su DTO.
     */
    public InscripcionDTO crearInscripcion(Long idEstudiante, Long idCurso) {
        // Validación de existencia
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + idEstudiante));

        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado con id: " + idCurso));

        // Lógica de Negocio: Opcional, pero esencial: verificar si ya está inscrito
        // if (inscripcionRepository.existsByEstudianteIdAndCursoId(idEstudiante, idCurso)) {
        //     throw new IllegalStateException("El estudiante ya está inscrito en este curso.");
        // }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("ACTIVA"); // Estado inicial

        Inscripcion inscripcionGuardada = inscripcionRepository.save(inscripcion);
        return convertirADTO(inscripcionGuardada);
    }

    /**
     * Obtener una inscripción por su ID.
     */
    @Transactional(readOnly = true)
    public InscripcionDTO obtenerInscripcionPorId(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada con id: " + id));
        return convertirADTO(inscripcion);
    }

    /**
     * Listar todas las inscripciones (devuelve DTOs).
     */
    @Transactional(readOnly = true)
    public List<InscripcionDTO> listarInscripciones() {
        return inscripcionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Cancelar una inscripción (devuelve DTO del objeto actualizado).
     */
    public InscripcionDTO cancelarInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada con id: " + id));

        // Lógica de Negocio: Solo permite cancelar si está ACTIVA
        if (!"ACTIVA".equals(inscripcion.getEstado())) {
            throw new IllegalStateException("Solo se pueden cancelar inscripciones ACTIVA.");
        }

        inscripcion.setEstado("CANCELADA");

        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);
        return convertirADTO(inscripcionActualizada);
    }

    /**
     * Opcional: Eliminar físicamente una inscripción (si la cancelación no es suficiente).
     */
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new NotFoundException("Inscripción no encontrada con id: " + id);
        }
        inscripcionRepository.deleteById(id);
    }
}