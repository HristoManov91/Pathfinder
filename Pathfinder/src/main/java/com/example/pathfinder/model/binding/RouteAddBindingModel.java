package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class RouteAddBindingModel {

    private String name;
    private String description;
    private MultipartFile gpxCoordinates;
    private LevelEnum level;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;

    public RouteAddBindingModel() {
    }

    @Size(min = 3 , max = 20 , message = "Route name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public RouteAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @Size(min = 5 , message = "Description must be more than 5 characters")
    public String getDescription() {
        return description;
    }

    public RouteAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddBindingModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    @NotBlank(message = "Level must be select")
    public LevelEnum getLevel() {
        return level;
    }

    public RouteAddBindingModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @NotNull
    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public RouteAddBindingModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
