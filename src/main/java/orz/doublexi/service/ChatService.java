package orz.doublexi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.doublexi.mapper.ChatMapper;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.ChatMsg;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatMapper chatMapper;

    public HashMap queryChatTarget(Integer accountid) {
        List<Account> accounts = chatMapper.queryChatTargetList(accountid);
        HashMap hashMap = new HashMap();
        hashMap.put("accounts", accounts);
        return hashMap;
    }

    public HashMap queryChatMessage(Integer accountid, Integer target) {
        List<ChatMsg> accounts = chatMapper.queryChatMessageList(accountid,target);
        HashMap hashMap = new HashMap();
        hashMap.put("msgs", accounts);
        return hashMap;
    }

    public boolean addMsg(String msg, Integer accountid, Integer target) {
        return chatMapper.addChatMsg(msg, accountid, target)>0;
    }
}
