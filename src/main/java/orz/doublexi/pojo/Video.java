package orz.doublexi.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Video {
    private Integer videoid;

    private String videotitle;

    private String videopicture;

    private String videolocation;

    private Integer videoowner;
    private String nickname;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date videodate;
    private String videodesc;

    public String getVideodesc() {
        return videodesc;
    }

    public void setVideodesc(String videodesc) {
        this.videodesc = videodesc;
    }

    public Date getVideodate() {
        return videodate;
    }

    public void setVideodate(Date videodate) {
        this.videodate = videodate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getVideotitle() {
        return videotitle;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle == null ? null : videotitle.trim();
    }

    public String getVideopicture() {
        return videopicture;
    }

    public void setVideopicture(String videopicture) {
        this.videopicture = videopicture == null ? null : videopicture.trim();
    }

    public String getVideolocation() {
        return videolocation;
    }

    public void setVideolocation(String videolocation) {
        this.videolocation = videolocation == null ? null : videolocation.trim();
    }

    public Integer getVideoowner() {
        return videoowner;
    }

    public void setVideoowner(Integer videoowner) {

        this.videoowner = videoowner;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoid=" + videoid +
                ", videotitle='" + videotitle + '\'' +
                ", videopicture='" + videopicture + '\'' +
                ", videolocation='" + videolocation + '\'' +
                ", videoowner=" + videoowner +
                ", nickname='" + nickname + '\'' +
                ", videodate=" + videodate +
                ", videodesc='" + videodesc + '\'' +
                '}';
    }
}