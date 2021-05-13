package dev.bbs.study.csw.vos;

import dev.bbs.study.csw.enums.RegisterResult;
import dev.bbs.study.csw.util.CryptoUtil;

public class RegisterVo {
    private final String email;
    private final String password;
    private final String nickname;
    private final String nameFirst;
    private final String nameOptional;
    private final String nameLast;
    private final String contactFirst;
    private final String contactSecond;
    private final String contactThird;
    private final String addressPost;
    private final String addressPrimary;
    private final String addressSecondary;
    private final String hashedPassword;


    private RegisterResult result;

    public RegisterVo(String email, String password, String nickname, String nameFirst,
                      String nameOptional, String nameLast, String contactFirst, String contactSecond,
                      String contactThird, String addressPost, String addressPrimary,
                      String addressSecondary) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.nameFirst = nameFirst;
        this.nameOptional = nameOptional;
        this.nameLast = nameLast;
        this.contactFirst = contactFirst;
        this.contactSecond = contactSecond;
        this.contactThird = contactThird;
        this.addressPost = addressPost;
        this.addressPrimary = addressPrimary;
        this.addressSecondary = addressSecondary;
        this.hashedPassword = CryptoUtil.Sha512.hash(password, null);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
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

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public RegisterResult getResult() {
        return result;
    }

    public void setResult(RegisterResult result) {
        this.result = result;
    }
}
