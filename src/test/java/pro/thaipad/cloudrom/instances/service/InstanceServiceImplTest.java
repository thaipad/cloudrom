/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.thaipad.cloudrom.common.exceptions.NotFoundException;
import pro.thaipad.cloudrom.instances.entity.Os;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.common.AbstractServiceTest;

import java.util.List;

import static pro.thaipad.cloudrom.UserTestData.*;
import static pro.thaipad.cloudrom.instances.InstanceTestData.*;

public class InstanceServiceImplTest extends AbstractServiceTest {

    private static final Logger log = LoggerFactory.getLogger("output");

    @Autowired
    protected InstanceService instanceService;

    @Test
    public void getAllTest() {
        List<Instance> list = instanceService.getAllInstances(USER_ID);
        assertMatch(list, MINI, POWER);
    }

    @Test
    public void getTest() {
        assertMatch(instanceService.getInstance(MINI.getId(), USER_ID), MINI);
    }

    @Test
    public void getWrongIdTest() {
        thrown.expect(NotFoundException.class);
        instanceService.getInstance(10, USER_ID);
    }

    @Test
    public void getWrongUserTest() {
        thrown.expect(NotFoundException.class);
        instanceService.getInstance(MINI.getId(), ADMIN_ID);
    }

    @Test
    public void createTest() {
        Instance instance = new Instance(null, "Created instance", "4/8/500", 4, 8, 500, Os.DEBIAN, null);
        instanceService.createInstance(instance, USER_ID);
        List<Instance> list = instanceService.getAllInstances(USER_ID);
        assertMatch(list, MINI, POWER, instance);
    }

    @Test
    public void updateTest() {
        Instance instance = new Instance(MINI);
        instance.setCpu(20);
        instanceService.updateInstance(instance, USER_ID);
        List<Instance> list = instanceService.getAllInstances(USER_ID);
        assertMatch(list, instance, POWER);
    }

    @Test
    public void updateWrongUserTest() {
        Instance instance = new Instance(MINI);
        instance.setCpu(20);
        thrown.expect(NotFoundException.class);
        instanceService.updateInstance(instance, ADMIN_ID);
    }

    @Test
    public void deleteTest() {
        instanceService.deleteInstance(MINI.getId(), USER_ID);
        List<Instance> list = instanceService.getAllInstances(USER_ID);
        assertMatch(list, POWER);
    }

    @Test
    public void deleteWrongUserTest() {
        thrown.expect(NotFoundException.class);
        instanceService.deleteInstance(MINI.getId(), ADMIN_ID);
    }

    @Test
    public void deleteWrongIdTest() {
        thrown.expect(NotFoundException.class);
        instanceService.deleteInstance(10, USER_ID);
    }
}
