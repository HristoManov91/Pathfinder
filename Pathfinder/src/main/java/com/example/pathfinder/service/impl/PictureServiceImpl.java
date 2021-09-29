package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
}