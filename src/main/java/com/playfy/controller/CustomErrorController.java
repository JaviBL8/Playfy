package com.playfy.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public HashMap<String,String> error() {
        HashMap<String,String> status = new HashMap<>();
        status.put("status: ", "error");
        status.put("message: ", "Route does not exists");
        return status;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}