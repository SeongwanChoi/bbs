package dev.bbs.study.csw.controllers.user;

import dev.bbs.study.csw.dtos.UserDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(UserDto.NAME)
public class RootController {
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String indexGet() {
        return "index";
    }
}
