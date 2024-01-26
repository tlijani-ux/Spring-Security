package com.seif.SpringSecurity.service;

import com.seif.SpringSecurity.dto.JwtAuthenticationResponse;
import com.seif.SpringSecurity.dto.RefreshTokenRequest;
import com.seif.SpringSecurity.dto.SignInRequest;
import com.seif.SpringSecurity.dto.SignUpRequest;
import com.seif.SpringSecurity.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
