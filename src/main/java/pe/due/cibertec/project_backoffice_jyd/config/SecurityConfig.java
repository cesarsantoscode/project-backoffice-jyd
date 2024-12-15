package pe.due.cibertec.project_backoffice_jyd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // definir rutas protegidas y quien puede acceder a ellas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/manage/login").permitAll() // establecer rutas publicas
                        .requestMatchers("/manage/start").hasAnyRole("ADMIN", "OPERATOR") // acceso para ADMIN y OPERATOR
                        .requestMatchers("/manage/add").hasAnyRole("ADMIN") // acceso para ADMIN
                        .anyRequest().authenticated() // el resto debe autenticarse
                )

                // redireccionar a una pagina de error en caso no tenga permisos
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((req, rsp, e) -> {
                            rsp.sendRedirect("/manage/restricted");
                        })
                )

                // configurar el formulario de inicio de sesion
                .formLogin(form -> form
                        .loginPage("/manage/login")
                        .defaultSuccessUrl("/manage/start", false)
                        .permitAll()
                )

                // configurar el formulario de cerrar sesion
                .logout(logout -> logout
                        .logoutUrl("/manage/logout")
                        .logoutSuccessUrl("/manage/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> User.builder()
//                .username("pepito")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ADMIN")
//                .build();
//    }

}
