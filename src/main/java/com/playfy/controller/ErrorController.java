package com.playfy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    /*@RequestMapping("/error")
    public HashMap<String, Object> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        HashMap<String, Object> error = new HashMap<>();
        HashMap<String, String> details = new HashMap<>();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                details.put("code: ", "1");
                details.put("message: ", "We could not find the resource you requested.");
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                details.put("code: ", "2");
                details.put("message: ", "Unexpected internal server error.");
            }
        }

        error.put("status: ", "error");
        error.put("details",  details);

        return error;
    }
*/
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "Error";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
