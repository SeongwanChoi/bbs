package dev.bbs.study.csw.dtos;

public class UserDto {
    public static final String NAME = "user";

    private final int index;
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
    private final int level;

    public UserDto(int index, String email, String password, String nickname,
                   String nameFirst, String nameOptional, String nameLast,
                   String contactFirst, String contactSecond, String contactThird,
                   String addressPost, String addressPrimary, String addressSecondary, int level) {
        this.index = index;
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
        this.level = level;
    }

    public static String getNAME() {
        return NAME;
    }

    public int getIndex() {
        return index;
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

    public int getLevel() {
        return level;
    }
}
