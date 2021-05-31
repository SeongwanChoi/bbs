package dev.bbs.study.csw.interceptors;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoSingInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Autowired
    public AutoSingInterceptor(UserService userService) {
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
                if (cookie.getName().equals("ask")) {
                    autoSignKeyCookie = cookie;
                    break;
                }
            }
        }
        return true;
    }
}
