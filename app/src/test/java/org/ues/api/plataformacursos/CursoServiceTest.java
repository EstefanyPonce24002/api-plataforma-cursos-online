package org.ues.api.plataformacursos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ues.api.plataformacursos.dto.CursoDTO;
import org.ues.api.plataformacursos.model.Curso;
import org.ues.api.plataformacursos.model.Instructor;
import org.ues.api.plataformacursos.repository.CursoRepository;
import org.ues.api.plataformacursos.repository.InstructorRepository;
import org.ues.api.plataformacursos.service.CursoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepo;

    // Simula el repositorio de instructores
    @Mock
    private InstructorRepository instructorRepo;

    // Inyecta los mocks en la clase que queremos probar
    @InjectMocks
    private CursoService cursoService;

    //Comprueba que crear un curso funciona y devuelve la información correcta del instructor y del título
    @Test
    void crearCurso_deberiaGuardarYRetornarDTO() {
        // 1. Preparación del DTO de entrada (lo que recibiría el servicio)
        CursoDTO dto = new CursoDTO();
        dto.setTitulo("BD Avanzada");
        dto.setDescripcion("Curso sobre PostgreSQL");
        dto.setEstado("activo");
        dto.setIdInstructor(1L);
        // 2. Instructor simulado (lo que devuelve el repositorio de instructores)
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        // 3. Curso simulado (lo que devuelve el repositorio al guardar)
        Curso curso = new Curso();
        curso.setId(10L);
        curso.setTitulo(dto.getTitulo());
        curso.setDescripcion(dto.getDescripcion());
        curso.setEstado(dto.getEstado());
        curso.setInstructor(instructor);
        // 4. Configuración de mocks: definimos qué deben devolver
        when(instructorRepo.findById(1L)).thenReturn(Optional.of(instructor));
        when(cursoRepo.save(any(Curso.class))).thenReturn(curso);
        // 5. Ejecución del método real
        CursoDTO resultado = cursoService.crearCurso(dto);
        // 6. Verificación: comprobamos que el resultado sea el esperado
        assertEquals("BD Avanzada", resultado.getTitulo()); // El título coincide
        assertEquals(1L, resultado.getIdInstructor());  // El instructor asociado es correcto
    }

    //Comprueba que la búsqueda por ID devuelve un DTO con los datos correctos
    @Test
    void obtenerCursoPorId_deberiaRetornarDTO() {
        Instructor instructor = new Instructor();
        // 1. Instructor simulado
        instructor.setId(1L);
        // 2. Curso simulado (lo que devuelve el repositorio al buscar por ID)
        Curso curso = new Curso();
        curso.setId(10L);
        curso.setTitulo("Java");
        curso.setDescripcion("Intro a Java");
        curso.setEstado("activo");
        curso.setInstructor(instructor);
        // 3. Configuración del mock: cuando se busca el curso con ID 10, devuelve el curso simulado
        when(cursoRepo.findById(10L)).thenReturn(Optional.of(curso));
        // 4. Ejecución del método real
        CursoDTO resultado = cursoService.obtenerCursoPorId(10L);
        // 5. Verificación: comprobamos que el DTO tenga los datos correctos
        assertEquals("Java", resultado.getTitulo());
        assertEquals(1L, resultado.getIdInstructor());
    }
}
