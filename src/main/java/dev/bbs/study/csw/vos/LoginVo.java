package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.util.CryptoUtil;

public class LoginVo {
    private final String email;
    private final String password;
    private final String hashedPassword;

    private final String submit;

    private LoginResult loginResult;
    private UserDto userDto;

    public LoginVo(String email, String password, String submit) {
        this.email = email;
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password, null);
        this.submit = submit;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSubmit() {
        return submit;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
