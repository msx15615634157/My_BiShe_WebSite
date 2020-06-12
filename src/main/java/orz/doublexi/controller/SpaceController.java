package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.pojo.Account;
import orz.doublexi.service.CommonService;
import orz.doublexi.service.VideoService;
import orz.doublexi.utils.ServletUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SpaceController {

    @Autowired
    CommonService commonService;
    @Autowired
    VideoService videoService;
    @RequestMapping("my_upload_videos.do")
    @ResponseBody
    public Map myUploadViedos(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Account account =(Account) ServletUtils.getSession().getAttribute("account");
        return videoService.queryMyUploadVideos(account.getAccountid(),page,limit);

    }
    @RequestMapping("his_upload_videos.do")
    @ResponseBody
    public Map hisUploadViedos(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,@RequestParam("accountid") Integer accountid){
        return videoService.queryHisUploadVideos(accountid,page,limit);

    }
    @RequestMapping("delete_my_upload_video.do")
    @ResponseBody
    public Map deleteVideo(@RequestParam("videoid") Integer videoid){
        boolean flag = videoService.deleteVideoById(videoid);
        HashMap<String, Object> map = new HashMap<>();
        if (flag) {
            map.put("code", "0");
            map.put("msg", "删除成功");
            return map;
        }
        map.put("code", "1");
        map.put("msg", "删除失败");
        return map;



    }
    @RequestMapping("my_shoucang_videos.do")
    @ResponseBody
    public Map myShoucangViedos(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Account account =(Account) ServletUtils.getSession().getAttribute("account");
        return videoService.queryShoucangVideos(account.getAccountid(),page,limit);

    }
    @RequestMapping("my_guanzhu_accounts.do")
    @ResponseBody
    public Map myGuanzhuAccounts(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        Account account =(Account) ServletUtils.getSession().getAttribute("account");
        return videoService.queryGuanzhuAccounts(account.getAccountid(),page,limit);

    }
    @RequestMapping("modifyaccount.do")
    @ResponseBody
    public Map modifyAccountInfo(@RequestParam("nickname") String nickname,@RequestParam("desc") String desc){
        HashMap map = new HashMap();
        Account account =(Account) ServletUtils.getSession().getAttribute("account");
        Account account1 = commonService.queryAccontById(account.getAccountid().toString());
        boolean b;
        if (account1.getNickname().equals(nickname)) {
          b= videoService.modifyAccount(nickname, desc, account.getAccountid());
        }else{
            boolean b1 = commonService.queryHasNickname(nickname);
            if (b1) {
                map.put("code", "1");
                map.put("msg", "昵称重复");
                return map;
            }
             b = videoService.modifyAccount(nickname, desc, account.getAccountid());
        }





        if (b) {
            map.put("code", "0");
            map.put("msg", "修改成功");
            account.setNickname(nickname);
            account.setDesc(desc);
        }else{
            map.put("code", "1");
            map.put("msg", "修改失败");
        }
        return map;

    }
    @RequestMapping("modifypwd.do")
    @ResponseBody
    public Map modifyAccountPassword(@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd1") String newpwd){
        Account account =(Account) ServletUtils.getSession().getAttribute("account");
        HashMap map = new HashMap();
        if (!oldpwd.equals(account.getAccountpwd())) {
            map.put("code", "1");
            map.put("msg", "原密码输入有误");
            return map;
        }
        boolean b = videoService.modifyAccountPassword(newpwd, account.getAccountid());

        if (b) {
            map.put("code", "0");
            map.put("msg", "修改成功");
            account.setAccountpwd(newpwd);
        }else{
            map.put("code", "1");
            map.put("msg", "修改失败");
        }
        return map;

    }
    @RequestMapping("guanzhu_status.do")
    @ResponseBody
    public Map guanzhuStatus(@RequestParam(value = "be_accountid") int be_accountid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("guanzhu", 0);
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        Map map = videoService.queryButtonStatus(accountid, be_accountid);
        return map;

    }
}
