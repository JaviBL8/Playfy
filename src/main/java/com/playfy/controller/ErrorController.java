package com.playfy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        HashMap<String, Object> error = new HashMap<>();
        HashMap<String, String> details = new HashMap<>();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                details.put("message: ", "We could not find the resource you requested.");
                details.put("description: ", "The reference set does not exist.");
                details.put("code: ", "404");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                details.put("message: ", "Unexpected internal server error.");
                details.put("description: ", "No route found");
                details.put("code: ", "500");
            }
        }

        return null;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
