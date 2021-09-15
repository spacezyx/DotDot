package com.example.dotdot.repository;

import com.example.dotdot.entity.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RobotRepository extends JpaRepository<Robot,Integer> {

    @Query(value = "from Robot where user_id = :user_id")
    List<Robot> getRobots(@Param("user_id") Integer user_id);

    @Query(value = "from Robot where user_id = :user_id and valid =false")
    List<Robot> getInvalidRobots(@Param("user_id") Integer user_id);

    @Query(value = "select APISecret from Robot WHERE APIKey = :APIKey")
    String getAPISecretByApikey(@Param("APIKey") String APIKey);


    @Query(value = "select valid from Robot WHERE APIKey = :APIKey")
    Boolean getValidByApikey(@Param("APIKey") String APIKey);

    @Query(value = "select used_times from Robot WHERE APIKey = :APIKey")
    Integer getTimesByApikey(@Param("APIKey") String APIKey);

    @Query(value = "select type from Robot WHERE APIKey = :APIKey")
    Integer getTypeByApikey(@Param("APIKey") String APIKey);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Robot SET  used_times = (used_times-1) WHERE APIKey = :APIKey")
    void decreaseTimesByApikey(@Param("APIKey") String APIKey);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Robot SET  used_times = (used_times+ :times) WHERE APIKey = :APIKey")
    void increaseTimesByApikey(@Param("APIKey") String APIKey,@Param("times") Integer times);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Robot SET  last_time = :last_time WHERE APIKey = :APIKey")
    void UpdateLastTimeByApikey(@Param("APIKey") String APIKey,@Param("last_time") String last_time);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Robot WHERE id = :id")
    void deleteOne(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Robot WHERE user_id = :user_id")
    void Reset(@Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into dotdot.robot (user_id,name,APIKey,last_time,establish_time,valid,used_times,APISecret,type) values(?1, ?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    void addRobot( @Param("user_id") Integer user_id,@Param("name") String name, @Param("APIKey") String APIKey,@Param("last_time") String last_time,
                   @Param("establish_time") String establish_time,@Param("valid") Boolean valid,@Param("used_times") Integer used_times,@Param("APISecret") String APISecret,@Param("type") Integer type);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Robot SET name = :name, valid = :valid, last_time = :last_time WHERE id = :id")
    void modifyRobot(@Param("id") Integer id, @Param("name") String name,@Param("valid") Boolean valid,@Param("last_time") String last_time
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE Robot SET  APISecret = :APISecret WHERE id = :id")
    void updateAPISecret(@Param("id") Integer id,@Param("APISecret") String APISecret
    );

}
