package com.sda.final_project_wro27;

import com.sda.final_project_wro27.model.User;
import com.sda.final_project_wro27.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> list = userRepository.findAllUsers();
        model.addAttribute("users", list);
        System.out.println(list);
        if (list.isEmpty()){
            model.addAttribute("response","NoData");
        }
        else {
            model.addAttribute("response","full");
        }
        return "showAllUsers";
    }

    @GetMapping("/deleteuser/{login}")
    public String deleteUser(@PathVariable("login") String login, Model model) {
        User userByLogin = userService.findUserByLogin(login);
        System.out.println(userByLogin);
        System.out.println("user do usuniecia: "+userByLogin);
        userRepository.delete(userByLogin);
        model.addAttribute("users", userRepository.findAll());
        return "showAllUsers";
    }

    @PostMapping("/goback")
    public String goBack() {
        return "index";
    }

}
