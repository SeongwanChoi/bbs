package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.enums.Lost_emailSendCodeResult;

public class Lost_passwordVo {
    private final String authCode;
    private final String key;
    private final String password;
    private final String hashedPassword;

    private String ip;
    private Lost_emailSendCodeResult result;

    public Lost_passwordVo(String authCode, String key, String password, String hashedPassword) {
        this.authCode = authCode;
        this.key = key;
        this.password = password;
        this.hashedPassword = hashedPassword;
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

    public Lost_emailSendCodeResult getResult() {
        return result;
    }

    public void setResult(Lost_emailSendCodeResult result) {
        this.result = result;
    }
}
