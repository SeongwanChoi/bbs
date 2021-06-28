package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.enums.Lost_passwordSendCodeResult;

public class Lost_passwordSendCodeVo {
    private final String email;
    private final String nameFirst;
    private final String nameLast;
    private final String contactFirst;
    private final String contactSecond;
    private final String contactThird;

    private String ip;
    private String code;
    private String key;
    private Lost_passwordSendCodeResult result;

    public Lost_passwordSendCodeVo(String email, String nameFirst, String nameLast, String contactFirst, String contactSecond, String contactThird) {
        this.email = email;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.contactFirst = contactFirst;
        this.contactSecond = contactSecond;
        this.contactThird = contactThird;
    }

    public String getEmail() {
        return email;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getContactFirst() {
        return contactFirst;
    }

    public String getContactSecond() {
        return contactSecond;
    }

    public String getContactThird() {
        return contactThird;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Lost_passwordSendCodeResult getResult() {
        return result;
    }

    public void setResult(Lost_passwordSendCodeResult result) {
        this.result = result;
    }
}
