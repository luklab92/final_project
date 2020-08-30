package com.sda.final_project_wro27;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("countries",Countries.values());
        model.addAttribute("registrationObject",registrationDto);
        return "registrationPage";
    }

    @PostMapping("/register")
    public String registrationEffect(RegistrationDto registrationDto) {
        service.registerUser(registrationDto);
        return "redirect:/login";
    }

}
