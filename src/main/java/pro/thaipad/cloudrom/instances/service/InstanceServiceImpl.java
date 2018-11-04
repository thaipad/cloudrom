/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.instances.repository.InstanceRepository;
import pro.thaipad.cloudrom.openstack.OpenstackApi;
import pro.thaipad.cloudrom.openstack.OpenstackApiMockImpl;
import pro.thaipad.cloudrom.users.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstanceServiceImpl implements InstanceService {

    @Autowired
    private InstanceRepository instanceRepository;

    @Autowired
    private OpenstackApi openstackApi;

    @Override
    public List<Instance> getAllInstances(int userId) {
        return instanceRepository.getAll(userId);
    }

    @Override
    public Instance getInstance(int id, int userId) {
        return instanceRepository.get(id, userId);
    }

    @Override
    public Instance createInstance(Instance instance, int userId) {
        instance = openstackApi.createInstance(instance);
        return instanceRepository.save(instance, userId);
    }

    @Override
    public void updateInstance(Instance instance, int userId) {
        if (!openstackApi.updateInstance(instance)) {
            instance = openstackApi.createInstance(instance);
        }
        instance.setRunningDate(null);
        instanceRepository.save(instance, userId);
    }

    @Override
    public void updateNameInstance(Instance instance, int userId) {
        instanceRepository.save(instance, userId);
    }

    @Override
    public User getUserByInstanceId(int id) {
        return instanceRepository.getUserByInstanceId(id);
    }

    @Override
    public void deleteInstance(int id, int userId) {
        Instance instance = getInstance(id, userId);
        if (openstackApi.deleteInstance(instance.getOsInstanceId())){
            instanceRepository.delete(id, userId);
        }
    }

    @Override
    public boolean runInstance(int id, int userId) {
        Instance instance = getInstance(id, userId);
        LocalDateTime runningDate = openstackApi.runInstance(instance.getOsInstanceId());
        if (runningDate != null) { // success of running instance
            if (!runningDate.equals(instance.getRunningDate())) { // instance hasn't already run inside openstack
                instance.setRunningDate(runningDate);
                instanceRepository.save(instance, userId);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean stopInstance(int id, int userId) {
        Instance instance = getInstance(id, userId);
        openstackApi.stopInstance(instance.getOsInstanceId());
        if (instance.getRunningDate() != null) { // instance hasn't already stopped inside openstack
            instance.setRunningDate(null);
            instanceRepository.save(instance, userId);
        }
        return true;
    }
}
