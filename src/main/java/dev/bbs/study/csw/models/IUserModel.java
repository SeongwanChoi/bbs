package dev.bbs.study.csw.models;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserModel {
    int selectEmailCount(@Param("email") String email); // 회원가입 - 이메일 중복검사

    int selectNicknameCount(@Param("nickname") String nickname); // 회원가입 - 닉네임 중복검사

    void insertUser(RegisterVo registerVo); // 회원가입

    UserDto selectUser(LoginVo loginVo); // 로그인

    void insertAutoSignKey(
            @Param("email") String email,
            @Param("key") String key,
            @Param("days") int days); // 자동로그인 활성화
}
