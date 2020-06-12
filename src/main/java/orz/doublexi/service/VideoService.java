package orz.doublexi.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.doublexi.mapper.CommonMapper;
import orz.doublexi.mapper.VideoMapper;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.Comment;
import orz.doublexi.pojo.Video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    CommonMapper commonMapper;
    public List<Video> queryVideo(int page,int num){
        List<Video> videos = videoMapper.queryVideo((page - 1) * num, num);
        return videos;
    }
    public List<Video> queryVideoByKey(int page,int num,String key){
        List<Video> videos = videoMapper.queryVideoByKey((page - 1) * num, num,key);
        return videos;
    }

    public Video queryVideoById(int videoid) {
       return videoMapper.queryVideoById(videoid);
    }
    public boolean deleteVideoById(int videoid) {
       return videoMapper.deleteVideoById(videoid)>0;
    }
    public Map queryMyUploadVideos(int accountid,int page,int limit) {
        PageHelper.startPage(page, limit);
        List<Video> videos = videoMapper.queryVideosByAccountid(accountid);
        Page p = (Page)videos;
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("data", videos);
        map.put("count", p.getTotal());
        return map;
    }
    public Map queryHisUploadVideos(int accountid,int page,int limit) {
        PageHelper.startPage(page, limit);
        List<Video> videos = videoMapper.queryVideosByAccountid(accountid);
        Page p = (Page)videos;
        System.out.println(videos.getClass());
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("data", videos);
        map.put("count", p.getTotal());
        return map;
    }
    public Map queryShoucangVideos(Integer accountid, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Video> videos = videoMapper.queryShoucangVideos(accountid);

        Page p = (Page)videos;
        System.out.println(videos.getClass());
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("data", videos);
        map.put("count", p.getTotal());
        return map;

    }
    public Map queryGuanzhuAccounts(Integer accountid, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Account> accounts = videoMapper.queryGuanzhuAccounts(accountid);
        Page p = (Page)accounts;
        System.out.println(accounts.getClass());
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("data", accounts);
        map.put("count", p.getTotal());
        return map;
    }
    public boolean dianzan(int videoid, int accountid) {
        return videoMapper.dianzan(videoid, accountid)>0;
    }
    public boolean shoucang(int videoid, int accountid) {
        return videoMapper.shoucang(videoid, accountid)>0;
    }
    public boolean guanzhu(int accountid, int be_accountid) {
        return videoMapper.guanzhu(accountid, be_accountid)>0;
    }

    public Map queryButtonStatus(Integer accountid, int videoid, Integer be_accountid) {
        Map map = new HashMap();
        map.put("dianzan", 0);
        map.put("guanzhu", 0);
        map.put("shoucang", 0);
        if (videoMapper.isdianzan(videoid, accountid)>0) {
            map.put("dianzan", 1);
        }
        if (videoMapper.isguanzhu(accountid, be_accountid)>0) {
            map.put("guanzhu", 1);
        }
        if (videoMapper.isshoucang(videoid, accountid)>0) {
            map.put("shoucang", 1);
        }
        return map;
    }
    public Map queryButtonStatus(Integer accountid,Integer be_accountid) {
        Map map = new HashMap();
        map.put("guanzhu", 0);
        if (videoMapper.isguanzhu(accountid, be_accountid)>0) {
            map.put("guanzhu", 1);
        }
        return map;
    }

    public boolean quxiaoGuanzhu(Integer accountid, int be_accountid) {
            return videoMapper.quxiaoGuanzhu(accountid, be_accountid)>0;
    }

    public boolean quxiaoShoucang(int videoid, Integer accountid) {
        return videoMapper.quxiaoShoucang(videoid, accountid)>0;
    }

    public boolean quxiaoDianzan(int videoid, Integer accountid) {
        return videoMapper.quxiaoDianzan(videoid, accountid)>0;
    }

    public boolean insertComment(Comment comment) {
       return videoMapper.insertComment(comment.getCommentcontent(), comment.getCommentowner(), comment.getCommentvideoid(), comment.getCommentownerid())>0;
    }
    public List<Comment> queryComment(Integer commentvideoid,Integer lastcommentid) {
        return videoMapper.queryComment(commentvideoid,lastcommentid);
    }


    public boolean modifyAccount(String nickname, String desc, Integer accountid) {
       int i= commonMapper.modifyAccountById(nickname, desc, accountid);
       return i>0;
    }

    public boolean modifyAccountPassword(String pwd, Integer accountid) {
        return commonMapper.modifyAccountPassword(pwd, accountid)>0;
    }
}
