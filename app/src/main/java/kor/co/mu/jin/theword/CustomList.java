package kor.co.mu.jin.theword;

public class CustomList {

    String imgUrl;
    String title;

    public CustomList(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public CustomList() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
