package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.PictureEntity;
import com.example.pathfinder.model.entity.RouteEntity;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, UserService userService, CategoryService categoryService, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {
        PictureEntity picture = new PictureEntity().setUrl("/images/pic4.jpg").setTitle("DefaultPicture");

        return routeRepository.findAll()
                .stream()
                .map(routeEntity -> {
                    RouteViewModel map = modelMapper.map(routeEntity, RouteViewModel.class);
                    map.setPictureUrl(routeEntity.getPictures().stream().findFirst().orElse(picture).getUrl());

                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        RouteEntity route = modelMapper.map(routeServiceModel , RouteEntity.class);
        route.setAuthor(userService.findCurrentLoginUserEntity());

        Set<CategoryEntity> categories =
                routeServiceModel.getCategories().stream().map(categoryService::findByName).collect(Collectors.toSet());
        route.setCategories(categories);

        routeRepository.save(route);
    }

    @Override
    public RouteDetailsViewModel findById(Long id) {
        return routeRepository.findById(id)
                .map(routeEntity -> modelMapper.map(routeEntity , RouteDetailsViewModel.class))
                .orElse(null);
    }
}
