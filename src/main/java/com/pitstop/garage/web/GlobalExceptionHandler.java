package com.pitstop.garage.web;

import com.pitstop.garage.exceptions.UserAlreadyExistException;
import com.pitstop.garage.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ModelAndView handleUserAlreadyExists(UserAlreadyExistException exception) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidation(MethodArgumentNotValidException exception) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", exception.getBindingResult().getTarget());
        return modelAndView;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrity(DataIntegrityViolationException exception) {
        log.error("Database constraint violation", exception);
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());
        modelAndView.addObject("errorMessage", "Could not save user. Check your data and try again.");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneral(Exception exception) {
        log.error("Unhandled exception", exception);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", "Something went wrong. Please try again.");
        return modelAndView;
    }
}
