package pro.thaipad.cloudrom.instances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pro.thaipad.cloudrom.instances.entity.Instance;
import pro.thaipad.cloudrom.users.entity.User;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudInstanceRepository extends JpaRepository<Instance, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Instance i WHERE i.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Instance save(Instance instance);

    @Query("SELECT i FROM Instance i WHERE i.user.id=:userId")
    List<Instance> getAll(@Param("userId") int userId);

    @Query("SELECT i FROM Instance i WHERE i.id=:id AND i.user.id=:userId")
    Instance getById(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT i.user FROM Instance i WHERE i.id=:id")
    User getUserByInstanceId(@Param("id") int id);

}
