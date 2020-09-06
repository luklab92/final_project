package com.sda.final_project_wro27.repository;

import com.sda.final_project_wro27.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    @Query("select case when count(us)>0 then true else false end from UserStatus us where lower(us.status) like lower(?1)")
    boolean statusExists(String name);

    @Query("SELECT us from UserStatus us where lower(us.status) = lower(?1)")
    UserStatus findByStatusByName(String name);
}
