package com.sda.final_project_wro27.repository;

import com.sda.final_project_wro27.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select case when count(u)>0 then true else false end from UserRole u where lower(u.role) like lower(?1)")
    boolean roleExist(String name);

    @Query("SELECT ur from UserRole ur where lower(ur.role) = lower(?1)")
    UserRole findByRoleName(String name);
}
