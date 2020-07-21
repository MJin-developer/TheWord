package kor.co.mu.jin.theword;

public class MYStoryItemList {

    String title;
    String profileImg;
    String name;
    String content;
    int showNum;
    int favoriteNumber;
    int subwordNumber;

    public MYStoryItemList(String title, String profileImg, String name, String content, int showNum, int favoriteNumber, int subwordNumber) {
        this.title = title;
        this.profileImg = profileImg;
        this.name = name;
        this.content = content;
        this.showNum = showNum;
        this.favoriteNumber = favoriteNumber;
        this.subwordNumber = subwordNumber;
    }

    public MYStoryItemList() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
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

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
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
}
