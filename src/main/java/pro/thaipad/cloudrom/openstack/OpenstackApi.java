/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.openstack;

import pro.thaipad.cloudrom.common.exceptions.ErrorOpenstackOperationException;
import pro.thaipad.cloudrom.instances.entity.Instance;

import java.io.Closeable;
import java.time.LocalDateTime;

public interface OpenstackApi {

    Instance createInstance(Instance instance) throws ErrorOpenstackOperationException;

    boolean updateInstance(Instance instance) throws ErrorOpenstackOperationException ;

    boolean deleteInstance(int osInstanceId) throws ErrorOpenstackOperationException ;

    LocalDateTime runInstance(int osInstanceId) throws ErrorOpenstackOperationException ;

    void stopInstance(int osInstanceId) throws ErrorOpenstackOperationException ;

    LocalDateTime getRunningDateInstance(int osInstanceId) throws ErrorOpenstackOperationException ;

}
