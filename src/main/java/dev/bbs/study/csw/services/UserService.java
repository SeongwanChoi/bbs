package dev.bbs.study.csw.services;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.vos.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDto userDto;

    @Autowired
    public UserService(UserDto userDto) {
        this.userDto = userDto;
    }

    public void login(LoginVo loginVo) {

    }
}
