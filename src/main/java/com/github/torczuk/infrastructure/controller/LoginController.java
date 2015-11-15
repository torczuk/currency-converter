package com.github.torczuk.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",
        method = RequestMethod.GET
    )
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("error") != null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "login";
    }
}
