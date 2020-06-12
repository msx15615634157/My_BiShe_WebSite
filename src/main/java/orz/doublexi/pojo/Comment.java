package orz.doublexi.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment {
    private Integer commentid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date commentdate;

    private String commentcontent;

    private String commentowner;

    private Integer commentvideoid;
    private Integer commentownerid;

    public Integer getCommentownerid() {
        return commentownerid;
    }

    public void setCommentownerid(Integer commentownerid) {
        this.commentownerid = commentownerid;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent == null ? null : commentcontent.trim();
    }

    public String getCommentowner() {
        return commentowner;
    }

    public void setCommentowner(String commentowner) {
        this.commentowner = commentowner;
    }

    public Integer getCommentvideoid() {
        return commentvideoid;
    }

    public void setCommentvideoid(Integer commentvideoid) {
        this.commentvideoid = commentvideoid;
    }
}