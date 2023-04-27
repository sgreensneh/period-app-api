package africa.semicolon.periodapp.services;

import africa.semicolon.periodapp.data.models.User;
import africa.semicolon.periodapp.dto.requests.LoginRequestDto;
import africa.semicolon.periodapp.dto.requests.SignupRequestDto;
import africa.semicolon.periodapp.dto.response.TokenResponseDto;
import africa.semicolon.periodapp.dto.response.UserDto;

public interface UserService {

    TokenResponseDto createAccount(SignupRequestDto requestDto);

    TokenResponseDto login(LoginRequestDto requestDto);

    User getAuthenticatedUser();

    UserDto getUserDetails();

}
