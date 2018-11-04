package pro.thaipad.cloudrom.users.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import pro.thaipad.cloudrom.common.AbstractServiceTest;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.users.entity.Role;
import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

import static pro.thaipad.cloudrom.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void getUser() {
        User user = service.getUser(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getWrongUser() {
        thrown.expect(NotFoundException.class);
        service.getUser(WRONG_USER_ID);
    }

    @Test
    public void getUserByEmail() {
        User user = service.getUserByEmail(USER.getEmail());
        assertMatch(user, USER);
    }

    @Test
    public void getUserByWrongEmail() {
        thrown.expect(NotFoundException.class);
        service.getUserByEmail("wrong@email.com");
    }

    @Test
    public void getAllUsers() {
        List<User> all = service.getAllUsers();
        assertMatch(all, ADMIN, ARCHIVED_USER, USER);
    }

    @Test
    public void getAllNotArchivedUsers() {
        List<User> all = service.getAllNotArchivedUsers();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void createUser() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = service.createUser(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAllUsers(), ADMIN, ARCHIVED_USER, newUser, USER);
    }

    @Test
    public void createDublicateEmailUser() {
        thrown.expect(DataAccessException.class);
        service.createUser(new User(null, "New", USER.getEmail(), "newPass", Role.ROLE_USER));
    }

    @Test
    public void updateUser() {
        User modifiedUser = new User(USER);
        modifiedUser.setName("Modified name");
        modifiedUser.setEnabled(false);
        service.updateUser(modifiedUser);
        assertMatch(service.getUser(modifiedUser.getId()), modifiedUser);
    }

    @Test
    public void delete() throws Exception {
        service.deleteUser(ARCHIVED_USER_ID);
        assertMatch(service.getAllUsers(), ADMIN, USER);
    }

    @Test
    public void deleteWrongUser() throws Exception {
        thrown.expect(NotFoundException.class);
        service.deleteUser(WRONG_USER_ID);
    }

}