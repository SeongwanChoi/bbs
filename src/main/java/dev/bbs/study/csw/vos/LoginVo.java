package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.util.CryptoUtil;

public class LoginVo {
    private final String email;
    private final String password;


    public LoginVo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
