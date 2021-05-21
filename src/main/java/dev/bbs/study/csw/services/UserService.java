package dev.bbs.study.csw.services;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.enums.RegisterResult;
import dev.bbs.study.csw.models.IUserModel;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserModel userModel;


    @Autowired
    public UserService(IUserModel userModel) {
        this.userModel = userModel;
    }

    private static class Regex {
        public static final String EMAIL = "^(?=.{8,50}$)([0-9a-z]([_]?[0-9a-z])*?)@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
        public static final String PASSWORD = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'\",<.>/?]{4,100})$";
        public static final String NICKNAME = "^([0-9a-zA-Z가-힣]{2,10})$";
        public static final String NAME_FIRST = "^([가-힣]{1,10})$";
        public static final String NAME_LAST = "^([가-힣]{1,10})$";
        public static final String CONTACT_FIRST = "^(010|070)$";
        public static final String CONTACT_SECOND = "^([0-9]{4})$";
        public static final String CONTACT_THIRD = "^([0-9]{4})$";
        public static final String ADDRESS_POST = "^([0-9]{5})$";
        public static final String ADDRESS_PRIMARY = "^([0-9a-zA-Z가-힣\\- ]{10,100})$";
    }



    public static boolean checkEmail(String email) {
        return email.matches(Regex.EMAIL);
    }
    public static boolean checkPassword (String password) { return password.matches(Regex.PASSWORD); }
    public static boolean checkNickname(String nickname) {
        return nickname.matches(Regex.NICKNAME);
    }
    public static boolean checkNameFirst(String nameFirst) {
        return nameFirst.matches(Regex.NAME_FIRST);
    }
    public static boolean checkNameLast(String nameLast) {
        return nameLast.matches(Regex.NAME_LAST);
    }
    public static boolean checkContactFirst(String contactFirst) {
        return contactFirst.matches(Regex.CONTACT_FIRST);
    }
    public static boolean checkContactSecond(String contactSecond) {
        return contactSecond.matches(Regex.CONTACT_SECOND);
    }
    public static boolean checkContactThird(String contactThird) {
        return contactThird.matches(Regex.CONTACT_THIRD);
    }

    public static boolean checkAddressPost(String addressPost) {
        return addressPost.matches(Regex.ADDRESS_POST);
    }
    public static boolean checkAddressPrimary(String addressPrimary) {
        return addressPrimary.matches(Regex.ADDRESS_PRIMARY);
    }

    public void login(LoginVo loginVo) {
        if (!UserService.checkEmail(loginVo.getEmail()) ||
            !UserService.checkPassword(loginVo.getPassword())) {
            loginVo.setLoginResult(LoginResult.FAILURE);
            return;
        }
        UserDto userDto = this.userModel.selectUser(loginVo);
        if (userDto == null) {
            loginVo.setLoginResult(LoginResult.NONE);
            return;
        }
        if (userDto.getLevel() == 10) {
            loginVo.setLoginResult(LoginResult.UNAVAILABLE);
            return;
        }
        loginVo.setLoginResult(LoginResult.SUCCESS);
        loginVo.setUserDto(userDto);
        return;
    }

    public void register(RegisterVo registerVo) {
        if (!UserService.checkEmail(registerVo.getEmail()) ||
                !UserService.checkPassword(registerVo.getPassword()) ||
                !UserService.checkNickname(registerVo.getNickname()) ||
                !UserService.checkNameFirst(registerVo.getNameFirst()) ||
                !UserService.checkNameLast(registerVo.getNameLast()) ||
                !UserService.checkContactFirst(registerVo.getContactFirst()) ||
                !UserService.checkContactSecond(registerVo.getContactSecond()) ||
                !UserService.checkContactThird(registerVo.getContactThird()) ||
                !UserService.checkAddressPost(registerVo.getAddressPost()) ||
                !UserService.checkAddressPrimary(registerVo.getAddressPrimary())){
            registerVo.setResult(RegisterResult.FAILURE);
            return;
        }
        if (this.getEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        }
        if (this.getNicknameCount(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        }
        this.userModel.insertUser(registerVo);
        registerVo.setResult(RegisterResult.SUCCESS);
    }

    public int getEmailCount(String email) {
        return this.userModel.selectEmailCount(email);
    }

    public int getNicknameCount(String nickname){
        return this.userModel.selectNicknameCount(nickname);
    }



}
