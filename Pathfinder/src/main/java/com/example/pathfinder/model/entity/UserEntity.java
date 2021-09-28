package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enums.LevelEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String email;
    private Set<RoleEntity> role = new HashSet<>();
    private LevelEnum level;

    public UserEntity() {
    }

    @Column(unique = true , nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToMany
    public Set<RoleEntity> getRole() {
        return role;
    }

    public UserEntity setRole(Set<RoleEntity> role) {
        this.role = role;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public UserEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    @Column
    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
