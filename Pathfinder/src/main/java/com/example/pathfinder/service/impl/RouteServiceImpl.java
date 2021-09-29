package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
