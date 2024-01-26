package com.seif.SpringSecurity.service.Impl;

import com.seif.SpringSecurity.dto.JwtAuthenticationResponse;
import com.seif.SpringSecurity.dto.RefreshTokenRequest;
import com.seif.SpringSecurity.dto.SignInRequest;
import com.seif.SpringSecurity.dto.SignUpRequest;
import com.seif.SpringSecurity.entities.Role;
import com.seif.SpringSecurity.entities.User;
import com.seif.SpringSecurity.repository.UserRepository;
import com.seif.SpringSecurity.service.AuthenticationService;
import com.seif.SpringSecurity.service.JWTService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService  jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }



    public User signup(SignUpRequest signUpRequest){

        User user  = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

       return userRepository.save(user) ;

    }


    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));

      var user=userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("infalid email or password"));
      var  jwt=jwtService.generateToken(user);
      var refreshToken=jwtService.generateRefreshToken(new HashMap<>(), user);


      JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

      jwtAuthenticationResponse.setToken(jwt);
      jwtAuthenticationResponse.setRefreshToken(refreshToken);
      return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){

        String userEmail= jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse.;





        }



return null;

    }

















}
