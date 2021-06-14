package dev.bbs.study.csw.controllers.user;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.enums.RegisterResult;
import dev.bbs.study.csw.services.UserService;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.Lost_emailVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(UserDto.NAME)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(UserDto.NAME)
    private UserDto userDto() {
        return null;
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet(@ModelAttribute(UserDto.NAME) UserDto userDto) {
        if (userDto != null) {
            return "redirect:/";
        }
        return "user/register";
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost(
            @ModelAttribute(UserDto.NAME) UserDto userDto,
            RegisterVo registerVo,
            Model model) {
        if (userDto != null) {
            return "redirect:/";
        }
        this.userService.register(registerVo);
        if (registerVo.getResult() == RegisterResult.SUCCESS) {
            return "user/register.success";
        } else {
            model.addAttribute("registerVo", registerVo);
            return "user/register";
        }
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet(@ModelAttribute(UserDto.NAME) UserDto userDto) {
        if (userDto != null) {
            return "redirect:/";
        }
        return "user/login";
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginPost(
            HttpServletResponse response,
            @ModelAttribute(UserDto.NAME) UserDto userDto,
            LoginVo loginVo,
            Model model) {
        if (userDto != null) {
            return "redirect:/";
        }
        this.userService.login(loginVo);
        if (loginVo.getLoginResult() == LoginResult.SUCCESS) {
            if (loginVo.isAutoSign()) {
                this.userService.putAutoSignKey(loginVo.getUserDto());
                Cookie cookie = new Cookie("Autologin", loginVo.getUserDto().getAutoSignKey());
                cookie.setMaxAge(60 * 60 * 24 * UserService.Config.AUTO_SIGN_VALID_DAYS);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            model.addAttribute(UserDto.NAME, loginVo.getUserDto());
            return "redirect:/";
        } else {
            model.addAttribute("vo", loginVo);
            return "user/login";
        }
    }

    @RequestMapping(
            value = "/logout",
            method =RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String logoutGet(
            SessionStatus sessionStatus,
            @ModelAttribute(UserDto.NAME) UserDto userDto,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (userDto != null) {
            Cookie autoSignKeyCookie = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Autologin")) {
                    autoSignKeyCookie = cookie;
                    break;
                }
            }
            if (autoSignKeyCookie != null) {
            this.userService.expireAutoSignKey(autoSignKeyCookie);
            autoSignKeyCookie.setMaxAge(0);
            autoSignKeyCookie.setPath("/");
            response.addCookie(autoSignKeyCookie);
            }
        }
        sessionStatus.setComplete();
        return "redirect:/";
    }


    @RequestMapping(
            value = "lost_email",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String lostEmailGet(@ModelAttribute(UserDto.NAME) UserDto userDto) {
        if (userDto != null) {
            return "redirect:/";
        }
        return "user/lost_email";
    }

    @RequestMapping(
            value = "lost_email",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String lostEmailPost(
            @ModelAttribute(UserDto.NAME) UserDto userDto,
            HttpServletRequest request,
            Lost_emailVo lostEmailVo) {
        if (userDto != null) {
            return "redirect:/";
        }
        lostEmailVo.setIp(request.getRemoteAddr());
    }
}
