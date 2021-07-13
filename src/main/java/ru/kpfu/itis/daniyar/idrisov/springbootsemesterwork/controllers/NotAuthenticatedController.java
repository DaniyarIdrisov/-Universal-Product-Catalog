package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
public class NotAuthenticatedController {

    @PermitAll
    @GetMapping("/notAuthenticated")
    public String notAuthenticatedPage() {
        return "not_authenticated";
    }

}
