package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.util.CryptoUtil;

public class LoginVo {
    private final String email;
    private final String password;
    private final String hashedPassword;

    private final String submit;
    private final String ViewEmail;
    private final String ViewPassword;

    private LoginResult loginResult;
    private UserDto userDto;

    public LoginVo(String email, String password, String submit, String ViewEmail, String ViewPassword) {
        this.email = email;
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password, null);
        this.submit = submit;
        this.ViewEmail = ViewEmail;
        this.ViewPassword = CryptoUtil.Sha512.hash(ViewPassword, null);
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

    public String getViewEmail() {
        return ViewEmail;
    }

    public String getViewPassword() {
        return ViewPassword;
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
