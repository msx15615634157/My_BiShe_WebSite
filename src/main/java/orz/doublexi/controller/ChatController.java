package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.pojo.Account;
import orz.doublexi.service.ChatService;
import orz.doublexi.service.CommonService;
import orz.doublexi.utils.ServletUtils;

import java.util.*;

@Controller
public class ChatController {
    public static HashMap<Integer,HashMap> messageBox = new HashMap();
    @Autowired
    ChatService chatService;
    @Autowired
    CommonService commonService;

    @RequestMapping("querychattarget.do")
    @ResponseBody
    public HashMap queryChatTarget(){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        return chatService.queryChatTarget(account.getAccountid());
    }
    @RequestMapping("chat_to_he.do")
    @ResponseBody
    public HashMap chatToHe(@RequestParam("target")Integer target){
        Account t = commonService.queryAccontById(target.toString());
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        HashMap hashMap = chatService.queryChatTarget(account.getAccountid());
        List<Account> accounts = (List)hashMap.get("accounts");
        Iterator<Account> iterator = accounts.iterator();
        for (; iterator.hasNext(); ) {
            Account next = iterator.next();
            if (next.getAccountid() .equals(t.getAccountid()) ) {
                iterator.remove();
            }
        }
        accounts.add(0,t);
        return hashMap;
    }
    @RequestMapping("querymsg.do")
    @ResponseBody
    public HashMap queryChatMessage(@RequestParam("target") Integer target){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        HashMap map = new HashMap();
        ArrayList list = new ArrayList();
        map.put(target, list);
        map.put("date", new Date());
        messageBox.put(account.getAccountid(), map);
        return chatService.queryChatMessage(account.getAccountid(),target);

    }
    @RequestMapping("addmsg.do")
    @ResponseBody
    public HashMap addMessage( @RequestParam("msg") String msg, @RequestParam("target")Integer target) {
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        boolean b = chatService.addMsg(msg, account.getAccountid(), target);
        HashMap hashMap = messageBox.get(target);
        if (hashMap != null && hashMap.get(account.getAccountid()) != null) {
            List list = (List)hashMap.get(account.getAccountid());
            list.add(msg);
        }
        HashMap map = new HashMap();
        if (b) {
           map.put("code", "0");
        }else {
            map.put("code", "1");
        }
        return map;
    }
    @RequestMapping("update.do")
    @ResponseBody
    public List updateMessage( @RequestParam("target")Integer target) {
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        HashMap hashMap = messageBox.get(account.getAccountid());
        List list = (List)hashMap.get(target);
        if (list.size() > 0) {
            hashMap.put(target, new ArrayList<>());
            return list;

        }


        return null;
    }

}


