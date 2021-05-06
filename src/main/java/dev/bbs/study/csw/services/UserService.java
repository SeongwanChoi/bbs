package dev.bbs.study.csw.services;

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

    public void login(LoginVo loginVo) {

    }

    public void register(RegisterVo registerVo) {
        if (this.userModel.selectEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        }
        if (this.userModel.selectNicknameCount(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        }
        if (this.userModel.selectContactCount(
                registerVo.getContactFirst(),
                registerVo.getContactSecond(),
                registerVo.getContactThird())> 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_CONTACT);
            return;
        }
        this.userModel.insertUser(registerVo);
        registerVo.setResult(RegisterResult.SUCCESS);
    }
}
