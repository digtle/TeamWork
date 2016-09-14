package com.phone1000.groupproject.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${USER_NAME} on 2016/9/13.
 */
public class CommentListBean {
    private String author;
    private String authorid;
    private String dateLine;
    private String message;
public CommentListBean(){
    postCommentList = new ArrayList<>();
}
    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateLine() {
        return dateLine;
    }

    public void setDateLine(String dateLine) {
        this.dateLine = dateLine;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<PostComment> getPostCommentList() {
        return postCommentList;
    }

    public void setPostCommentList(List<PostComment> postCommentList) {
        this.postCommentList = postCommentList;
    }

   public List<PostComment> postCommentList;
  public static class PostComment{
        private String author;
        private String authorid;
        private String dateLine;
        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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

        public String getDateLine() {
            return dateLine;
        }

        public void setDateLine(String dateLine) {
            this.dateLine = dateLine;
        }


    }
}
