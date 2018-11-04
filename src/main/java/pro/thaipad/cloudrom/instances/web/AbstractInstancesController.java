/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.thaipad.cloudrom.AuthorizedUser;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.instances.service.InstanceService;
import pro.thaipad.cloudrom.users.entity.Role;
import pro.thaipad.cloudrom.users.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbstractInstancesController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    InstanceService instanceService;

    public List<Instance> getAll() {
        log.info("getAll");
        int userId = AuthorizedUser.authUserId();
        return instanceService.getAllInstances(userId);
    }

    public Instance get(int id) {
        log.info("get {}", id);
        int userId = AuthorizedUser.authUserId();
        return instanceService.getInstance(id, userId);
    }

    public boolean run(int id) {
        log.info("start {}", id);
        int userId = AuthorizedUser.authUserId();
        return instanceService.runInstance(id, userId);
    }

    public boolean stop(int id) {
        log.info("stop {}", id);
        int userId = AuthorizedUser.authUserId();
        return instanceService.stopInstance(id, userId);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        int userId = AuthorizedUser.authUserId();
        instanceService.deleteInstance(id, userId);
    }

    public Instance create(Instance instance) {
        log.info("create {}", instance);
        User user = AuthorizedUser.safeGet().getUser();
        instance.setUser(user);
        return instanceService.createInstance(instance, user.getId());
    }

    public void update(Instance instance) {
        log.info("update {}", instance);
        User user = AuthorizedUser.safeGet().getUser();
        instance.setUser(user);
        instanceService.updateInstance(instance, user.getId());
    }

    public void updateName(Instance instance) {
        log.info("update name {}", instance);
        int userId = AuthorizedUser.authUserId();
        instanceService.updateNameInstance(instance, userId);
    }

}
