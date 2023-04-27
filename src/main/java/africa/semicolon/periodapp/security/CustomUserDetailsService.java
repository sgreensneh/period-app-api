package africa.semicolon.periodapp.security;

import africa.semicolon.periodapp.data.models.User;
import africa.semicolon.periodapp.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return AuthenticatedUser.builder()
                .user(user)
                .roles(List.of("ROLE_USER"))
                .build();
    }
}
