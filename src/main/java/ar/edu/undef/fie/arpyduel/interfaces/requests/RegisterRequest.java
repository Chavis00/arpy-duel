package ar.edu.undef.fie.arpyduel.interfaces.requests;

import ar.edu.undef.fie.arpyduel.interfaces.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
