package dev.bbs.study.csw.vos.apis;

import dev.bbs.study.csw.vos.LoginVo;

public class CountVo {
    private final String field;
    private final String value;

    private LoginVo loginVo;

    public CountVo(String field, String value) {
        this.field = field;
        this.value = value;

    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public LoginVo getLoginVo() {
        return loginVo;
    }

    public void setLoginVo(LoginVo loginVo) {
        this.loginVo = loginVo;
    }
}
