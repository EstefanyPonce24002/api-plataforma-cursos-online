package org.ues.api.plataformacursos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ues.api.plataformacursos.dto.EstudianteDTO;
import org.ues.api.plataformacursos.model.Estudiante;
import org.ues.api.plataformacursos.repository.EstudianteRepository;
import org.ues.api.plataformacursos.service.EstudianteService;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {
    // Simula el repositorio de cursos (no accede a BD real)
    @Mock
    private EstudianteRepository estudianteRepo;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void crearEstudiante_deberiaGuardarYRetornarDTO() {
        // 1. DTO de entrada (lo que recibe el servicio)
        EstudianteDTO dto = new EstudianteDTO();
        dto.setNombre("Ana");
        dto.setEmail("ana@ues.edu.sv");
        dto.setContrasena("123");

        when(estudianteRepo.findByEmail("ana@ues.edu.sv")).thenReturn(null);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Ana");
        estudiante.setEmail("ana@ues.edu.sv");
        estudiante.setContrasena("123");
        // 2. Configuración de mocks:
        // - El repositorio indica que no existe otro estudiante con ese email
        when(estudianteRepo.save(any(Estudiante.class))).thenReturn(estudiante);

        EstudianteDTO resultado = estudianteService.crearEstudiante(dto);

        assertEquals("Ana", resultado.getNombre());
        assertEquals("ana@ues.edu.sv", resultado.getEmail());
    }

    @Test
    void obtenerEstudiante_deberiaRetornarDTO() {
        // - Simulamos el estudiante que se guardará en BD
        Estudiante estudiante = new Estudiante();
        estudiante.setId(2L);
        estudiante.setNombre("Carlos");
        estudiante.setEmail("carlos@ues.edu.sv");
        estudiante.setContrasena("abc");
        // - Cuando se guarda, devuelve el estudiante simulado
        when(estudianteRepo.findById(2L)).thenReturn(Optional.of(estudiante));
        // 3. Ejecución del método real
        EstudianteDTO resultado = estudianteService.obtenerEstudiante(2L);
        // 4. Verificación: comprobamos que el DTO devuelto tenga los datos correctos
        assertEquals("Carlos", resultado.getNombre());// Nombre correcto
        assertEquals("carlos@ues.edu.sv", resultado.getEmail()); // Email correcto
    }
}