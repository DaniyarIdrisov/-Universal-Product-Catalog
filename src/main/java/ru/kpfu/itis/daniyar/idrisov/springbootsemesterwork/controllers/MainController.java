package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.annotation.security.PermitAll;

@Controller
public class MainController {

    @Autowired
    private UsersService usersService;

    @PermitAll
    @GetMapping("/main")
    public String mainPage(Model model) {
        Integer maxCountOfComments = usersService.getMaxCountOfComments();
        Integer maxCountOfOrders = usersService.getMaxCountOfOrders();
        if (maxCountOfOrders == null) {
            model.addAttribute("order", 0);
        }
        else {
            model.addAttribute("order", maxCountOfOrders);
        }
        if (maxCountOfComments == null) {
            model.addAttribute("comment", 0);
        }
        else {
            model.addAttribute("comment", maxCountOfComments);
        }
        return "main";
    }

}
