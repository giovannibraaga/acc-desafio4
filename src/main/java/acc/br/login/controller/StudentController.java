package acc.br.login.controller;

import acc.br.login.dto.StudentDTO;
import acc.br.login.entities.Student;
import acc.br.login.infra.errors.StudentsErrors.*;
import acc.br.login.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity register(@RequestBody StudentDTO student) throws RegisterError {
        try {
            studentService.registerStudent(student);
            return ResponseEntity.created(new URI("/student/" + student.getId())).build();
        } catch (URISyntaxException e) {
            throw new RegisterError("Erro ao registrar o aluno");
        }
    }

    @PutMapping
    public void update(@RequestParam Student student) throws UpdateStudentError {
        studentService.update(student);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws DeleteStudentError {
        studentService.delete(id);
    }
}

