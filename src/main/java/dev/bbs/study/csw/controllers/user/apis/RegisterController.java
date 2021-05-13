package dev.bbs.study.csw.controllers.user.apis;

import dev.bbs.study.csw.services.UserService;
import dev.bbs.study.csw.vos.apis.CountVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/apis/register",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/count")
    public String countPost(CountVo countVo) {
        switch (countVo.getField()) {
            case "e":
                return String.format("{\"count\":%d}", this.userService.getEmailCount(countVo.getValue()));
            case "n":
                return String.format("{\"count\":%d}", this.userService.getNicknameCount(countVo.getValue()));
            default:
                return "{}";
        }
    }
}
