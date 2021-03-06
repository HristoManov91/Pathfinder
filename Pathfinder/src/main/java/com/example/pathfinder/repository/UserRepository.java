package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {

    //ToDo findUser
    Optional<UserEntity> findUserEntityByUsernameAndPassword(String username, String password);

    @Query ("SELECT u.username FROM UserEntity u")
    Optional<UserEntity> findByUsername(String username);
}
