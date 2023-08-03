package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindUserCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.account.Role;
import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.exception.badrequest.UserAlreadyExistsException;
import ar.edu.undef.fie.arpyduel.infrastructure.UserRepository;
import ar.edu.undef.fie.arpyduel.interfaces.requests.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    private final UserRepository repository;
    private final FindUserCommandQuery userQuery;
    private final PasswordEncoder passwordEncoder;

    public UserCommandService(UserRepository repository, FindUserCommandQuery userQuery, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userQuery = userQuery;
        this.passwordEncoder = passwordEncoder;
    }

    public void checkUserExist(String username, String email){
        if(repository.existsByUsername(username)){
            throw new UserAlreadyExistsException("Username is already taken");
        }
        if(repository.existsByEmail(email)){
            throw new UserAlreadyExistsException("Email is already taken");
        }
    }
    public User create(RegisterRequest request){
        var username = request.getUsername();
        var email = request.getEmail();
        checkUserExist(username, email);
        var password = request.getPassword();
        var user = new User();

        user.validateUserName(username);
        user.validateEmail(email);
        user.validatePassword(password);

        var encodedPassword = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);

        return repository.save(user);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void addFriend(Long userId, String username) {
        var user = userQuery.getByUsername(username);
        user.checkFriendLimit();
        var friend = userQuery.getById(userId);
        user.addFriend(friend);
        repository.save(user);
    }
}
