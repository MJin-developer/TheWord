package kor.co.mu.jin.theword;

public class subwordList {

    String imgurl;
    String name;
    String content;

    public subwordList() {
    }

    public subwordList(String imgurl, String name, String content) {
        this.imgurl = imgurl;
        this.name = name;
        this.content = content;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
