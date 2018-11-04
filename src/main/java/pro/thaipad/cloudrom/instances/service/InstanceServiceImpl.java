/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.thaipad.cloudrom.common.exceptions.ErrorOpenstackOperationException;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.instances.repository.InstanceRepository;
import pro.thaipad.cloudrom.openstack.OpenstackApi;
import pro.thaipad.cloudrom.users.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstanceServiceImpl implements InstanceService {

    private final Logger log = LoggerFactory.getLogger(getClass());

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

        // At first try to create instance in openstack. IOn success create in our database
        try {
            instance = openstackApi.createInstance(instance);
            return instanceRepository.save(instance, userId);
        } catch (ErrorOpenstackOperationException e) {
            log.error("Can't create instance in openstack");
            // TODO implement case if error openstack connection or operation
            return null;
        }
    }

    @Override
    public void updateInstance(Instance instance, int userId) {
        // At first try to update instance in openstack. On success update in our database
        try {
            if (!openstackApi.updateInstance(instance)) { // if such instance don't exist in openstack then create it
                instance = openstackApi.createInstance(instance);
            }
            instance.setRunningDate(null); // can modify only stopping instances
            instanceRepository.save(instance, userId);
        } catch (ErrorOpenstackOperationException e) {
            log.error("Can't create instance in openstack");
            // TODO implement case if error openstack connection or operation
        }
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
        // At first try to delete instance in openstack. On success delete in our database
        try {
            Instance instance = getInstance(id, userId);
            if (openstackApi.deleteInstance(instance.getOsInstanceId())){
                instanceRepository.delete(id, userId);
            }
        } catch (ErrorOpenstackOperationException e) {
            log.error("Can't create instance in openstack");
            // TODO implement case if error openstack connection or operation
        }
    }

    @Override
    public boolean runInstance(int id, int userId) {
        Instance instance = getInstance(id, userId);
        // At first try to run instance in openstack. On success run in our database
        try {
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
        } catch (ErrorOpenstackOperationException e) {
            log.error("Can't create instance in openstack");
            // TODO implement case if error openstack connection or operation
            return false;
        }
    }

    @Override
    public boolean stopInstance(int id, int userId) {
        Instance instance = getInstance(id, userId);
        // At first try to stop instance in openstack. On success stop in our database
        try {
            openstackApi.stopInstance(instance.getOsInstanceId());
            if (instance.getRunningDate() != null) { // instance hasn't already stopped inside openstack
                instance.setRunningDate(null);
                instanceRepository.save(instance, userId);
            }
            return true;
        } catch (ErrorOpenstackOperationException e) {
            log.error("Can't create instance in openstack");
            // TODO implement case if error openstack connection or operation
            return false;
        }
    }
}
