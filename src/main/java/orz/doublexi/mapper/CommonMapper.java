package orz.doublexi.mapper;

import org.apache.ibatis.annotations.Param;
import orz.doublexi.pojo.Account;

public interface CommonMapper {
     Account getAccountById(@Param("accountid") String accountid);
     Account getAccountByNickname(@Param("nickname") String nickname);

    Account getAccountByNicknameAndPassword(@Param("nickname") String nickname, @Param("accountpwd")String password);
    Account getAccountByIdAndPassword(@Param("accountid") String accountid, @Param("accountpwd")String password);
    int regist(@Param("nickname") String nickname,@Param("accountpwd")String password,@Param("isadmin") Integer isadmin);

    int modifyAccountById(@Param("nickname")String nickname, @Param("desc")String desc,@Param("accountid") Integer accountid);

    int modifyAccountPassword(@Param("accountpwd")String accountpwd, @Param("accountid")Integer accountid);
}
