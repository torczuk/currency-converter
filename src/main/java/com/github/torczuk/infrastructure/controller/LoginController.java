package com.github.torczuk.infrastructure.controller;

import com.github.torczuk.dto.LoginCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value="/login",
            method= RequestMethod.POST
    )
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
