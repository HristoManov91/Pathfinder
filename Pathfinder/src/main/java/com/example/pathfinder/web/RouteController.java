package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.RouteAddBindingModel;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/details/{id}")
        public String details(@PathVariable Long id, Model model){
        model.addAttribute("route" , routeService.findById(id));
        return "route-details";
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        List<RouteViewModel> routes = routeService.findAllRoutesView();

        model.addAttribute("routes", routes);

        return "routes";
    }

    @GetMapping("/add")
    public String addRoute(Model model) {
        model.addAttribute("levels", LevelEnum.values());
        model.addAttribute("categories", CategoryNameEnum.values());
        if (!model.containsAttribute("routeAddBindingModel")){
            model.addAttribute("routeAddBindingModel" , new RouteAddBindingModel());
        }
        return "add-route";
    }

    @PostMapping("/add")
    public String addRouteConfirm(@Valid RouteAddBindingModel routeAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper.map(routeAddBindingModel , RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }
}
