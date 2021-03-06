package com.phone1000.groupproject.bean;

import java.util.List;

/**
 * Created by ${USER_NAME} on 2016/9/6.
 */
public class MostnewListInfo {
 private int attachcount;
    private List<String> attachList;
    private String author;
    private String authorid;
    private String dateline;
    private List<LikeListBean> listBeanList;

    public List<LikeListBean> getListBeanList() {
        return listBeanList;
    }

    public void setListBeanList(List<LikeListBean> listBeanList) {
        this.listBeanList = listBeanList;
    }

    public String getTid() {

        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public MostnewListInfo(String tid) {
        this.tid = tid;
    }

    private String tid;
  public  MostnewListInfo(){};
    public MostnewListInfo(int attachcount, List<String> attachList, String author, String authorid, String dateline, String forum_icon, String recommend_add, String forum_name, String postcommentcount, String replies, String subject, String summary,String tid) {
        this.attachcount = attachcount;
        this.attachList = attachList;
        this.author = author;
        this.authorid = authorid;
        this.dateline = dateline;
        this.forum_icon = forum_icon;
        this.recommend_add = recommend_add;
        this.forum_name = forum_name;
        this.postcommentcount = postcommentcount;
        this.replies = replies;
        this.subject = subject;
        this.summary = summary;
        this.tid = tid;
    }

    private String forum_icon;

    public int getAttachcount() {
        return attachcount;
    }

    public void setAttachcount(int attachcount) {
        this.attachcount = attachcount;
    }

    public List<String> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<String> attachList) {
        this.attachList = attachList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getForum_icon() {
        return forum_icon;
    }

    public void setForum_icon(String forum_icon) {
        this.forum_icon = forum_icon;
    }

    public String getForum_name() {
        return forum_name;
    }

    public void setForum_name(String forum_name) {
        this.forum_name = forum_name;
    }

    public String getRecommend_add() {
        return recommend_add;
    }

    public void setRecommend_add(String recommend_add) {
        this.recommend_add = recommend_add;
    }

    public String getPostcommentcount() {
        return postcommentcount;
    }

    public void setPostcommentcount(String postcommentcount) {
        this.postcommentcount = postcommentcount;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String forum_name;
    private String recommend_add;
    private String postcommentcount;
    private String replies;
    private String subject;
    private String summary;
class LikeListBean{
    private String avatar;
    private String dateline;
    private String uid;
    private String username;

    public LikeListBean(String avatar, String dateline, String uid, String username) {
        this.avatar = avatar;
        this.dateline = dateline;
        this.uid = uid;
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
}
