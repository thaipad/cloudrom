package pro.thaipad.cloudrom.instances.service;

import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

public interface InstanceService {
    List<Instance> getAllInstances(int userId);

    Instance createInstance(Instance instance, int userId);

    void updateInstance(Instance instance, int userId);

    void updateNameInstance(Instance instance, int userId);

    User getUserByInstanceId(int id);

    void deleteInstance(int id, int userId);

    boolean runInstance(int id, int userId);

    boolean stopInstance(int id, int userId);

    Instance getInstance(int id, int userId);
}
