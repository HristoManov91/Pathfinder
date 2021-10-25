package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.binding.UserRegisterBindingModel;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("isExists" , true);
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel" , new UserRegisterBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes
                    .addFlashAttribute
                            ("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService
                .findUserByUsernameAndPassword(userLoginBindingModel.getUsername() , userLoginBindingModel.getPassword());

        if (userServiceModel == null){
            redirectAttributes.addFlashAttribute("isExists" , false);
            redirectAttributes.addFlashAttribute("userLoginBindingModel" , userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);

            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId() , userServiceModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean equalsPasswords =
                userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || !equalsPasswords) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:register";
        }

        boolean isUsernameExists = userService.isUsernameExists(userRegisterBindingModel.getUsername());
        if (isUsernameExists){
            //ToDo redirect with message
        }

        UserServiceModel usm = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userService.registerUser(usm);

        return "redirect:login";
    }

    @GetMapping("logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id , Model model){

        UserViewModel uvm = modelMapper.map(userService.findUserById(id), UserViewModel.class);
        model.addAttribute("user" , uvm);


        return "profile";
    }
}
