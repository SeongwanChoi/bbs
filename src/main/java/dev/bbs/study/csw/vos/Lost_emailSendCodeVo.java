package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.enums.Lost_emailSendCodeResult;

public class Lost_emailSendCodeVo {
    private final String nameFirst;
    private final String nameOptional;
    private final String nameLast;
    private final String contactFirst;
    private final String contactSecond;
    private final String contactThird;

    private String key;
    private String ip;
    private Lost_emailSendCodeResult result;

    public Lost_emailSendCodeVo(String nameFirst, String nameOptional, String nameLast, String contactFirst, String contactSecond, String contactThird) {
        this.nameFirst = nameFirst;
        this.nameOptional = nameOptional;
        this.nameLast = nameLast;
        this.contactFirst = contactFirst;
        this.contactSecond = contactSecond;
        this.contactThird = contactThird;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameOptional() {
        return nameOptional;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
