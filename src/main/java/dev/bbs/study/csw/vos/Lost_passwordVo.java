package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.enums.Lost_emailSendCodeResult;
import dev.bbs.study.csw.enums.Lost_passwordSendCodeResult;

public class Lost_passwordVo {
    private final String email;
    private final String authCode;
    private final String key;
    private final String password;
    private final String hashedPassword;

    private String ip;
    private Lost_passwordSendCodeResult result;

    public Lost_passwordVo(String email, String authCode, String key, String password, String hashedPassword) {
        this.email = email;
        this.authCode = authCode;
        this.key = key;
        this.password = password;
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Lost_passwordSendCodeResult getResult() {
        return result;
    }

    public void setResult(Lost_passwordSendCodeResult result) {
        this.result = result;
    }
}
