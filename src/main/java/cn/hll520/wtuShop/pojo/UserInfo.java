package cn.hll520.wtuShop.pojo;


/**
 * @author lpc
 * @create 2020-07-20-11:02
 */
public class UserInfo {
    private Integer userid;
    private String username;
    private String password;
    private String realname;

    public UserInfo(){}

    public UserInfo(Integer userid, String username, String password, String realname) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.realname = realname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public static UserInfo getInfo(UserInfo user){
        if(user==null)
            return null;
        UserInfo info=new UserInfo();
        info.setUserid(user.getUserid());
        info.setUsername(user.getUsername());
        info.setRealname(user.getRealname());
        info.setPassword(null);
        return info;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
