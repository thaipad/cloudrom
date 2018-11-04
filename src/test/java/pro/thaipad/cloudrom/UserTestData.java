/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom;

import pro.thaipad.cloudrom.users.entity.Role;
import pro.thaipad.cloudrom.users.entity.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static pro.thaipad.cloudrom.common.entity.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int ARCHIVED_USER_ID = START_SEQ + 2;
    public static final int WRONG_USER_ID = START_SEQ - 1;

    public static final User USER;
    public static final User ADMIN;
    public static final User ARCHIVED_USER;

    static {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.ROLE_USER);
        Set<Role> adminRoles = new HashSet<>(userRoles);
        adminRoles.add(Role.ROLE_ADMIN);
        USER = new User(USER_ID, "User", "user@yandex.ru", "password", true, false, LocalDateTime.now(), userRoles);
        ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", true, false, LocalDateTime.now(), adminRoles);
        ARCHIVED_USER = new User(ARCHIVED_USER_ID, "Archived user", "noname@yandex.ru", "password", false, true, LocalDateTime.now(), userRoles);
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
