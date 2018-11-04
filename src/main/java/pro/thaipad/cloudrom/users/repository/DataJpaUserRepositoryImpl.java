/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.users.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public User save(User user) {
        return Optional.ofNullable(userRepository.save(user)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(int id) {
        if (userRepository.delete(id) == 0) {
            throw new NotFoundException();
        }
    }

    @Override
    public User get(int id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return Optional.ofNullable(userRepository.getByEmail(email)).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public List<User> getAllNotArchived() {
        return userRepository.getFilteredByArchived(false);
    }
}
