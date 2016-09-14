package com.phone1000.groupproject.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.adapter.ExpandListAdapter;
import com.phone1000.groupproject.bean.CommentListBean;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.view.IjsonView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamNewAritcleActivity extends AppCompatActivity implements IjsonView {
    @BindView(R.id.aritcle_detail_exlist)
ExpandableListView expandableListView;
    @BindView(R.id.team_ariticle_back)
    ImageButton backImage;
    @BindView(R.id.team_ariticle_commend)
    ImageButton commendBtn;
    @BindView(R.id.share_ib)
    ImageButton shareBtn;
   private Context mContex;
    private  String tid;
    private View headerView;
    private ExpandListAdapter expandListAdapter;
    private JsonHttpUtils mJsonHttpUtils;
    private GetDetailHeadView getDetailHeadView;
    private List<CommentListBean> commentListBeanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_new_aritcle);
        mContex = this;
        ButterKnife.bind(this);
        initTid();
        initView();
        loadDatas();

    }

    private void loadDatas() {
        mJsonHttpUtils = JsonHttpUtils.newInstance();
        String commentUrl = DigtleUrl.getDetailUrl(DigtleUrl.FIND_ARITICLE_COMMENTLIST_URL,tid,DigtleUrl.FIND_ARRICLE_COMMENTLIST_FOOTURL);
        mJsonHttpUtils.load(commentUrl,null,this,JsonHttpUtils.REQUEST_METHOD_GET);
    }


    private void initView() {
        getDetailHeadView = new GetDetailHeadView(tid,mContex);
        headerView =  getDetailHeadView.initHeaderView(headerView);
        expandableListView.addHeaderView(headerView);
        expandListAdapter = new ExpandListAdapter(commentListBeanList,mContex);
        expandableListView.setAdapter(expandListAdapter);
        //设置父view不现实箭头
        expandableListView.setGroupIndicator(null);
        //设置点击之后无法收缩
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

    }

    private void initTid() {
        Intent intent =getIntent();
        tid =intent.getStringExtra("tid");
    }

    @Override
    public void getJsonString(String jsonString) {

        //解析数据
        try {
            JSONObject jsobject = new JSONObject(jsonString);
            JSONObject returnData = jsobject.getJSONObject("returnData");
            JSONObject postlist = returnData.getJSONObject("postlist");
            Iterator<String> postKeys = postlist.keys();
          while (postKeys.hasNext()){
               String  postkey = postKeys.next();
                JSONObject comment = postlist.getJSONObject(postkey);
                String author = comment.getString("author");
                String authorid =comment.getString("authorid");
                String dateline =comment.getString("dateline");
                String message =comment.getString("message");
                CommentListBean commentListBean = new CommentListBean();
                commentListBean.setAuthor(author);
                commentListBean.setAuthorid(authorid);
                commentListBean.setDateLine(dateline);
                commentListBean.setMessage(message);
              List<CommentListBean.PostComment> postCommentList = new ArrayList<>();
               if(comment.has("postcomment")) {
                   JSONArray postComment = comment.getJSONArray("postcomment");
                      for (int i = 0; i <postComment.length();i++) {
                           CommentListBean.PostComment postcommentbean = new CommentListBean.PostComment();
                           JSONObject jsonObject2 = postComment.getJSONObject(i);
                           String postauthor = jsonObject2.getString("author");
                           String postauthorId = jsonObject2.getString("authorid");
                           String postCommnet = jsonObject2.getString("comment");
                           String postdateline = jsonObject2.getString("dateline");
                           postcommentbean.setDateLine(postdateline);
                           postcommentbean.setAuthor(postauthor);
                           postcommentbean.setAuthorid(postauthorId);
                           postcommentbean.setComment(postCommnet);
                           postCommentList.add(postcommentbean);
                       }
                   }
              commentListBean.postCommentList.addAll(postCommentList);
               commentListBeanList.add(commentListBean);
            }
            expandListAdapter.notifyDataSetChanged();
            for(int i1 = 0; i1<expandableListView.getCount();i1++ ){
                expandableListView.expandGroup(i1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
