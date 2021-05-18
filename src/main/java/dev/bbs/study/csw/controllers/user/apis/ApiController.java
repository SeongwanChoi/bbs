package dev.bbs.study.csw.controllers.user.apis;

import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.services.UserService;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.apis.CountVo;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/apis",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {
    private final UserService userService;

    public ApiController(UserService userService) {
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

    @RequestMapping(value = "/select")
    public String selectPost(LoginVo loginVo) {
        this.userService.login(loginVo);
        JSONObject jsonObject = new JSONObject();
        if (loginVo.getLoginResult() == LoginResult.NONE) {
            jsonObject.put("result", loginVo.getLoginResult());
            return jsonObject.toString(4);
        } else {
            return null;
        }
    }
}
