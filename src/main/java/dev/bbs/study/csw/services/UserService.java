package dev.bbs.study.csw.services;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.enums.RegisterResult;
import dev.bbs.study.csw.models.IUserModel;
import dev.bbs.study.csw.util.CryptoUtil;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    public static class Config {
        public static final int AUTO_SIGN_KEY_HASH_COUNT = 10; //
        public static final int AUTO_SIGN_VALID_DAYS = 7; // 자동로그인 최대 적용기간
    }

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

        public static final String AUTO_SIGN_KEY = "^([0-9a-z]{128})$";
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

    public void login(LoginVo loginVo) { // 로그인
        if (loginVo.getEmail().equals("")) {
            loginVo.setLoginResult(LoginResult.EMAILBlank);
            return;
        } // 이메일 공백확인
        if (loginVo.getPassword().equals("")) {
            loginVo.setLoginResult(LoginResult.PASSWORDBlank);
            return;
        } // 패스워드 공백확인
        if (!UserService.checkEmail(loginVo.getEmail()) ||
            !UserService.checkPassword(loginVo.getPassword())) {
            loginVo.setLoginResult(LoginResult.FAILURE);
            return;
        } // 이메일, 패스워드 정규식 검사
        UserDto userDto = this.userModel.selectUser(loginVo);
        if (userDto == null) {
            loginVo.setLoginResult(LoginResult.NONE);
            return;
        } // 계정 없을경우 결과처리
        if (userDto.getLevel() == 10) {
            loginVo.setLoginResult(LoginResult.UNAVAILABLE);
            return;
        } // 계정 상태확인후 결과처리
        loginVo.setLoginResult(LoginResult.SUCCESS);
        loginVo.setUserDto(userDto);
        return;
    } // 승인될경우 승인결과 (SUCCESS) 넣어주고 DTO에 있는 유저정보(본인) 넘겨줌

    public UserDto login(Cookie autoSignKeyCookie) {
        if (!autoSignKeyCookie.getValue().matches(Regex.AUTO_SIGN_KEY)) {
            return null;
        }
        UserDto userDto = this.userModel.selectUserFromCookie(autoSignKeyCookie.getValue());
        if (userDto == null || userDto.getLevel() == 10) {
            return null;
        }
        return userDto;
    }

    public void putAutoSignKey(UserDto userDto) { // 자동로그인 최초확인
        String key = String.format("%s%s%s%f",
                userDto.getEmail(),
                userDto.getPassword(),
                new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()),
                Math.random()); // 자동로그인 키 생성
        for (int i = 0; i < Config.AUTO_SIGN_KEY_HASH_COUNT; i++) {
            key = CryptoUtil.Sha512.hash(key, null);
        } // 자동로그인 키 해쉬화
        this.userModel.insertAutoSignKey(userDto.getEmail(), key, Config.AUTO_SIGN_VALID_DAYS);
        userDto.setAutoSignKey(key);
    }

    public void register(RegisterVo registerVo) { // 회원가입
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
        } // 정규식 검사
        if (this.getEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        } // 이메일 중복검사값으로 결과처리
        if (this.getNicknameCount(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        } // 닉네임 중복검사값으로 결과처리
        this.userModel.insertUser(registerVo);
        registerVo.setResult(RegisterResult.SUCCESS);
    } // 최종확인 이후 결과값 "SUCCESS"넣어줌 + DB에서 적은 정보(registerVo)로 insert 시켜줌

    public int getEmailCount(String email) {
        return this.userModel.selectEmailCount(email);
    } // DB에서 이메일 중복검사 (0 or 1)

    public int getNicknameCount(String nickname) {
        return this.userModel.selectNicknameCount(nickname);
    } // DB에서 닉네임 중복검사 (0 or 1)
}
