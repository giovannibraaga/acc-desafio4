package acc.br.login.infra.populator;

import acc.br.login.dto.LoginDTO;
import acc.br.login.entities.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginPopulator {

    public Login toEntity(LoginDTO loginDTO) {
        return new Login(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        );
    }

    public LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getUsername(),
                login.getPassword()
        );
    }
}
