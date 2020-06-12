package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.Comment;
import orz.doublexi.pojo.Video;
import orz.doublexi.service.CommonService;
import orz.doublexi.service.VideoService;
import orz.doublexi.utils.ServletUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    @Autowired
    CommonService commonService;
    @Autowired
    VideoService videoService;
    @RequestMapping("video.do")
    public String queryVideo(@RequestParam(value = "videoid") int index, Model model) {
        Video video = videoService.queryVideoById(index);
        Account account = commonService.queryAccontById(video.getVideoowner().toString());
        model.addAttribute("video", video);
        model.addAttribute("owner", account);

        return "video";
    }
    @RequestMapping("dianzan.do")
    @ResponseBody
    public HashMap dianzan(@RequestParam(value = "videoid") int videoid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        boolean dianzan = videoService.dianzan(videoid, accountid);
        return commonResponseMethod(dianzan);

    }
    @RequestMapping("quxiao_dianzan.do")
    @ResponseBody
    public HashMap quxiaoDianzan(@RequestParam(value = "videoid") int videoid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        boolean dianzan = videoService.quxiaoDianzan(videoid, accountid);
        return commonResponseMethod(dianzan);

    }
    @RequestMapping("shoucang.do")
    @ResponseBody
    public HashMap shoucang(@RequestParam(value = "videoid") int videoid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        boolean shoucang = videoService.shoucang(videoid, accountid);
        return commonResponseMethod(shoucang);

    }
    @RequestMapping("quxiao_shoucang.do")
    @ResponseBody
    public HashMap quxiaoShoucang(@RequestParam(value = "videoid") int videoid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        boolean shoucang = videoService.quxiaoShoucang(videoid, accountid);
        return commonResponseMethod(shoucang);

    }
    @RequestMapping("guanzhu.do")
    @ResponseBody
    public HashMap guanzhu(@RequestParam(value = "be_accountid") int be_accountid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        System.out.println(accountid);
        System.out.println(be_accountid);
        boolean guanzhu = videoService.guanzhu(accountid, be_accountid);
        return commonResponseMethod(guanzhu);

    }
    @RequestMapping("quxiao_guanzhu.do")
    @ResponseBody
    public HashMap  quxiaoGuanzhu(@RequestParam(value = "be_accountid") int be_accountid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            hashMap.put("msg", "未登录");
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        System.out.println(accountid);
        System.out.println(be_accountid);
        boolean guanzhu = videoService.quxiaoGuanzhu(accountid, be_accountid);
        return commonResponseMethod(guanzhu);

    }
    @RequestMapping("button_status.do")
    @ResponseBody
    public Map buttonStatus(@RequestParam(value = "videoid") int videoid,@RequestParam(value = "be_accountid") int be_accountid){
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("guanzhu", 0);
            hashMap.put("dianzan", 0);
            hashMap.put("shoucang", 0);
            return hashMap;
        }
        Integer accountid = account.getAccountid();
        Map map = videoService.queryButtonStatus(accountid, videoid, be_accountid);
        return map;

    }
    @RequestMapping("comment.do")
    @ResponseBody
    public Map comment(@RequestParam(value = "msg") String msg,@RequestParam(value = "videoid") Integer videoid){
        System.out.println(msg);
        Account account = (Account) ServletUtils.getSession().getAttribute("account");
        if (account == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", 2);
            return hashMap;
        }
        Comment comment = new Comment();
        comment.setCommentcontent(msg);
        comment.setCommentowner(account.getNickname());
        comment.setCommentownerid(account.getAccountid());
        comment.setCommentvideoid(videoid);
        boolean insertComment = videoService.insertComment(comment);

        return commonResponseMethod(insertComment);

    }
    @RequestMapping("query_comment.do")
    @ResponseBody
    public List queryComment(@RequestParam(value = "videoid") Integer videoid,@RequestParam("lastcommentid") Integer lastcommentid){
        List<Comment> comments = videoService.queryComment(videoid,lastcommentid);

        return comments;

    }
    private HashMap commonResponseMethod(boolean flag){
        HashMap hashMap = new HashMap();
        if (flag) {
            hashMap.put("code", 0);
        }else{
            hashMap.put("code", 1);
        }
        return hashMap;

    }
}
