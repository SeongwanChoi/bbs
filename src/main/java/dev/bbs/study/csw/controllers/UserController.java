package dev.bbs.study.csw.controllers;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/user/")
@SessionAttributes(UserDto.NAME)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/user/",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(
            value = "/user/",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginPost() {
        return "login";
    }

    @RequestMapping(
            value = "/register/",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet() {
        return "register";
    }

    @RequestMapping(
            value = "/register/",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost() {
        return "register";
    }
}
