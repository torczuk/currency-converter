package com.github.torczuk.infrastructure.controller;

import com.github.torczuk.domain.service.UserService;
import com.github.torczuk.dto.SignUpDetails;
import com.github.torczuk.infrastructure.validator.SignUpDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired private UserService userService;
    @Autowired private SignUpDetailsValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String index() {
        return "register";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(
            @RequestBody @Valid SignUpDetails details,
            BindingResult bindingResult,
            final RedirectAttributes redirectAttributes,
            HttpServletResponse httpServletResponse
    ) {

        if(bindingResult.hasErrors()) {
            ResponseEntity<?> responseEntity = new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        userService.createUser(details);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
