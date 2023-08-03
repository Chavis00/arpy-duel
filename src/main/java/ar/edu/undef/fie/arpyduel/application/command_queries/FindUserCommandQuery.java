package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.exception.notfound.UserNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserCommandQuery {
    private final UserRepository repository;

    public FindUserCommandQuery(UserRepository repository) {
        this.repository = repository;
    }

    public User getById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }
}
