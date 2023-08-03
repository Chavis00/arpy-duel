package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindUserCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.UserCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final FindUserCommandQuery userQuery;
    private final UserCommandService userService;

    public UserController(FindUserCommandQuery userQuery, UserCommandService userService) {
        this.userQuery = userQuery;
        this.userService = userService;
    }
    @PostMapping("/friends/{userId}")
    public ResponseEntity<Object> addFriend(@PathVariable Long userId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        userService.addFriend(userId, username);
        return ResponseEntity.ok().build();
    }

}
