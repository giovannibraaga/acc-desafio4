package acc.br.login.security;

import acc.br.login.entities.Login;
import acc.br.login.repositories.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for setting up security rules.
 */
@Configuration
public class SecurityConfig {
    /**
     * Bean for password encoding using BCrypt.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for in-memory user details service with predefined users and roles.
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(LoginRepository loginRepository) {
        return username -> {
            Login userLogin = loginRepository.findByUsername(username);
            if (userLogin == null) {
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }

            return User.withUsername(userLogin.getUsername())
                    .password(userLogin.getPassword())
                    .roles("USER")
                    .build();
        };
    }


    /**
     * Bean for configuring HTTP security with authorization and authentication rules.
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/cursos/listar", "/studentRegistration", "/student", "/public/**", "/h2-console/**", "/login", "/").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/cursos/listar", "studentRegistration", "/student", "/login", "/")
                )
                .headers(headers -> headers
                        .frameOptions().disable(
                        ));

        return http.build();
    }

}