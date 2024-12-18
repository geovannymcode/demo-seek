package com.geovannycode.infraestructure.Controller.impl;

import com.geovannycode.infraestructure.Controller.AuthController;
import com.geovannycode.infraestructure.request.LoginRequest;
import com.geovannycode.infraestructure.utils.JWTTokenGenerator;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

    @Value("${jwt.user}")
    private String user;

    @Value("${jwt.pass}")
    private String pass;

    private final JWTTokenGenerator jwtTokenGenerator;

    public AuthControllerImpl(JWTTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginRequest credentials) {
        return Optional.ofNullable(credentials)
                .filter(creds -> user.equals(creds.username()) && pass.equals(creds.password()))
                .map(creds -> jwtTokenGenerator.getJWTToken(creds.username()))
                .map(token -> ResponseEntity.ok(Map.of("token", token)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Username or password incorrect")));
    }
}
