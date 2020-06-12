package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.pojo.Account;
import orz.doublexi.service.CommonService;
import orz.doublexi.utils.ServletUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CommonController {
    @Autowired
    CommonService commonService;
    @RequestMapping("index.do")
    public String index(){

        return "index";
    }

    @RequestMapping("tologin.do")
    public String toLogin(Model model,@RequestParam("location") String location){
        String[] s = location.split("_");
        switch (s[0]) {
            case "index":
                model.addAttribute("location", "index.do");
                break;
            case "video":
                model.addAttribute("location", "video.do?videoid=" + s[1]);
                break;
            case "myspace":
                model.addAttribute("location", "myspace.do");
                break;
            default:
                    break;

        }

        return "login";
    }
    @RequestMapping("myspace.do")
    public String mySpace(){
        if (ServletUtils.getSession().getAttribute("account") != null) {
            return "myspace";
        }
        return "index";
    }
    @RequestMapping("chat.do")
    public String chat(Model model){
        if (ServletUtils.getSession().getAttribute("account") != null) {
            model.addAttribute("req", "querychattarget.do");
            return "chat";
        }
        return "login";
    }
    @RequestMapping("chat_to.do")
    public String chatTo(Model model,@RequestParam("target")Integer target){
        if (ServletUtils.getSession().getAttribute("account") != null) {
            model.addAttribute("req", "chat_to_he.do?target="+target);
            return "chat";
        }
        return "login";
    }
    @RequestMapping("hisspace.do")
    public String hisSpace(@RequestParam("accountid") Integer accountid,Model model){
        Account account = commonService.queryAccontById(accountid.toString());
        model.addAttribute("his", account);
        return "hisspace";
    }
    @RequestMapping("tougao.do")
    public String tougao(Model model){
        Account account = (Account)ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            model.addAttribute("location","tougao.do");
            return "login";
        }

        return "tougao";
    }
    @RequestMapping("loginout.do")
    @ResponseBody
    public Object loginout(Model model){
        ServletUtils.getSession().removeAttribute("account");
        System.out.println("退出登录");
        HashMap hashMap = new HashMap();
        hashMap.put("value", true);
        return hashMap;
    }
    @RequestMapping("login.do")
    @ResponseBody
    public HashMap login(HttpServletRequest request){
        String accountid_or_name = request.getParameter("accountid_or_name");
        String password = request.getParameter("password");
        Account account = commonService.login(accountid_or_name, password);
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("msg","账号或密码错误" );
            hashMap.put("code","0" );
            return hashMap;
        }

        ServletUtils.getSession().setAttribute("account",account);

        HashMap hashMap = new HashMap();
        hashMap.put("msg", "登录成功");
        hashMap.put("code", "1");
        return hashMap;
    }
    @RequestMapping("regist.do")
    @ResponseBody
    public HashMap regist(HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        boolean b = commonService.queryHasNickname(nickname);
        if (b) {
            HashMap hashMap = new HashMap();
            hashMap.put("msg","注册失败,昵称重复" );
            hashMap.put("code","0" );
            return hashMap;
        }
        boolean flag=commonService.registNormal(nickname, password);
        if (!flag) {
            HashMap hashMap = new HashMap();
            hashMap.put("msg","注册失败" );
            hashMap.put("code","0" );
            return hashMap;
        }


        HashMap hashMap = new HashMap();
        hashMap.put("msg", "注册成功");
        hashMap.put("code", "1");
        return hashMap;
    }
}


