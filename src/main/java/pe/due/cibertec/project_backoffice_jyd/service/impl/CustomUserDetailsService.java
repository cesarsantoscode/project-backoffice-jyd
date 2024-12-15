package pe.due.cibertec.project_backoffice_jyd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.due.cibertec.project_backoffice_jyd.entity.User;
import pe.due.cibertec.project_backoffice_jyd.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // consultar usuario por username
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // retornar un UserDetails que sera usado por Spring Security
        User user = optional.get();
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole());

        return userBuilder.build();

    }

}
