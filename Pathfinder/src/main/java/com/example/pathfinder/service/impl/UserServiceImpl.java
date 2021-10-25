package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel usm) {
        UserEntity userEntity = modelMapper.map(usm, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(usm.getPassword()));
        userEntity.setLevel(LevelEnum.BEGINNER);

        userRepository.save(userEntity);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository
                .findUserEntityByUsernameAndPassword(username , password)
                .map(userEntity -> modelMapper.map(userEntity , UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setUsername(username);
        currentUser.setId(id);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public UserServiceModel findUserById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> modelMapper.map(userEntity , UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findCurrentLoginUserEntity() {
        return userRepository.findById(currentUser.getId()).orElse(null);
    }
}
