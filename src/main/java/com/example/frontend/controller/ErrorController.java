package com.example.frontend.controller;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	   public ModelAndView handleAllExceptions(Exception ex) {
	       ModelAndView modelAndView = new ModelAndView();
	       modelAndView.setViewName("error");
	       modelAndView.addObject("message", ex.getMessage());
	       return modelAndView;
	   }

	   
	}

