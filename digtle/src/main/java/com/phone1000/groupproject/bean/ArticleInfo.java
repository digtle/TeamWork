package com.phone1000.groupproject.bean;

/**
 * Created by ${USER_NAME} on 2016/9/6.
 */
public class ArticleInfo {
//    private String logoExample ="http://www.dgtle.com/uc_server/data/avatar/000/41/19/13_avatar_middle.jpg";
    private String logo;
    private int aid;
    private String author;
    private String summary;
    private String title;
    private String recommend_add;
    private String pic_url;
    private int commentnum;
    private  String catid;
    private String authorid;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommend_add() {
        return recommend_add;
    }

    public void setRecommend_add(String recommend_add) {
        this.recommend_add = recommend_add;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }
}

