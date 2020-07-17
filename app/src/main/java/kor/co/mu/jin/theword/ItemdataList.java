package kor.co.mu.jin.theword;

public class ItemdataList {

    String imgUrl;
    String title;

    int favoriteNumber;
    int subwordNumber;
    String youtubeID;
    String content;

    public ItemdataList() {
    }

    public ItemdataList(String imgUrl, String title, int favoriteNumber, int subwordNumber, String youtubeID, String content) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.favoriteNumber = favoriteNumber;
        this.subwordNumber = subwordNumber;
        this.youtubeID = youtubeID;
        this.content = content;
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

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public int getSubwordNumber() {
        return subwordNumber;
    }

    public void setSubwordNumber(int subwordNumber) {
        this.subwordNumber = subwordNumber;
    }

    public String getYoutubeID() {
        return youtubeID;
    }

    public void setYoutubeID(String youtubeID) {
        this.youtubeID = youtubeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
