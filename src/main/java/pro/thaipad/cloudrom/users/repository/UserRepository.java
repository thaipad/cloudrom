package pro.thaipad.cloudrom.users.repository;

import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

public interface UserRepository {
    // throw NotFoundException if not found
    User save(User user);

    // throw NotFoundException if not found
    void delete(int id);

    // throw NotFoundException if not found
    User get(int id);

    // throw NotFoundException if not found
    User getByEmail(String email);

    List<User> getAll();

    List<User> getAllNotArchived();
}
