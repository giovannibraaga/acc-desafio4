package acc.br.login.services;

import acc.br.login.dto.LoginDTO;
import acc.br.login.dto.StudentDTO;
import acc.br.login.entities.Student;
import acc.br.login.infra.errors.StudentsErrors.*;
import acc.br.login.infra.populator.LoginPopulator;
import acc.br.login.infra.populator.StudentPopulator;
import acc.br.login.repositories.LoginRepository;
import acc.br.login.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginPopulator loginPopulator;

    @Autowired
    private StudentPopulator studentPopulator;

    @Transactional
    public void registerStudent(@RequestBody StudentDTO student) throws RegisterError {
        if (student.getName() == null || student.getUsername() == null || student.getPassword() == null ||
                student.getBirthDate() == null || student.getCep() == null || student.getCidade() == null) {
            throw new RegisterError("Todos os campos devem ser preenchidos");
        }

        try {
            var encodedPassword = passwordEncoder.encode(student.getPassword());
            LoginDTO login = new LoginDTO();
            login.setUsername(student.getUsername());
            System.out.println("Encoded Password: " + encodedPassword);
            login.setPassword(encodedPassword);
            loginRepository.save(loginPopulator.toEntity(login));

            Student newStudent = studentPopulator.toEntity(student);
            newStudent.setPassword(encodedPassword);
            studentRepository.save(newStudent);
        } catch (Exception e) {
            throw new RegisterError("Erro ao registrar aluno");
        }
    }

    @Transactional
    public void update(Student student) throws UpdateStudentError {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new UpdateStudentError("Erro ao atualizar aluno");
        }
    }

    @Transactional
    public void delete(Long id) throws DeleteStudentError {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteStudentError("Erro ao deletar aluno");
        }
    }

}
