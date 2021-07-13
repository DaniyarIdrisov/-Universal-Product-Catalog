package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteFailController {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'GOD')")
    @GetMapping("/deleteFail")
    public String deleteFail() {
        return "delete_fail";
    }

}
