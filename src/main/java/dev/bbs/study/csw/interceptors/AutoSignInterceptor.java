package dev.bbs.study.csw.interceptors;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoSignInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Autowired
    public AutoSignInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(UserDto.NAME);
        UserDto userDto = userObj instanceof UserDto ? (UserDto) userObj : null;
        if (userDto == null && request.getCookies() != null) {
            Cookie autoSignKeyCookie = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Autologin")) {
                    autoSignKeyCookie = cookie;
                    break;
                }
            }
            if (autoSignKeyCookie != null) {
                userDto = this.userService.login(autoSignKeyCookie);
                if (userDto != null) {
                    session.setAttribute(UserDto.NAME, userDto);

                    this.userService.extendAutoSignKey(autoSignKeyCookie);
                    autoSignKeyCookie.setMaxAge(60 * 60 * 24 * UserService.Config.AUTO_SIGN_VALID_DAYS);
                    autoSignKeyCookie.setPath("/");
                    response.addCookie(autoSignKeyCookie);
                    if (request.getRequestURI().equals("/user/login") ||
                            request.getRequestURI().equals("/user/register")) {
                        response.sendRedirect("/");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
