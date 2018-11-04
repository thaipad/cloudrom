/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances;

import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.instances.entity.Os;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static pro.thaipad.cloudrom.UserTestData.USER;
import static pro.thaipad.cloudrom.common.entity.AbstractBaseEntity.START_SEQ;

public class InstanceTestData {

    public static final Instance MINI = new Instance(START_SEQ + 3, "Mini", "Minimum configuration", 1, 2, 50, Os.CENTOS, USER);
    public static final Instance POWER = new Instance(START_SEQ + 4, "Power", "8/8/1000", 8, 8, 1000, Os.WINDOWS_2016, USER);

    public static void assertMatch(Instance actual, Instance expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Instance> actual, Instance... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Instance> actual, Iterable<Instance> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

}
