/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.openstack;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.thaipad.cloudrom.instances.entity.Instance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OpenstackApiMockImpl implements OpenstackApi {

    private static final Map<Integer, LocalDateTime> mockOsInstances;
    private AtomicInteger maxId = new AtomicInteger(10025);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");

    static {

        mockOsInstances = new HashMap<>();
        mockOsInstances.put(10001, LocalDateTime.of(2018,10,26,10,26,55));
        mockOsInstances.put(10025, null);
        mockOsInstances.put(10002, LocalDateTime.parse("2018-10-27T15:26:55"));
        mockOsInstances.put(10003, LocalDateTime.parse("2018-10-27T15:36:00"));
        mockOsInstances.put(10020, null);
        mockOsInstances.put(10004, LocalDateTime.parse("2018-10-27T11:15:55"));
        mockOsInstances.put(10005, LocalDateTime.parse("2018-10-27T11:25:25"));
        mockOsInstances.put(10006, LocalDateTime.parse("2018-10-27T11:29:00"));
        mockOsInstances.put(10021, null);
        mockOsInstances.put(10022, null);
        mockOsInstances.put(10023, null);
        mockOsInstances.put(10007, LocalDateTime.parse("2018-10-27T08:10:55"));
        mockOsInstances.put(10008, LocalDateTime.parse("2018-10-27T10:26:55"));
        mockOsInstances.put(10009, LocalDateTime.parse("2018-10-27T12:43:25"));
        mockOsInstances.put(10010, LocalDateTime.parse("2018-10-27T14:25:05"));
        mockOsInstances.put(10024, null);
        mockOsInstances.put(10011, LocalDateTime.parse("2018-10-28T17:26:55"));
        mockOsInstances.put(10012, LocalDateTime.parse("2018-10-28T17:29:51"));
        mockOsInstances.put(10013, LocalDateTime.parse("2018-10-28T17:33:00"));
        mockOsInstances.put(10014, LocalDateTime.parse("2018-10-28T18:01:31"));
        mockOsInstances.put(10015, LocalDateTime.parse("2018-10-28T18:09:16"));
        mockOsInstances.put(10016, LocalDateTime.parse("2018-10-28T18:15:19"));
        mockOsInstances.put(10017, LocalDateTime.parse("2018-10-28T19:44:55"));
        mockOsInstances.put(10018, LocalDateTime.parse("2018-10-28T19:53:20"));
        mockOsInstances.put(10019, LocalDateTime.parse("2018-10-26T12:07:21"));

    }

    @Override
    public Instance createInstance(Instance instance) {
        int id = maxId.incrementAndGet();
        mockOsInstances.put(id, null);
        Instance instanceCopy = new Instance(instance);
        instanceCopy.setOsInstanceId(id);
        instanceCopy.setRunningDate(null);
        return instanceCopy;
    }

    @Override
    public boolean updateInstance(Instance instance) {
        return mockOsInstances.containsKey(instance.getOsInstanceId());
    }

    @Override
    public boolean deleteInstance(int osInstanceId) {
        if (mockOsInstances.containsKey(osInstanceId)) {
            mockOsInstances.remove(osInstanceId);
        }
        return true;
    }

    @Override
    public LocalDateTime runInstance(int osInstanceId) {
        if (!mockOsInstances.containsKey(osInstanceId)) {
            return null;
        } else if (mockOsInstances.get(osInstanceId) == null) {
            mockOsInstances.put(osInstanceId, LocalDateTime.now());
            prossesingEmulator(10);
        }
        return mockOsInstances.get(osInstanceId);
    }

    @Override
    public void stopInstance(int osInstanceId) {
        if (mockOsInstances.containsKey(osInstanceId)) {
            mockOsInstances.put(osInstanceId, null);
            prossesingEmulator(3);
        }
    }

    @Override
    public LocalDateTime getRunningDateInstance(int osInstanceId) {
        return mockOsInstances.getOrDefault(osInstanceId, null);
    }

    private void prossesingEmulator(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
