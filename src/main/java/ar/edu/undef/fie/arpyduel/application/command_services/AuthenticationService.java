package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindUserCommandQuery;
import ar.edu.undef.fie.arpyduel.security.JwtService;
import ar.edu.undef.fie.arpyduel.interfaces.requests.AuthenticationRequest;
import ar.edu.undef.fie.arpyduel.interfaces.requests.RegisterRequest;
import ar.edu.undef.fie.arpyduel.interfaces.responses.AuthenticationResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserCommandService userService;
    private final JwtService jwtService;
    private final FindUserCommandQuery findUserCommandQuery;

    public AuthenticationService(UserCommandService userService, JwtService jwtService, FindUserCommandQuery findUserCommandQuery) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.findUserCommandQuery = findUserCommandQuery;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = userService.create(request);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var user = findUserCommandQuery.getByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
