package dev.bbs.study.csw.models;

import dev.bbs.study.csw.dtos.UserDto;
import dev.bbs.study.csw.vos.LoginVo;
import dev.bbs.study.csw.vos.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserModel {
    int selectEmailCount(@Param("email") String email);

    int selectNicknameCount(@Param("nickname") String nickname);

    int selectContactCount(
            @Param("contactFirst") String contactFirst,
            @Param("contactSecond") String contactSecond,
            @Param("contactThird") String contactThird);

    void insertUser(RegisterVo registerVo);

    UserDto selectUser(LoginVo loginVo);
}
