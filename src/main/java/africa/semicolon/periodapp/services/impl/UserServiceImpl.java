package africa.semicolon.periodapp.services.impl;

import africa.semicolon.periodapp.data.models.Flow;
import africa.semicolon.periodapp.data.models.User;
import africa.semicolon.periodapp.data.repositories.UserRepository;
import africa.semicolon.periodapp.dto.requests.LoginRequestDto;
import africa.semicolon.periodapp.dto.requests.SignupRequestDto;
import africa.semicolon.periodapp.dto.response.TokenResponseDto;
import africa.semicolon.periodapp.dto.response.UserDto;
import africa.semicolon.periodapp.exceptions.user.InvalidLoginDetailsException;
import africa.semicolon.periodapp.exceptions.user.UnauthorizedRequestException;
import africa.semicolon.periodapp.exceptions.user.UsernameAlreadyUsedException;
import africa.semicolon.periodapp.security.AuthenticatedUser;
import africa.semicolon.periodapp.security.JwtGenerator;
import africa.semicolon.periodapp.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;


    @Override
    public TokenResponseDto createAccount(SignupRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new UsernameAlreadyUsedException();
        }

        Flow flow = Flow.builder()
                .cycleLength(requestDto.getCycleLength())
                .lastFlow(requestDto.getFirstFlow())
                .flowLength(requestDto.getFlowLength())
                .build();

        User user = User.builder()
                .fullName(requestDto.getFullName())
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .flow(flow)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtGenerator.generateToken(savedUser);
        return TokenResponseDto.builder().token("Bearer " + token).build();
    }

    @Override
    public TokenResponseDto login(LoginRequestDto requestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
                            requestDto.getPassword())
            );
            String token = jwtGenerator.generateToken(authentication);
            return TokenResponseDto.builder().token("Bearer " + token).build();
        } catch (Exception e) {
            throw new InvalidLoginDetailsException();
        }
    }

    @Override
    public User getAuthenticatedUser() {
        try {
            AuthenticatedUser authUser =
                    (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authUser == null) {
                throw new UnauthorizedRequestException();
            }
            return userRepository.findByUsername(authUser.getUsername())
                    .orElseThrow(UnauthorizedRequestException::new);
        } catch (Exception e) {
            throw new UnauthorizedRequestException();
        }
    }

    @Override
    public UserDto getUserDetails() {
        User user = this.getAuthenticatedUser();
        return mapper.map(user, UserDto.class);
    }
}
