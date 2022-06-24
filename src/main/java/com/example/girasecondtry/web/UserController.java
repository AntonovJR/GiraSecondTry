package com.example.girasecondtry.web;

import com.example.girasecondtry.CurrentUser;
import com.example.girasecondtry.models.binding.UserLoginBindingModel;
import com.example.girasecondtry.models.binding.UserRegisterBindingModel;
import com.example.girasecondtry.models.service.UserServiceModel;
import com.example.girasecondtry.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }
        if(!model.containsAttribute("wrongPassword")){
            model.addAttribute("wrongPassword", true);
        }
        return "login";
    }


    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }


        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "/login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || userService.findUser(userLoginBindingModel.getEmail())==null) {

            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("isFound",false);
            return "redirect:login";
        }
        UserServiceModel userServiceModel = userService.findByEmailAndPassword(userLoginBindingModel.getEmail(), userLoginBindingModel.getPassword());

        if (userServiceModel==null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("wrongPassword",false);
            return "redirect:login";
        }

        userService.loginUser(userServiceModel);

        return "redirect:/task/home";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "/index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
