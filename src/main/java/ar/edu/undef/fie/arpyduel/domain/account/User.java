package ar.edu.undef.fie.arpyduel.domain.account;


import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.exception.badrequest.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany
    private List<Character> characters;

    @ManyToMany
    private List<User> friends;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private boolean enabled;
    public User() {
        this.friends = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.role = role;
        this.characters = new ArrayList<>();
        this.friends = new ArrayList<>();
    }


    /*
        UserDetails interface methods
    */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }


    public void addCharacter(Character character) {
        if(reachLimitOfCharacters())
            throw new CharacterLimitException();
        this.characters.add(character);
    }
    public void addFriend(User newFriend) {
        if(reachLimitOfFriends())
            throw new FriendLimitException();
        this.friends.add(newFriend);
    }
    public Boolean reachLimitOfCharacters(){
        return this.characters.size()>5;
    }
    public Boolean reachLimitOfFriends(){
        return this.friends.size()>100;
    }

    public void checkCharacterLimit(){
        if(reachLimitOfCharacters())
            throw new CharacterLimitException();
    }
    public void checkFriendLimit(){
        if(reachLimitOfFriends())
            throw new FriendLimitException();
    }
    public void removeCharacter(Character character){
        this.characters.remove(character);
    }
    public void removeFriend(User friend){
        this.friends.remove(friend);
    }
    /*
        GETTERS & SETTERS
    */

    public void validateUserName(String username) {
        if (username == null || username.isEmpty())
            throw new InvalidUsernameException("Username cannot be empty.");
        if (username.length() < 4)
            throw new InvalidUsernameException("Username must be at least 4 characters long.");
        if(username.length() > 20)
            throw new InvalidUsernameException("Username must be less than 20 characters long.");
        if (username.matches(".*\\s.*"))
            throw new InvalidUsernameException("Username cannot contain spaces.");
        if (!username.matches("^[a-zA-Z0-9._-]+$"))
            throw new InvalidUsernameException("Username can only contain letters, numbers, periods, underscores, and dashes.");
    }
    public void validatePassword(String password) {
        if (password == null || password.isEmpty())
            throw new InvalidPasswordException("Password cannot be empty.");
        if (password.length() < 8)
            throw new InvalidPasswordException("Password must be at least 8 characters long.");
        if (password.length() > 20)
            throw new InvalidPasswordException("Password must be less than 20 characters long.");
        if (password.matches(".*\\s.*"))
            throw new InvalidPasswordException("Password cannot contain spaces.");
        if (!password.matches(".*[A-Z].*"))
            throw new InvalidPasswordException("Password must contain at least one uppercase letter.");
        if (!password.matches(".*[a-z].*"))
            throw new InvalidPasswordException("Password must contain at least one lowercase letter.");
        if (!password.matches(".*\\d.*"))
            throw new InvalidPasswordException("Password must contain at least one digit.");
        if (!password.matches(".*[!@#$%^&*()].*"))
            throw new InvalidPasswordException("Password must contain at least one special character (!@#$%^&*()).");
    }
    public void validateEmail(String email) {
        if (email == null || email.isEmpty())
            throw new InvalidEmailException("Email address cannot be empty.");
        if (email.length() > 50)
            throw new InvalidEmailException("Email address must be less than 50 characters long.");
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new InvalidEmailException("Invalid email address format.");
        if (email.matches(".*\\s.*"))
            throw new InvalidPasswordException("Password cannot contain spaces.");
    }
    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
