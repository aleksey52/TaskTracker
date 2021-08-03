package com.practice.tracker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ResourceNotFoundException.class})
  protected ModelAndView resourceNotFound(Exception ex) {
    ModelAndView modelAndView = new ModelAndView("404_page");
    modelAndView.addObject("Type", ex.getMessage());
    return modelAndView;
  }
}