package dev.bbs.study.csw.mappers;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.Lost_emailSendCodeVo;
import dev.bbs.study.csw.vos.Lost_passwordSendCodeVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int selectEmailCount(@Param("email") String email); // 회원가입 - 이메일 중복검사

    int selectNicknameCount(@Param("nickname") String nickname); // 회원가입 - 닉네임 중복검사

    void insertUser(RegisterVo registerVo); // 회원가입

    UserDto selectUser(LoginVo loginVo); // 로그인

    void insertAutoSignKey(
            @Param("email") String email,
            @Param("key") String key,
            @Param("days") int days); // 자동로그인 활성화

    UserDto selectUserFromCookie(
            @Param("key") String key); // 자동로그인 키 확인

    void updateAutoSignKeyDay(
            @Param("key") String key,
            @Param("days") int days); // 자동로그인 정상 사용시 유효기간 초기화

    void updateAutoSignKeyExpiry(
            @Param("key") String key); // 자동로그인 종료

    String selectEmail(Lost_emailSendCodeVo lostEmailSendCodeVo); // 이메일 찾아서(AuthCode로 씀)

    void insertLostEmailAuthCode( // 인증키로 이메일 찾음
            @Param("email") String email,
            @Param("code") String code,
            @Param("key") String key,
            @Param("ip") String ip,
            @Param("minutes") int minutes);

    String selectEmailByAuthCodeFromEmail( // 인증키로 이메일 찾기 줌
            @Param("authCode") String authCode,
            @Param("key") String key,
            @Param("ip") String ip);

    void updateEmailAuthCodeExpired( // 이메일 찾았으면 인증키 비활성화
            @Param("key") String key);

    int selectUserCount(     // 이메일로 계정 존재여부를 찾음
            @Param("email") String email,
            @Param("nameFirst") String nameFirst,
            @Param("nameLast") String nameLast,
            @Param("contactFirst") String contactFirst,
            @Param("contactSecond") String contactSecond,
            @Param("contactThird") String contactThird);

     String selectEmailByAuthCodeFromPassword( // passwordAuthCode 일치여부 확인
            @Param("email") String email,
            @Param("authCode") String authCode,
            @Param("key") String key,
            @Param("ip") String ip);

    void updatePasswordAuthCodeExpired(
            @Param("key") String key);

    void updateUserPassword(
            @Param("email") String email,
            @Param("password") String password);
}