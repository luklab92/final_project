package com.sda.final_project_wro27.repository;

import com.sda.final_project_wro27.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

  @Query("select case when count(u)>0 then true else false end from UserEntity u where lower(u.login) like lower(?1)")
  boolean existsByLogin(String login);


  @Query("SELECT u from UserEntity u where lower(u.login) = lower(?1)")
  UserEntity findUserByLogin(String login);

  @Query("SELECT u from UserEntity u")
  List<UserEntity> findAllUsers();
}
