package com.wakewithme.api.user.web;

import com.wakewithme.api.common.security.JwtTokenProvider;
import com.wakewithme.api.user.service.api.UserService;
import com.wakewithme.api.user.web.request.LoginRequest;
import com.wakewithme.api.user.web.request.UserDto;
import com.wakewithme.api.user.web.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Operation(
            summary = "Login user",
            description = "Authenticates user and returns JWT token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String token = tokenProvider.generateToken(loginRequest.getEmail());

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .user(userService.getUserByEmail(loginRequest.getEmail()))
                .build());
    }

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account and returns authentication token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDto userDto) {
        var userResponse = userService.registerUser(userDto);
        String token = tokenProvider.generateToken(userDto.getEmail());

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .user(userResponse)
                .build());
    }
}
