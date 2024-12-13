package acc.br.login.infra.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CursoErrors {
    public static class RegisterError extends Exception {
        public RegisterError(String message) {
            super(message);
        }
    }
}
