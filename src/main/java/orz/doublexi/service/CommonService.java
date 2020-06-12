package orz.doublexi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.doublexi.mapper.CommonMapper;
import orz.doublexi.pojo.Account;
@Service
public class CommonService {
    @Autowired
    CommonMapper commonMapper;

    public Account login(String account, String password) {
        Account accountByIdAndPassword = commonMapper.getAccountByIdAndPassword(account, password);
        if (accountByIdAndPassword != null) {
            System.out.println(accountByIdAndPassword);
            return accountByIdAndPassword;
        }

        return commonMapper.getAccountByNicknameAndPassword(account,password);
    }

    public Account queryAccontById(String accountid) {
        return commonMapper.getAccountById(accountid);
    }
    public boolean registNormal(String nickname,String pwd){
        return commonMapper.regist(nickname, pwd, 0)!=0;
    }
    public boolean registAdmin(String nickname,String pwd){
       return commonMapper.regist(nickname, pwd, 1)!=0;
    }

    public boolean queryHasNickname(String nickname) {
       return commonMapper.getAccountByNickname(nickname)!=null;
    }
}
