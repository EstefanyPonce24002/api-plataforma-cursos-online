
package org.ues.api.plataformacursos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ues.api.plataformacursos.dto.InscripcionDTO;
import org.ues.api.plataformacursos.exception.NotFoundException;
import org.ues.api.plataformacursos.model.Curso;
import org.ues.api.plataformacursos.model.Estudiante;
import org.ues.api.plataformacursos.model.Inscripcion;
import org.ues.api.plataformacursos.repository.CursoRepository;
import org.ues.api.plataformacursos.repository.EstudianteRepository;
import org.ues.api.plataformacursos.repository.InscripcionRepository;
import org.ues.api.plataformacursos.service.InscripcionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InscripcionServiceTest {

    @Mock // Simula el repositorio de inscripciones (no accede a BD real)
    private InscripcionRepository inscripcionRepo;

    @Mock // Simula el repositorio de estudiantes
    private EstudianteRepository estudianteRepo;

    @Mock // Simula el repositorio de cursos
    private CursoRepository cursoRepo;

    @InjectMocks // Inyecta los mocks en la clase que queremos probar
    private InscripcionService inscripcionService;

    // Caso de éxito: crear inscripción
    @Test
    void crearInscripcionDesdeDTO_deberiaGuardarYRetornarDTO() {
        // DTO de entrada con datos de estudiante y curso
        InscripcionDTO dto = new InscripcionDTO();
        dto.setIdEstudiante(1L);
        dto.setIdCurso(2L);
        dto.setEstado("ACTIVA");


        // Entidades simuladas (estudiante y curso existentes)
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);

        Curso curso = new Curso();
        curso.setId(2L);

        // Inscripción simulada que devuelve el repositorio al guardar
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(10L);
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("ACTIVA");

        // Configuración de mocks: qué deben devolver los repositorios
        when(estudianteRepo.findById(1L)).thenReturn(Optional.of(estudiante));
        when(cursoRepo.findById(2L)).thenReturn(Optional.of(curso));
        when(inscripcionRepo.save(any(Inscripcion.class))).thenReturn(inscripcion);

        // Ejecución del método real
        InscripcionDTO resultado = inscripcionService.crearInscripcionDesdeDTO(dto);

        // Verificación: comprobamos que el DTO devuelto tenga los datos correctos
        assertEquals(10L, resultado.getId());
        assertEquals(1L, resultado.getIdEstudiante());
        assertEquals(2L, resultado.getIdCurso());
        assertEquals("ACTIVA", resultado.getEstado());
    }

    // Caso de error: estudiante no encontrado
    @Test
    void crearInscripcionDesdeDTO_deberiaLanzarExcepcionSiEstudianteNoExiste() {
        // DTO con estudiante inexistente
        InscripcionDTO dto = new InscripcionDTO();
        dto.setIdEstudiante(99L);
        dto.setIdCurso(2L);

        // Mock: el repositorio devuelve vacío (Optional.empty)
        when(estudianteRepo.findById(99L)).thenReturn(Optional.empty());

        // Verificación: esperamos que se lance NotFoundException
        assertThrows(NotFoundException.class, () -> inscripcionService.crearInscripcionDesdeDTO(dto));
    }

    // Caso de éxito: cancelar inscripción activa
    @Test
    void cancelarInscripcion_deberiaActualizarEstadoACancelada() {
        // Entidades simuladas
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);

        Curso curso = new Curso();
        curso.setId(2L);

        // Inscripción activa
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(10L);
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("ACTIVA");

        // Mock: inscripción encontrada y guardada
        when(inscripcionRepo.findById(10L)).thenReturn(Optional.of(inscripcion));
        when(inscripcionRepo.save(any(Inscripcion.class))).thenReturn(inscripcion);

        // Ejecución
        InscripcionDTO resultado = inscripcionService.cancelarInscripcion(10L);

        // Verificación: el estado debe cambiar a CANCELADA
        assertEquals("CANCELADA", resultado.getEstado());
    }

    // Caso de error: cancelar inscripción no activa
    @Test
    void cancelarInscripcion_deberiaLanzarExcepcionSiNoEstaActiva() {
        // Inscripción simulada con estado distinto de ACTIVA
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(10L);
        inscripcion.setEstado("FINALIZADA");

        // Mock: inscripción encontrada
        when(inscripcionRepo.findById(10L)).thenReturn(Optional.of(inscripcion));

        // Verificación: esperamos IllegalStateException
        assertThrows(IllegalStateException.class, () -> inscripcionService.cancelarInscripcion(10L));
    }
}