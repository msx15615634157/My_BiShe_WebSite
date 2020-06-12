package orz.doublexi.mapper;

import org.apache.ibatis.annotations.Param;
import orz.doublexi.pojo.Account;
import orz.doublexi.pojo.Comment;
import orz.doublexi.pojo.Video;

import java.util.List;

public interface VideoMapper {
    List<Video> queryVideo(@Param("from") int from, @Param("num") int num);
    List<Video> queryVideoByKey(@Param("from") int from, @Param("num") int num,@Param("key") String key);
    Video queryVideoById(@Param("videoid") int videoid);
    Integer deleteVideoById(@Param("videoid") int videoid);
    List<Video> queryVideosByAccountid(@Param("accountid") Integer accountid);
    Integer dianzan(@Param("videoid") int videoid,@Param("accountid") int accountid );
    Integer isdianzan(@Param("videoid") int videoid,@Param("accountid") int accountid );
    Integer shoucang(@Param("videoid") int videoid,@Param("accountid") int accountid );
    Integer isshoucang(@Param("videoid") int videoid, @Param("accountid") int accountid );
    Integer guanzhu(@Param("accountid") int accountid,@Param("be_accountid") int be_accountid );
    Integer isguanzhu(@Param("accountid") int accountid,@Param("be_accountid") int be_accountid );

    Integer quxiaoGuanzhu(@Param("accountid")int accountid, @Param("be_accountid")int be_accountid);

    Integer quxiaoShoucang(@Param("videoid")int videoid, @Param("accountid")Integer accountid);

    Integer quxiaoDianzan(@Param("videoid")int videoid, @Param("accountid")Integer accountid);
    Integer insertComment(@Param("commentcontent")String commentcontent ,@Param("commentowner")String commentowner ,@Param("commentvideoid")int commentvideoid ,@Param("commentownerid")int commentownerid);

    List<Comment> queryComment(@Param("commentvideoid") Integer commentvideoid,@Param("lastcommentid") Integer lastcommentid);

    List<Video> queryShoucangVideos(@Param("accountid") Integer accountid);

    List<Account> queryGuanzhuAccounts(@Param("accountid")Integer accountid);
}
