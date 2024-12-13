package acc.br.login.infra.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class StudentsErrors {

    public static class RegisterError extends Exception {
        public RegisterError(String message) {
            super(message);
        }
    }

    public static class LoginError extends Exception {
        public LoginError(String message) {
            super(message);
        }
    }

    public static class UpdateStudentError extends Exception {
        public UpdateStudentError(String message) {
            super(message);
        }
    }

    public static class DeleteStudentError extends Exception {
        public DeleteStudentError(String message) {
            super(message);
        }
    }
}
