package acc.br.login.infra.config;

import acc.br.login.entities.Login;
import acc.br.login.repositories.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoginConfig {

    @Bean
    public CommandLineRunner initializeAdmin(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (loginRepository.findByUsername("admin") == null) {
                Login admin = new Login();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                loginRepository.save(admin);
            }
        };
    }

}
