package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.util.CryptoUtil;

public class LoginVo {
    private final String email;
    private final String password;
    private final String hashedPassword;

    public LoginVo(String email, String password) {
        this.email = email;
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password, null);
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
}
