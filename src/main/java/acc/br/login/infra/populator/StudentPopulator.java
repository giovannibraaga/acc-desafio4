package acc.br.login.infra.populator;

import acc.br.login.dto.StudentDTO;
import acc.br.login.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentPopulator {

    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setCep(studentDTO.getCep());
        student.setCidade(studentDTO.getCidade());
        student.setCurso(studentDTO.getCurso());
        return student;
    }

    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setCep(student.getCep());
        studentDTO.setCidade(student.getCidade());
        studentDTO.setCurso(student.getCurso());

        return studentDTO;
    }
}
