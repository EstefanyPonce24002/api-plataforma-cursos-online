package org.ues.api.plataformacursos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ues.api.plataformacursos.dto.EstudianteDTO;
import org.ues.api.plataformacursos.model.Estudiante;
import org.ues.api.plataformacursos.repository.EstudianteRepository;
import org.ues.api.plataformacursos.exception.NotFoundException;
import org.ues.api.plataformacursos.model.Curso; // Importación necesaria para mapear CursosIds

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    private EstudianteDTO convertirADTO(Estudiante estudiante) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setEmail(estudiante.getEmail());
        dto.setContrasena(estudiante.getContrasena());
        //dto.setTipoUsuario(estudiante.getTipoUsuario());

        return dto;
    }

    // MÉTODOS CRUD
    //Listar todos los estudiantes registrados
    @Transactional(readOnly = true) // Optimización para métodos de solo lectura
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener un estudiante por su ID (devuelve DTO).
    @Transactional(readOnly = true)
    public EstudianteDTO obtenerEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + id));
        return convertirADTO(estudiante);
    }

    //Crear un nuevo estudiante a partir del DTO (devuelve DTO).
    public EstudianteDTO crearEstudiante(EstudianteDTO dto) {
        // Validación de email duplicado (opcional, pero buena práctica)
        if (estudianteRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("El email ya está registrado.");
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        // Nota: Se toma la contraseña del DTO de entrada.
        estudiante.setContrasena(dto.getContrasena());


        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);
        return convertirADTO(estudianteGuardado);
    }

    //Actualizar los datos de un estudiante existente (devuelve DTO).
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + id));

        estudiante.setNombre(dto.getNombre());
        // Se asume que el cambio de email también puede ser una operación sensible
        estudiante.setEmail(dto.getEmail());

        // Si se envió una nueva contraseña en el DTO, la actualizamos
        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            estudiante.setContrasena(dto.getContrasena());
        }

        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return convertirADTO(estudianteActualizado);
    }

    // Eliminar un estudiante por su ID.
    public void eliminarEstudiante(Long id) {
        // Usamos findById para verificar la existencia antes de eliminar,
        if (!estudianteRepository.existsById(id)) {
            throw new NotFoundException("Estudiante no encontrado con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    // MÉTODOS DE BÚSQUEDA ADICIONALES
    //Implementación del método de búsqueda por email (devuelve DTO).
    @Transactional(readOnly = true)
    public EstudianteDTO buscarPorEmail(String email) {
        Estudiante estudiante = estudianteRepository.findByEmail(email);

        if (estudiante == null) {
            throw new NotFoundException("Estudiante no encontrado con email: " + email);
        }
        return convertirADTO(estudiante);
    }
}