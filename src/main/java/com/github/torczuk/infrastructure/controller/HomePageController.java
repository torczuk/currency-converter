package com.github.torczuk.infrastructure.controller;

import com.github.torczuk.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomePageController {

    @RequestMapping(value = "userId", method = RequestMethod.GET)
    public @ResponseBody String userId(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) {
            return null;
        }
        return String.valueOf(user.getId());
    }
}
