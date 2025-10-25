package org.ues.api.plataformacursos.service;

import org.ues.api.plataformacursos.dto.InstructorDTO;
import org.ues.api.plataformacursos.model.Instructor;
import org.ues.api.plataformacursos.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importación necesaria

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Recomendado para asegurar la atomicidad de las operaciones de DB
public class InstructorService {

    private final InstructorRepository instructorRepo;
    // Opcional: Podrías necesitar el CursoRepository si manejas la lista de cursos en el DTO
    // private final CursoRepository cursoRepo;

    public InstructorService(InstructorRepository instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    // ===========================================
    // MÉTODOS DE MAPEO (Entidad <-> DTO)
    // ===========================================

    /**
     * Convierte una entidad Instructor a su DTO correspondiente.
     */
    private InstructorDTO convertirADTO(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setId(instructor.getId());
        dto.setNombre(instructor.getNombre());
        dto.setContrasena(instructor.getContrasena());
        dto.setEmail(instructor.getEmail());

        // Opcional: Mapear la lista de cursos si la entidad Instructor los tiene
        // if (instructor.getCursos() != null) {
        //     dto.setCursosIds(instructor.getCursos().stream()
        //             .map(Curso::getId)
        //             .collect(Collectors.toList()));
        // }

        return dto;
    }

    // ===========================================
    // MÉTODOS CRUD (Ahora devuelven DTOs)
    // ===========================================

    /**
     * Crear nuevo instructor (devuelve DTO).
     */
    public InstructorDTO crearInstructor(InstructorDTO dto) {
        // Opcional: Verificar si el email ya existe
        if (instructorRepo.findByNombre(dto.getEmail()) != null) {
            throw new RuntimeException("El email ya está registrado para otro instructor.");
        }

        Instructor instructor = new Instructor();
        instructor.setNombre(dto.getNombre());
        instructor.setContrasena(dto.getContrasena());
        instructor.setEmail(dto.getEmail());

        Instructor instructorGuardado = instructorRepo.save(instructor);
        return convertirADTO(instructorGuardado);
    }

    /**
     * Listar todos los instructores (devuelve DTOs).
     */
    @Transactional(readOnly = true)
    public List<InstructorDTO> listarInstructores() {
        return instructorRepo.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtener un instructor por su ID (devuelve DTO).
     */
    @Transactional(readOnly = true)
    public InstructorDTO obtenerInstructorPorId(Long id) {
        Instructor instructor = instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + id));
        return convertirADTO(instructor);
    }

    /**
     * Actualizar un instructor existente (devuelve DTO).
     */
    public InstructorDTO actualizarInstructor(Long id, InstructorDTO dto) {
        Instructor instructor = instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + id));

        instructor.setNombre(dto.getNombre());
        instructor.setEmail(dto.getEmail());

        Instructor instructorActualizado = instructorRepo.save(instructor);
        return convertirADTO(instructorActualizado);
    }

    /**
     * Eliminar instructor.
     */
    public void eliminarInstructor(Long id) {
        // Se puede usar existsById para manejar mejor la excepción
        if (!instructorRepo.existsById(id)) {
            throw new RuntimeException("Instructor no encontrado con ID: " + id);
        }
        instructorRepo.deleteById(id);
    }

    // ===========================================
    // MÉTODOS DE BÚSQUEDA
    // ===========================================

    /**
     * Implementación del método de búsqueda por email (devuelve DTO).
     */
    @Transactional(readOnly = true)
    public InstructorDTO buscarPorEmail(String email) {
        Instructor instructor = instructorRepo.findByNombre(email);

        if (instructor == null) {
            throw new RuntimeException("Instructor no encontrado con email: " + email);
        }
        return convertirADTO(instructor);
    }
}