package dev.bbs.study.csw.vos;

public class Lost_emailVo {
    private final String authCode;
    private final String key;

    private String ip;
    private String email;

    public Lost_emailVo(String authCode, String key) {
        this.authCode = authCode;
        this.key = key;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getKey() {
        return key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 0800764 (commit)
