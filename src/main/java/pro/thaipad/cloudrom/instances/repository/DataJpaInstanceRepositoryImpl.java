/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.users.entity.User;
import pro.thaipad.cloudrom.users.repository.CrudUserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaInstanceRepositoryImpl implements InstanceRepository {

    @Autowired
    private CrudInstanceRepository instanceRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Transactional
    @Override
    public Instance save(Instance instance, int userId) {
        if (instance.isNew()) {
            instance.setUser(userRepository.getOne(userId));
        } else {
            User user = instanceRepository.getUserByInstanceId(instance.getId());
            if (user == null || user.getId() != userId) {
                throw new NotFoundException();
            }
            instance.setUser(user);
        }
        instanceRepository.save(instance);
        return Optional.ofNullable(instanceRepository.save(instance)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(int id, int userId) {
        User user = instanceRepository.getUserByInstanceId(id);
        if (user == null || user.getId() != userId || instanceRepository.delete(id) == 0) {
            throw new NotFoundException();
        }
    }

    @Override
    public Instance get(int id, int userId) {
        return Optional.ofNullable(instanceRepository.getById(id, userId)).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Instance> getAll(int userId) {
        return instanceRepository.getAll(userId);
    }

    @Override
    public User getUserByInstanceId(int id) {
        return instanceRepository.getUserByInstanceId(id);
    }
}
