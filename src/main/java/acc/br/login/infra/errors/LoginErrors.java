package acc.br.login.infra.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class LoginErrors {
    public static class LoginError extends Exception {
        public LoginError(String message) {
            super(message);
        }
    }
}
