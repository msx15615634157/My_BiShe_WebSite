package orz.doublexi.pojo;

public class UploadInfoBean {
    String videoname ;
    String imagename  ;
    String title  ;
    String desc  ;
    Integer owner;

    public UploadInfoBean(String videoname, String imagename, String title, String desc, Integer owner) {
        this.videoname = videoname;
        this.imagename = imagename;
        this.title = title;
        this.desc = desc;
        this.owner = owner;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "UploadInfoBean{" +
                "videoname='" + videoname + '\'' +
                ", imagename='" + imagename + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", owner=" + owner +
                '}';
    }
}
