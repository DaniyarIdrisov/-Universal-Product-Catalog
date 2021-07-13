package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserRegistrationForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegistrationController {

    @Autowired
    private UsersService usersService;

    @PermitAll
    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "registration";
    }

    @PermitAll
    @PostMapping("/addUser")
    public String addUser(@Valid UserRegistrationForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(errors -> {
                for (String error: Objects.requireNonNull(errors.getCodes())) {
                    if (error.equals("userRegistrationForm.ValidRepeatPasswords")) {
                        model.addAttribute("passwordsErrorMessage", errors.getDefaultMessage());
                    }
                }
                return true;
            });
            return "registration";
        }
        usersService.addUser(form);
        return "redirect:/registrationSuccessful";
    }

    @PermitAll
    @GetMapping("/registrationSuccessful")
    public String registrationSuccessfulPage() {
        return "registration_successful";
    }

}
