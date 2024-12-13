package acc.br.login.infra.config;

import acc.br.login.entities.Curso;
import acc.br.login.repositories.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursoConfig {
    @Bean
    public CommandLineRunner populateDatabase(CursoRepository cursoRepository) {
        return args -> {
            Curso curso1 = new Curso();
            curso1.setName("Spring MVC");
            curso1.setDescription("Aprenda Spring MVC do zero.");

            Curso curso2 = new Curso();
            curso2.setName("SAP Commerce");
            curso2.setDescription("Aprenda SAP Commerce");

            cursoRepository.save(curso1);
            cursoRepository.save(curso2);

            System.out.println("Banco de dados populado com os cursos iniciais.");
        };
    }
}