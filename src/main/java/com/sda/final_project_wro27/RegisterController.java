package com.sda.final_project_wro27;

import com.sda.final_project_wro27.dto.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService service;

    public RegisterController(UserService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("countries", Countries.values());
        model.addAttribute("registrationObject", registrationDto);

        return "registrationPage";
    }

    @PostMapping("/register")
    public String registrationEffect(RegistrationDto registrationDto, BindingResult bindingResult, Model model) {
//bindingResult.get

        if (!service.validPesel(registrationDto.getPesel())) {
            bindingResult.addError(new ObjectError("InvalidPesel", "You have invalid PESEL"));
            System.out.println(bindingResult.hasErrors());
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            model.addAttribute("binding", bindingResult);
            model.addAttribute("registrationObject", registrationDto);
            return "registrationPage";
        } else {
            service.registerUser(registrationDto);
            return "redirect:/login";
        }
    }
}



    /*@PostMapping("/register")
    public String postRegistration(@Validated RegistrationDTO registrationDTO,
                                   BindingResult bindingResult,
                                   Model model){
        boolean isDateOK = new DateValidatorUsingDateFormat(DateTimeFormatter.ofPattern(DATE_PATTERN))
                .isValid(registrationDTO.getBirthDate());
        if(bindingResult.hasErrors() || !isDateOK){
            model.addAttribute("error", "error");
            model.addAttribute("binding", bindingResult);
            model.addAttribute("registrationDTO", registrationDTO);
            return "registrationPage";
        }
        service.register(registrationDTO);
        return "redirect:/login";
    }*/



