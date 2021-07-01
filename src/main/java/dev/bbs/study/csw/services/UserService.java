package dev.bbs.study.csw.services;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.enums.LoginResult;
import dev.bbs.study.csw.enums.Lost_emailSendCodeResult;
import dev.bbs.study.csw.enums.Lost_passwordSendCodeResult;
import dev.bbs.study.csw.enums.RegisterResult;
import dev.bbs.study.csw.mappers.IUserMapper;
import dev.bbs.study.csw.util.CryptoUtil;
import dev.bbs.study.csw.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    public static class Config {
        public static final int AUTO_SIGN_KEY_HASH_COUNT = 10; // 해쉬암호화 재조정 횟수 (보안)
        public static final int AUTO_SIGN_VALID_DAYS = 7; // 자동로그인 최대 적용기간

        public static final int AUTH_CODE_HASH_COUNT = 9; // email찾기 기능 auth코드 생성 해쉬 재조정 횟수
        public static final int AUTH_CODE_VALID_MINUTES = 5; // 찾기 인증번호 최대사용시간
    }

    private final IUserMapper userMapper; // 모델 연결

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private static class Regex { // 정규식들
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

        public static final String AUTH_CODE_KEY = "^([0-9a-z]{128})$";
        public static final String AUTH_CODE = "^([0-9]{6})$";
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

    public static boolean checkAuthCodeKey(String authCodeKey) {
        return authCodeKey.matches(Regex.AUTH_CODE_KEY);
    }
    public static boolean checkAuth(String authCode) {
        return authCode.matches(Regex.AUTH_CODE);
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
        UserDto userDto = this.userMapper.selectUser(loginVo);
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
    } // 승인될경우 승인결과 (SUCCESS) 넣어주고 Dto에 있는 유저정보(본인) 넘겨줌

    public UserDto login(Cookie autoSignKeyCookie) { // 자동로그인 사용
        if (!autoSignKeyCookie.getValue().matches(Regex.AUTO_SIGN_KEY)) {
            return null; // key 정규식 검사
        } // 키 값 정규식 확인
        UserDto userDto = this.userMapper.selectUserFromCookie(autoSignKeyCookie.getValue()); // DB에서 확인될경우 DTO를 가져옴
        if (userDto == null || userDto.getLevel() == 10) { // DTO가 null이거나(유저정보 없음) || Level값이 10이상(잠금유저)
            return null; // 접근 거부
        }
        return userDto; // 그렇지 않을경우 유저정보 반환
    }

    public void putAutoSignKey(UserDto userDto) { // 자동로그인 최초확인
        String key = String.format("%s%s%s%f",
                userDto.getEmail(),
                userDto.getPassword(),
                new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()),
                Math.random()); // 자동로그인 키 생성
        for (int i = 0; i < Config.AUTO_SIGN_KEY_HASH_COUNT; i++) {
            key = CryptoUtil.Sha512.hash(key);
        } // 자동로그인 키 해쉬화
        this.userMapper.insertAutoSignKey(userDto.getEmail(), key, Config.AUTO_SIGN_VALID_DAYS); // 만든 키를 DB에 생성
        userDto.setAutoSignKey(key); // 최종적으로 해쉬화로 생성된 키를 DTO의 AutosignKey로 set 시킴
    }

    public void extendAutoSignKey(Cookie autoSignKeyCookie) { // 자동로그인 만료기간 업데이트
        if (!autoSignKeyCookie.getValue().matches(Regex.AUTO_SIGN_KEY)) {
            return;
        } // 키 값 정규식 확인
        this.userMapper.updateAutoSignKeyDay(autoSignKeyCookie.getValue(), Config.AUTO_SIGN_VALID_DAYS); // 통과한 키 값의 유효기간 업데이트
    }

    public void expireAutoSignKey(Cookie autoSignKeyCookie) { // 자동로그인 종료 업데이트
        if (!autoSignKeyCookie.getValue().matches(Regex.AUTO_SIGN_KEY)) {
            return;
        } // 키 값 정규식 확인
        this.userMapper.updateAutoSignKeyExpiry(autoSignKeyCookie.getValue()); // 통과한 경우 expired_flag 를 1로 만들어 사용 종료시킴
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
        this.userMapper.insertUser(registerVo);
        registerVo.setResult(RegisterResult.SUCCESS);
    } // 최종확인 이후 결과값 "SUCCESS"넣어줌 + DB에서 적은 정보(registerVo)로 insert 시켜줌

    public int getEmailCount(String email) {
        return this.userMapper.selectEmailCount(email);
    } // DB에서 이메일 중복검사 (0 or 1)

    public int getNicknameCount(String nickname) {
        return this.userMapper.selectNicknameCount(nickname);
    } // DB에서 닉네임 중복검사 (0 or 1)

    public void sendE (Lost_emailSendCodeVo lostEmailSendCodeVo) { // 이메일 찾기 인증번호 만드는 과정 (1)
        if (!UserService.checkNameFirst(lostEmailSendCodeVo.getNameFirst()) ||
                !UserService.checkNameLast(lostEmailSendCodeVo.getNameLast()) ||
                !UserService.checkContactFirst(lostEmailSendCodeVo.getContactFirst()) ||
                !UserService.checkContactSecond(lostEmailSendCodeVo.getContactSecond()) ||
                !UserService.checkContactThird(lostEmailSendCodeVo.getContactThird())) {
            lostEmailSendCodeVo.setResult(Lost_emailSendCodeResult.FAILURE);
            return;
        } // 받은정보 정규식 검사
        String email = this.userMapper.selectEmail(lostEmailSendCodeVo); // 일단 이메일 찾음
        if (email == null) {
            lostEmailSendCodeVo.setResult(Lost_emailSendCodeResult.FAILURE);
        } // 없으면 실패
        String code = String.valueOf((int) (Math.random() * Math.pow(10, 6))); // 랜덤한 6자리 숫자 (인증코드)
        String key = String.format("%s|%s", email, code); // email+code 줄지어서 키 값 만듬
        for (int i = 0; i < Config.AUTH_CODE_HASH_COUNT; i++) {
            key = CryptoUtil.Sha512.hash(key);
        } // 키 값 해쉬화 (9회)
        this.userMapper.insertLostEmailAuthCode(email, code, key, lostEmailSendCodeVo.getIp(), Config.AUTH_CODE_VALID_MINUTES); // email 찾기위한 인증코드 만듬
        lostEmailSendCodeVo.setKey(key); // vo에서 key값 들고있음
        lostEmailSendCodeVo.setCode(code); // code를 보여줘야해서 현제 어쩔수없음(후일 방식변경필요)
        lostEmailSendCodeVo.setResult(Lost_emailSendCodeResult.SENT); // 모든 과정 정상수행 후 SENT 반환
    }

    public void findEmail(Lost_emailVo lostEmailVo) { // 이메일 찾아주는 과정 (2)
        if (!UserService.checkAuth(lostEmailVo.getAuthCode()) ||
                !UserService.checkAuthCodeKey(lostEmailVo.getKey())) {
            return;
        } // 인증키와 키값 정규식 검사
        String email = this.userMapper.selectEmailByAuthCodeFromEmail(
                lostEmailVo.getAuthCode(),
                lostEmailVo.getKey(),
                lostEmailVo.getIp()); // 이제 진짜 찾아줄 email 찾음
        if (email != null) { // 찾는데 성공하면 인증키 만료시킴
            this.userMapper.updateEmailAuthCodeExpired(lostEmailVo.getKey());
        }
        System.out.println(email);
        lostEmailVo.setEmail(email); // vo에 email을 담아서 보내줌
    }

    public void sendP (Lost_passwordSendCodeVo lostPasswordSendCodeVo) { // 비밀번호 인증코드 만드는과정(1)
        if (!UserService.checkEmail(lostPasswordSendCodeVo.getEmail()) ||
                !UserService.checkNameFirst(lostPasswordSendCodeVo.getNameFirst()) ||
                !UserService.checkNameLast(lostPasswordSendCodeVo.getNameLast()) ||
                !UserService.checkContactFirst(lostPasswordSendCodeVo.getContactFirst()) ||
                !UserService.checkContactSecond(lostPasswordSendCodeVo.getContactSecond()) ||
                !UserService.checkContactThird(lostPasswordSendCodeVo.getContactThird())) {
            lostPasswordSendCodeVo.setResult(Lost_passwordSendCodeResult.FAILURE);
            return;
        } // 정규식 검사
        int count = this.userMapper.selectUserCount( // 받은 정보를 토대로 이메일이 존재하는지 확인 (0 or 1)
                lostPasswordSendCodeVo.getEmail(),
                lostPasswordSendCodeVo.getNameFirst(),
                lostPasswordSendCodeVo.getNameLast(),
                lostPasswordSendCodeVo.getContactFirst(),
                lostPasswordSendCodeVo.getContactSecond(),
                lostPasswordSendCodeVo.getContactThird());
        if (count == 0) { // 없으면 실패
            lostPasswordSendCodeVo.setResult(Lost_passwordSendCodeResult.FAILURE);
            return;
        }
        String code = String.valueOf((int) (Math.random() * Math.pow(10, 6))); // 랜덤한 6자리 숫자 (인증코드)
        String key = String.format("%s|%s%s%s|%s%f",
                lostPasswordSendCodeVo.getEmail(),
                lostPasswordSendCodeVo.getContactFirst(),
                lostPasswordSendCodeVo.getContactSecond(),
                lostPasswordSendCodeVo.getContactThird(),
                code,
                Math.random()); // 키 값 만듬
        for (int i = 0; i < Config.AUTH_CODE_HASH_COUNT; i++) {
            key = CryptoUtil.Sha512.hash(key);
        } // 키 값 해쉬화 (9회)
        lostPasswordSendCodeVo.setCode(code); // 만들어진 code 값 넣어줌
        lostPasswordSendCodeVo.setKey(key); // 만들어진 key 값 넣어줌
        lostPasswordSendCodeVo.setResult(Lost_passwordSendCodeResult.SENT); // 인증코드 생성 성공결과로 SENT본냄
    }

    public void newPassword(Lost_passwordVo lostPasswordVo) {
        if (!UserService.checkEmail(lostPasswordVo.getEmail()) ||
                !UserService.checkAuth(lostPasswordVo.getAuthCode()) ||
                !UserService.checkAuthCodeKey(lostPasswordVo.getKey())) {
            lostPasswordVo.setResult(Lost_passwordSendCodeResult.FAILURE);
            return;
        }
        String email = this.userMapper.selectEmailByAuthCodeFromPassword(
                lostPasswordVo.getEmail(),
                lostPasswordVo.getAuthCode(),
                lostPasswordVo.getKey(),
                lostPasswordVo.getIp());
        if (email == null) {
            lostPasswordVo.setResult(Lost_passwordSendCodeResult.FAILURE);
        }
        this.userMapper.updatePasswordAuthCodeExpired(lostPasswordVo.getKey());
        this.userMapper.updateUserPassword(email, lostPasswordVo.getHashedPassword());
        lostPasswordVo.setResult(Lost_passwordSendCodeResult.SUCCESS);
    }

}
