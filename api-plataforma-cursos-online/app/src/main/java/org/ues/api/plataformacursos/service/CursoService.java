package org.ues.api.plataformacursos.service;

import org.ues.api.plataformacursos.dto.CursoDTO;
import org.ues.api.plataformacursos.model.Curso;
import org.ues.api.plataformacursos.model.Instructor;
import org.ues.api.plataformacursos.repository.CursoRepository;
import org.ues.api.plataformacursos.repository.InstructorRepository;
//Se implementa la lógica de negocio (entre el controlador y el repositorio).
import org.springframework.stereotype.Service;
//Asegura que las operaciones con la base de datos se ejecuten dentro de una transacción
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors; // Se usa junto con las Streams para transformar o recopilar datos

@Service
@Transactional
// Contiene la lógica de negocio relacionada con los cursos.
public class CursoService {

    private final CursoRepository cursoRepo; // Accede a los datos de los cursos
    private final InstructorRepository instructorRepo; // Permite validar o buscar instructores asociados.

    public CursoService(CursoRepository cursoRepo, InstructorRepository instructorRepo) {
        this.cursoRepo = cursoRepo;
        this.instructorRepo = instructorRepo;
    }

    // MÉTODOS DE MAPEO (Entidad <-> DTO)
    //Convierte una entidad Curso a su DTO correspondiente.
    private CursoDTO convertirADTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setTitulo(curso.getTitulo());
        dto.setDescripcion(curso.getDescripcion());
        dto.setEstado(curso.getEstado());
        // Se asume que la relación 'instructor' existe y no es null
        dto.setIdInstructor(curso.getInstructor().getId());
        return dto;
    }

    // MÉTODOS CRUD
    // Registra un nuevo curso
    public CursoDTO crearCurso(CursoDTO dto) {
        // Busca al instructor antes de crear el curso
        Instructor instructor = instructorRepo.findById(dto.getIdInstructor())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + dto.getIdInstructor()));

        // Mapeo DTO -> Entidad
        Curso curso = new Curso();
        curso.setTitulo(dto.getTitulo());
        curso.setDescripcion(dto.getDescripcion());
        curso.setEstado(dto.getEstado());
        curso.setInstructor(instructor);

        // Guardar y devolver el resultado mapeado a DTO
        Curso cursoGuardado = cursoRepo.save(curso);
        return convertirADTO(cursoGuardado);
    }

    // Busca un curso específico por su ID
    public CursoDTO obtenerCursoPorId(Long id) {
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return convertirADTO(curso);
    }

    // Devuelve todos los cursos registrados.
    public List<CursoDTO> listarCursos() {
        // Usa Stream para mapear la lista de Entidades a lista de DTOs
        return cursoRepo.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CursoDTO actualizarCurso(Long id, CursoDTO dto) {
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));

        // Actualiza las propiedades
        curso.setTitulo(dto.getTitulo());
        curso.setDescripcion(dto.getDescripcion());
        curso.setEstado(dto.getEstado());

        // Opcional: Si el DTO incluye un ID de Instructor diferente, actualizar la relación
        if (dto.getIdInstructor() != null && !dto.getIdInstructor().equals(curso.getInstructor().getId())) {
            Instructor nuevoInstructor = instructorRepo.findById(dto.getIdInstructor())
                    .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            curso.setInstructor(nuevoInstructor);
        }

        Curso cursoActualizado = cursoRepo.save(curso);
        return convertirADTO(cursoActualizado);
    }
    // Elimina un curso por ID
    public void eliminarCurso(Long id) {
        cursoRepo.deleteById(id);
    }

    // MÉTODOS DE BÚSQUEDA
    public List<CursoDTO> listarCursosPorInstructor(Long instructorId) {
        return cursoRepo.findByInstructorId(instructorId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Implementación del nuevo método de búsqueda por título
    public List<CursoDTO> buscarPorTitulo(String titulo) {
        return cursoRepo.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    //Implementación del nuevo método de búsqueda por estado.
    public List<CursoDTO> buscarPorEstado(String estado) {
        return cursoRepo.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}