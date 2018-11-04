/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.instances.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import pro.thaipad.cloudrom.common.entity.AbstractBaseEntity;
import pro.thaipad.cloudrom.users.entity.User;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_instances")
@NoArgsConstructor
public class Instance extends AbstractBaseEntity {

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @NotNull
    @Column
    @Getter
    @Setter
    private Integer cpu;

    @NotNull
    @Column
    @Getter
    @Setter
    private Integer ram;

    @NotNull
    @Column
    @Getter
    @Setter
    private Integer hdd;

    @NotNull
    @Column(name = "os")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Os os;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @Getter
    @Setter
    @JsonIgnore
    private User user;

    @Getter
    @Setter
    @Column(name = "osinstance_id")
    private Integer osInstanceId; // key of instance in openstack

    @Getter
    @Setter
    @Column(name = "running_date")
    private LocalDateTime runningDate;

    public Instance(Integer id, @NotNull String name, String description, @NotNull Integer cpu, @NotNull Integer ram, @NotNull Integer hdd, @NotNull Os os, @NotNull User user) {
        super(id, name);
        this.description = description;
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.user = user;
    }

    public Instance(Instance instance) {
        super(instance.id, instance.name);
        this.description = instance.description;
        this.cpu = instance.cpu;
        this.ram = instance.ram;
        this.hdd = instance.hdd;
        this.os = instance.os;
        this.user = instance.user;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os=" + os +
                '}';
    }
}
