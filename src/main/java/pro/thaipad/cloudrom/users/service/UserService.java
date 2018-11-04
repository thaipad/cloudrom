package pro.thaipad.cloudrom.users.service;

import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

public interface UserService {

    User getUser(int id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    List<User> getAllNotArchivedUsers();

    User createUser(User user);

    void updateUser(User user);

    void archiveUser(int id);

    void dearchiveUser(int id);

    void activateUser(int id);

    void deactivateUser(int id);

    void deleteUser(int id);

}
