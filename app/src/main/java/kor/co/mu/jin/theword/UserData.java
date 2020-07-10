package kor.co.mu.jin.theword;

public class UserData {

    String ImgUrl;
    String Nickname;

    public UserData(String imgUrl, String nickname) {
        this.ImgUrl = imgUrl;
        this.Nickname = nickname;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }
}
