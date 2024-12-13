package acc.br.login.services;

import acc.br.login.entities.Curso;
import acc.br.login.infra.errors.CursoErrors;
import acc.br.login.repositories.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CursoServiceTest {
    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeCadastroCerto() {
        Curso curso = new Curso();
        curso.setName("Spring MVC");
        curso.setDescription("Curso Spring MVC Accenture");

        assertDoesNotThrow(() -> cursoService.register(curso));
        verify(cursoRepository, times(1)).save(curso);
    }

    @Test
    void testeCadastroErroNome() {
        Curso curso = new Curso();
        curso.setDescription("Curso de SAP Commerce Accenture");

        CursoErrors.RegisterError exception = assertThrows(CursoErrors.RegisterError.class, () -> {
            cursoService.register(curso);
        });

        assertEquals("O nome do curso é obrigatório.", exception.getMessage());

        verify(cursoRepository, times(0)).save(any());
    }

    @Test
    void testeCadastroErroDescricao() {
        Curso curso = new Curso();
        curso.setName("SAP Commerce");

        CursoErrors.RegisterError exception = assertThrows(CursoErrors.RegisterError.class, () -> {
            cursoService.register(curso);
        });

        assertEquals("A descrição do curso é obrigatória.", exception.getMessage());

        verify(cursoRepository, times(0)).save(any());
    }
}