package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.util.CryptoUtil;

public class LoginVo {
    private final String email;
    private String password;
    private String hashedPassword;

    private boolean autoSign;
    private LoginResult loginResult;
    private UserDto userDto;

    public LoginVo(String email, String password) {
        this.email = email;
        setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public boolean isAutoSign() {
        return this.autoSign;
    }

    public void setAutoSign(boolean autoSign) {
        this.autoSign = autoSign;
    }

    public LoginResult getLoginResult() {
        return this.loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

    public UserDto getUserDto() {
        return this.userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
