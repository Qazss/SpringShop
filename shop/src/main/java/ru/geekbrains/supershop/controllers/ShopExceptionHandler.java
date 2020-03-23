package ru.geekbrains.supershop.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;

import org.springframework.web.client.HttpServerErrorException;
import ru.geekbrains.supershop.exceptions.ProductNotFoundException;

@Slf4j
@ControllerAdvice
public class ShopExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "404 - The Page can't be found");
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String handleInternalServerError(final HttpServerErrorException.InternalServerError ex, Model model) {
        model.addAttribute("errorMessage", "500 - Internal Server Error");
        return "error";
    }

}