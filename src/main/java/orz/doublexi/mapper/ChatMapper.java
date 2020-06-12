package orz.doublexi.mapper;

import org.apache.ibatis.annotations.Param;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.ChatMsg;

import java.util.List;

public interface ChatMapper {
    List<Account> queryChatTargetList(@Param("accountid") Integer accountid);

    List<ChatMsg> queryChatMessageList(@Param("accountid")Integer accountid,@Param("target")Integer target);

    int addChatMsg(@Param("msg")String msg, @Param("accountid")Integer accountid, @Param("target")Integer target);
}
