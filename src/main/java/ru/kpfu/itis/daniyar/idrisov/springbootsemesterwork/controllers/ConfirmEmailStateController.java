package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.annotation.security.PermitAll;

@Controller
public class ConfirmEmailStateController {

    @Autowired
    private UsersService usersService;

    @PermitAll
    @GetMapping("/confirm/{state}")
    public String confirmPage(@PathVariable("state") String state) {
        UserDto userDto = usersService.getUserByConfirmCode(state);
        if (userDto == null) {
            return "redirect:/registration";
        }
        usersService.updateConfirmCode(userDto.getId());
        return "confirm_email";
    }

}
