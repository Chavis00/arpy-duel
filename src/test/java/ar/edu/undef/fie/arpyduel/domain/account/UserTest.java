package ar.edu.undef.fie.arpyduel.domain.account;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.badrequest.CharacterLimitException;
import ar.edu.undef.fie.arpyduel.exception.badrequest.FriendLimitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final User user = new User();

    @Test
    void addCharacter() {
        Character character = new Character();
        user.addCharacter(character);
        assertTrue(user.getCharacters().contains(character));
    }
    @Test
    void addFriend() {
        User friend = new User();
        user.addFriend(friend);
        assertTrue(user.getFriends().contains(friend));
    }
    @Test
    void reachLimitOfCharacters() {
        for (int i = 0; i < 6; i++) {
            user.addCharacter(new Character());
        }
        assertThrows(CharacterLimitException.class, () -> user.addCharacter(new Character()));
    }

    @Test
    void removeFriend() {
        User friend = new User();
        user.addFriend(friend);
        user.removeFriend(friend);
        assertFalse(user.getFriends().contains(friend));
    }

    @Test
    void reachLimitOfFriends() {
        for (int i = 0; i < 101; i++) {
            user.addFriend(new User());
        }
        assertThrows(FriendLimitException.class, () -> user.addFriend(new User()));
    }
    @Test
    void checkCharacterLimit() {
        for(int i = 0; i < 6; i++) {
            user.addCharacter(new Character());
        }
        assertThrows(CharacterLimitException.class, user::checkCharacterLimit);
    }
    @Test
    void checkFriendLimit() {
        for (int i = 0; i < 101; i++) {
            user.addFriend(new User());
        }
        assertThrows(FriendLimitException.class, user::checkFriendLimit);
    }
    @Test
    void removeCharacter() {
        user.addCharacter(new Character());
        user.removeCharacter(user.getCharacters().get(0));
        assertTrue(user.getCharacters().isEmpty());
    }

    @Test
    void isEnabled() {
        user.setEnabled(true);
        assertTrue(user.isEnabled());
        user.setEnabled(false);
        assertFalse(user.isEnabled());
    }

}