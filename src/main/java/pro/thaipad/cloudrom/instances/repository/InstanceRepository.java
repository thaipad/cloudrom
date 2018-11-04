/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.repository;

import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

public interface InstanceRepository {

    Instance save(Instance instance, int userId);

    void delete(int id, int userId);

    Instance get(int id, int userId);

    List<Instance> getAll(int userId);

    User getUserByInstanceId(int id);

}