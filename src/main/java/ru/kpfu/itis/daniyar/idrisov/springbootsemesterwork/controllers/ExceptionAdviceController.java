package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.aspects.ErrorLog;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.exceptions.GetException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdviceController {

    @ErrorLog
    @ExceptionHandler(GetException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView getException(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("status", "403.Forbidden");
        modelAndView.setViewName("my_error");
        return modelAndView;
    }

}
