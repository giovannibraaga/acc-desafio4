package acc.br.login.services;

import acc.br.login.dto.CursoDTO;
import acc.br.login.entities.Curso;
import acc.br.login.infra.errors.CursoErrors;
import acc.br.login.infra.populator.CursoPopulator;
import acc.br.login.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import acc.br.login.infra.errors.CursoErrors.RegisterError;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoPopulator cursoPopulator;

    @Transactional
    public void register(Curso curso) throws RegisterError {
        if (curso.getName() == null || curso.getName().isEmpty()) {
            throw new RegisterError("O nome do curso é obrigatório.");
        }

        if (curso.getDescription() == null || curso.getDescription().isEmpty()) {
            throw new RegisterError("A descrição do curso é obrigatória.");
        }

        try {
            cursoRepository.save(curso);
        } catch (Exception e) {
            throw new RegisterError("Erro ao registrar um novo curso");
        }
    }

    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(cursoPopulator::toDTO)
                .collect(Collectors.toList());
    }
}
