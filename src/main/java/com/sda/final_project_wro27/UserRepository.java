package com.sda.final_project_wro27;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select case when count(u)>0 then true else false end from User u where lower(u.login) like lower(?1)")
  boolean existsByLogin(String login);


  @Query("SELECT u from User u where lower(u.login) = lower(?1)")
    User findUserByLogin(String login);
}
