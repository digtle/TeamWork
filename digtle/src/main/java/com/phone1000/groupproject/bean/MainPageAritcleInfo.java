package com.phone1000.groupproject.bean;

/**
 * Created by ${USER_NAME} on 2016/9/7.
 */
public class MainPageAritcleInfo {
    private String recomandnum;
    private String title;
    private String authorid;
    private String aid;

    public MainPageAritcleInfo(String recomandnum, String title, String aid, String authorid, String commentnum, Long dataLine, String summary, String pic_url) {
        this.recomandnum = recomandnum;
        this.title = title;
        this.aid = aid;
        this.authorid = authorid;
        this.commentnum = commentnum;
        this.dataLine = dataLine;
        this.summary = summary;
        this.pic_url = pic_url;
    }

    public String getRecomandnum() {
        return recomandnum;
    }

    public void setRecomandnum(String recomandnum) {
        this.recomandnum = recomandnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    public Long getDataLine() {
        return dataLine;
    }

    public void setDataLine(Long dataLine) {
        this.dataLine = dataLine;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    private String commentnum;
    private Long dataLine;
    private String summary;
    private String pic_url;

}
