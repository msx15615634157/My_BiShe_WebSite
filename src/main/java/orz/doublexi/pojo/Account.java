package orz.doublexi.pojo;

public class Account {
    private Integer accountid;

    private String accountpwd;

    private String nickname;

    private Integer exp;

    private Boolean isadmin;

    private String accountimg;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccountpwd() {
        return accountpwd;
    }

    public void setAccountpwd(String accountpwd) {
        this.accountpwd = accountpwd == null ? null : accountpwd.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", accountpwd='" + accountpwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", exp=" + exp +
                ", isadmin=" + isadmin +
                ", accountimg='" + accountimg + '\'' +
                '}';
    }

    public String getAccountimg() {
        return accountimg;
    }

    public void setAccountimg(String accountimg) {

        this.accountimg = accountimg == null ? null : accountimg.trim();
    }

}