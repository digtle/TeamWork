package com.phone1000.groupproject.bean;

import java.util.List;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class LeftBannerInfo {

    /**
     * perpage : 30
     * register : 1
     * imagesize : all
     * auth_outdated : 1
     * cdn_img_ext : szhdl=imageview/2/w/[xxx]
     * bannerlist : [{"itemid":"918800","bid":"431","id":"5424","idtype":"rand","itemtype":"1","title":"『尾巴小组』活跃组员就是我！ 第六期","url":"http://www.dgtle.com/article-15735-1.html","pic":"http://s.dgtle.com/portal/201609/08/133634x3jvvsgncjmwggej.png","picflag":"1","makethumb":"0","summary":"","showstyle":"","related":"","fields":{"id":0,"author":"","authorid":0,"avatar":"","avatar_middle":"","avatar_big":"","posts":0,"todayposts":0,"lastpost":false,"dateline":false,"replies":0,"views":0,"heats":0,"recommends":0,"groupname":"","groupurl":""},"displayorder":"4","startdate":"1473312900","enddate":"0","thumbpath":"","from_id":false},{"itemid":"918015","bid":"431","id":"6546","idtype":"rand","itemtype":"1","title":"『尾巴小组』开学季，笔电挑选不用方","url":"http://www.dgtle.com/zt-group-topic-89-1.html","pic":"http://s.dgtle.com/portal/201608/31/202915g320992m0hh939h0.png","picflag":"1","makethumb":"0","summary":"","showstyle":"","related":"","fields":{"id":0,"author":"","authorid":0,"avatar":"","avatar_middle":"","avatar_big":"","posts":0,"todayposts":0,"lastpost":0,"dateline":0,"replies":0,"views":0,"heats":0,"recommends":0,"groupname":"","groupurl":""},"displayorder":"3","startdate":"1472646480","enddate":"0","thumbpath":"block/31/31818dc8ecaf70405585c9523def3495.jpg","from_id":false},{"itemid":"918799","bid":"431","id":"6238","idtype":"rand","itemtype":"1","title":"『尾巴小组』每日精选：不要宅，出克浪","url":"http://www.dgtle.com/zt-group-topic-107-1.html","pic":"http://s.dgtle.com/portal/201609/08/133259cr88kgioihkgrc2i.jpg","picflag":"1","makethumb":"0","summary":"","showstyle":"","related":"","fields":{"id":0,"author":"","authorid":0,"avatar":"","avatar_middle":"","avatar_big":"","posts":0,"todayposts":0,"lastpost":false,"dateline":false,"replies":0,"views":0,"heats":0,"recommends":0,"groupname":"","groupurl":""},"displayorder":"1","startdate":"1473312600","enddate":"0","thumbpath":"","from_id":false},{"itemid":"918201","bid":"431","id":"5919","idtype":"rand","itemtype":"1","title":"『尾巴小组』来自桌面的千言万语","url":"http://www.dgtle.com/zt-group-topic-94-1.html","pic":"http://s.dgtle.com/portal/201609/02/160151o7li2b3e24cj01bd.png","picflag":"1","makethumb":"0","summary":"","showstyle":"","related":"","fields":{"id":0,"author":"","authorid":0,"avatar":"","avatar_middle":"","avatar_big":"","posts":0,"todayposts":0,"lastpost":0,"dateline":0,"replies":0,"views":0,"heats":0,"recommends":0,"groupname":"","groupurl":""},"displayorder":"2","startdate":"1472803260","enddate":"0","thumbpath":"block/ab/ab5134152beae958d358b10f7d14181a.jpg","from_id":false}]
     */

    private int perpage;
    private int register;
    private String imagesize;
    private String auth_outdated;
    private String cdn_img_ext;
    /**
     * itemid : 918800
     * bid : 431
     * id : 5424
     * idtype : rand
     * itemtype : 1
     * title : 『尾巴小组』活跃组员就是我！ 第六期
     * url : http://www.dgtle.com/article-15735-1.html
     * pic : http://s.dgtle.com/portal/201609/08/133634x3jvvsgncjmwggej.png
     * picflag : 1
     * makethumb : 0
     * summary :
     * showstyle :
     * related :
     * fields : {"id":0,"author":"","authorid":0,"avatar":"","avatar_middle":"","avatar_big":"","posts":0,"todayposts":0,"lastpost":false,"dateline":false,"replies":0,"views":0,"heats":0,"recommends":0,"groupname":"","groupurl":""}
     * displayorder : 4
     * startdate : 1473312900
     * enddate : 0
     * thumbpath :
     * from_id : false
     */

    private List<BannerlistBean> bannerlist;

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getImagesize() {
        return imagesize;
    }

    public void setImagesize(String imagesize) {
        this.imagesize = imagesize;
    }

    public String getAuth_outdated() {
        return auth_outdated;
    }

    public void setAuth_outdated(String auth_outdated) {
        this.auth_outdated = auth_outdated;
    }

    public String getCdn_img_ext() {
        return cdn_img_ext;
    }

    public void setCdn_img_ext(String cdn_img_ext) {
        this.cdn_img_ext = cdn_img_ext;
    }

    public List<BannerlistBean> getBannerlist() {
        return bannerlist;
    }

    public void setBannerlist(List<BannerlistBean> bannerlist) {
        this.bannerlist = bannerlist;
    }

    public static class BannerlistBean {
        private String itemid;
        private String bid;
        private String id;
        private String idtype;
        private String itemtype;
        private String title;
        private String url;
        private String pic;
        private String picflag;
        private String makethumb;
        private String summary;
        private String showstyle;
        private String related;
        /**
         * id : 0
         * author :
         * authorid : 0
         * avatar :
         * avatar_middle :
         * avatar_big :
         * posts : 0
         * todayposts : 0
         * lastpost : false
         * dateline : false
         * replies : 0
         * views : 0
         * heats : 0
         * recommends : 0
         * groupname :
         * groupurl :
         */

        private FieldsBean fields;
        private String displayorder;
        private String startdate;
        private String enddate;
        private String thumbpath;
        private boolean from_id;

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdtype() {
            return idtype;
        }

        public void setIdtype(String idtype) {
            this.idtype = idtype;
        }

        public String getItemtype() {
            return itemtype;
        }

        public void setItemtype(String itemtype) {
            this.itemtype = itemtype;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPicflag() {
            return picflag;
        }

        public void setPicflag(String picflag) {
            this.picflag = picflag;
        }

        public String getMakethumb() {
            return makethumb;
        }

        public void setMakethumb(String makethumb) {
            this.makethumb = makethumb;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getShowstyle() {
            return showstyle;
        }

        public void setShowstyle(String showstyle) {
            this.showstyle = showstyle;
        }

        public String getRelated() {
            return related;
        }

        public void setRelated(String related) {
            this.related = related;
        }

        public FieldsBean getFields() {
            return fields;
        }

        public void setFields(FieldsBean fields) {
            this.fields = fields;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(String displayorder) {
            this.displayorder = displayorder;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getThumbpath() {
            return thumbpath;
        }

        public void setThumbpath(String thumbpath) {
            this.thumbpath = thumbpath;
        }

        public boolean isFrom_id() {
            return from_id;
        }

        public void setFrom_id(boolean from_id) {
            this.from_id = from_id;
        }

        public static class FieldsBean {
            private int id;
            private String author;
            private int authorid;
            private String avatar;
            private String avatar_middle;
            private String avatar_big;
            private int posts;
            private int todayposts;
            private boolean lastpost;
            private boolean dateline;
            private int replies;
            private int views;
            private int heats;
            private int recommends;
            private String groupname;
            private String groupurl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getAuthorid() {
                return authorid;
            }

            public void setAuthorid(int authorid) {
                this.authorid = authorid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAvatar_middle() {
                return avatar_middle;
            }

            public void setAvatar_middle(String avatar_middle) {
                this.avatar_middle = avatar_middle;
            }

            public String getAvatar_big() {
                return avatar_big;
            }

            public void setAvatar_big(String avatar_big) {
                this.avatar_big = avatar_big;
            }

            public int getPosts() {
                return posts;
            }

            public void setPosts(int posts) {
                this.posts = posts;
            }

            public int getTodayposts() {
                return todayposts;
            }

            public void setTodayposts(int todayposts) {
                this.todayposts = todayposts;
            }

            public boolean isLastpost() {
                return lastpost;
            }

            public void setLastpost(boolean lastpost) {
                this.lastpost = lastpost;
            }

            public boolean isDateline() {
                return dateline;
            }

            public void setDateline(boolean dateline) {
                this.dateline = dateline;
            }

            public int getReplies() {
                return replies;
            }

            public void setReplies(int replies) {
                this.replies = replies;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public int getHeats() {
                return heats;
            }

            public void setHeats(int heats) {
                this.heats = heats;
            }

            public int getRecommends() {
                return recommends;
            }

            public void setRecommends(int recommends) {
                this.recommends = recommends;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public String getGroupurl() {
                return groupurl;
            }

            public void setGroupurl(String groupurl) {
                this.groupurl = groupurl;
            }
        }
    }
}
