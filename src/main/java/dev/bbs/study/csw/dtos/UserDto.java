package dev.bbs.study.csw.dtos;


public class UserDto {
    public static final String NAME = "user";

    private final int index;
    private final String email;
    private final String password;
    private final String nickname;
    private final String nameF;
    private final String nameO;
    private final String nameL;
    private final String contactF;
    private final String contactS;
    private final String contactT;
    private final String addressPost;
    private final String addressPri;
    private final String addressSecon;

    public UserDto(int index, String email, String password, String nickname, String nameF, String nameO, String nameL,
                   String contactF, String contactS, String contactT, String addressPost, String addressPri, String addressSecon) {
        this.index = index;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.nameF = nameF;
        this.nameO = nameO;
        this.nameL = nameL;
        this.contactF = contactF;
        this.contactS = contactS;
        this.contactT = contactT;
        this.addressPost = addressPost;
        this.addressPri = addressPri;
        this.addressSecon = addressSecon;
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

    public String getNameF() {
        return nameF;
    }

    public String getNameO() {
        return nameO;
    }

    public String getNameL() {
        return nameL;
    }

    public String getContactF() {
        return contactF;
    }

    public String getContactS() {
        return contactS;
    }

    public String getContactT() {
        return contactT;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddressPri() {
        return addressPri;
    }

    public String getAddressSecon() {
        return addressSecon;
    }
}
