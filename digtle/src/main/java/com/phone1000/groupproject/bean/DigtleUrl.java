package com.phone1000.groupproject.bean;

/**
 * Created by ${USER_NAME} on 2016/9/7.
 */
public class DigtleUrl {
    public static final String MAIN_PAGE_BANNER_URL = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=portal&actions=diydata&bid=274";
     public  static final  String MAIN_PAGE_ARTICLE_URL ="http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=portal&actions=article&limit=0_20&order=dateline_desc";
    public static  final String GROUP_MOST_NEW_URL="http://www.dgtle.com/api/dgtle_api/v1/api.php?REQUESTCODE=46&apikeys=DGTLECOM_APITEST1&page=1&perpage=30";
    public  static  final  String GROUP_WELL_CHOSEN= "http://www.dgtle.com/api/dgtle_api/v1/api.php?REQUESTCODE=46&apikeys=DGTLECOM_APITEST1&digest=1&page=1&perpage=20";
    public  static  final  String USER_LOGO_URL ="http://www.dgtle.com/uc_server/data/avatar/000/";
    public static final  String FIND_ARTITLE_INGO_URL ="http://www.dgtle.com/api/dgtle_api/v1/api.php?REQUESTCODE=62&apikeys=DGTLECOM_APITEST1&page=1&perpage=20";
    public static String  getUserLogoUrl(String  aid){
      String  str = "";
      StringBuffer  parms = new StringBuffer();
        if(aid.length() < 5){
            return "434745" ;
        }
      else if(aid.length() == 5){
         str = "0"+aid;
      }
        else {
          str = aid;
      }

      String value1= str.substring(0,2);
      String value2 = str.substring(2,4);
      String value3 = str.substring(4);
      parms.append(USER_LOGO_URL).append(value1).append("/").append(value2).append("/").append(value3).append("_avatar_middle.jpg");
        return  parms.toString();
  }

}
