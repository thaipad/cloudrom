/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.openstack;

import pro.thaipad.cloudrom.instances.entity.Instance;

import java.time.LocalDateTime;

public interface OpenstackApi {

    Instance createInstance(Instance instance);

    boolean updateInstance(Instance instance);

    boolean deleteInstance(int osInstanceId);

    LocalDateTime runInstance(int osInstanceId);

    void stopInstance(int osInstanceId);

    LocalDateTime getRunningDateInstance(int osInstanceId);

}
