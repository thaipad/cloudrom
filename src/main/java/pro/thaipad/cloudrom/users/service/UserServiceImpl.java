/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.thaipad.cloudrom.AuthorizedUser;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.users.entity.User;
import pro.thaipad.cloudrom.users.repository.UserRepository;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUser(int id) {
        return userRepository.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public List<User> getAllNotArchivedUsers() {
        return userRepository.getAllNotArchived();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(prepareToSave(user));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(prepareToSave(user));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    @Override
    public void archiveUser(int id) {
        //TODO implement method
    }

    @Override
    public void dearchiveUser(int id) {
        //TODO implement method
    }

    @Override
    public void activateUser(int id) {
        //TODO implement method
    }

    @Override
    public void deactivateUser(int id) {
        //TODO implement method
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.getByEmail(email.toLowerCase());
            return new AuthorizedUser(user);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("User " + email + " not found!");
        }
    }

    private User prepareToSave(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

}
