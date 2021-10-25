package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    CategoryEntity findByName(CategoryNameEnum name);
}
