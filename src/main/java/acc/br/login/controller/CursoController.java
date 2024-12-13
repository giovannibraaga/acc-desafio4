package acc.br.login.controller;

import acc.br.login.dto.CursoDTO;
import acc.br.login.entities.Curso;
import acc.br.login.infra.errors.CursoErrors;
import acc.br.login.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping("/criar")
    public void register(Curso curso) throws CursoErrors.RegisterError {
        cursoService.register(curso);
    }

    @GetMapping("/listar")
    public List<CursoDTO> listaDeCursos() {
        return cursoService.listarCursos();
    }
}
