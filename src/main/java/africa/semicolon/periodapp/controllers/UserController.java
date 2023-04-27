package africa.semicolon.periodapp.controllers;

import africa.semicolon.periodapp.dto.requests.LoginRequestDto;
import africa.semicolon.periodapp.dto.requests.SignupRequestDto;
import africa.semicolon.periodapp.dto.response.TokenResponseDto;
import africa.semicolon.periodapp.dto.response.UserDto;
import africa.semicolon.periodapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Create new account")
    public ResponseEntity<TokenResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        return ResponseEntity.ok(userService.createAccount(requestDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }

    @GetMapping("/details")
    @Operation(summary = "Get user details")
    public ResponseEntity<UserDto> getUserDetails() {
        return ResponseEntity.ok(userService.getUserDetails());
    }
}
