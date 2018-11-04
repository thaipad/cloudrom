/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.users.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import pro.thaipad.cloudrom.common.entity.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
@NoArgsConstructor
public class User extends AbstractBaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    @Getter
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    @Getter
    @Setter
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default false")
    @Getter
    @Setter
    private boolean enabled;

    @Column(name = "archived", nullable = false, columnDefinition = "bool default false")
    @Getter
    @Setter
    private boolean archived;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    @Getter
    @Setter
    private LocalDateTime registered;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @Getter
    private Set<Role> roles;

    public User(Integer id, String name, String email, String password, boolean enabled, boolean archived, LocalDateTime registered, Set<Role> roles) {
        super(id, name);
        this.enabled = enabled;
        this.archived = archived;
        this.registered = registered;
        this.setRoles(roles);
        this.password = password;
        setEmail(email);
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, false, false, LocalDateTime.now(), EnumSet.of(role, roles));
    }

    public User(User user) {
        super(user.id, user.name);
        this.email = user.email;
        this.password = user.password;
        this.enabled = user.enabled;
        this.archived = user.archived;
        this.registered = user.registered;
        this.setRoles(user.roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", archived=" + archived +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
