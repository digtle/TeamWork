package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.adapter.TeamNewListAdapter;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.view.IjsonView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TeamFragmentRight extends Fragment implements IjsonView {
    private Context mContext;
    private View view;
    private List<String> datas = new ArrayList<>();
    private JsonHttpUtils jsonHttpUtils;
    private TeamNewListAdapter adapter;
    private List<MostnewListInfo>  beanList = new ArrayList<>();
    //重写抽象工厂方法
    public static TeamFragmentRight newInstance(){
        TeamFragmentRight teamFragmentRight = new TeamFragmentRight();
        return teamFragmentRight;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.team_ftagment_right, container, false);
        loadDatas();
        initView();
        return view;
    }

    private void loadDatas() {
      jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(DigtleUrl.FIND_WELL_CHOSEN,null,this,JsonHttpUtils.REQUEST_METHOD_GET);
    }

    private void initView() {
        final SwipeRefreshLayout myRefreshListView = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        ListView mListView = (ListView) view.findViewById(R.id.list_view);
           adapter = new TeamNewListAdapter(mContext,beanList);
        mListView.setAdapter(adapter);
        //设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        beanList.clear();
                        jsonHttpUtils.load(DigtleUrl.FIND_WELL_CHOSEN,null,TeamFragmentRight.this,JsonHttpUtils.REQUEST_METHOD_GET);
                        //刷新适配器

                        //更新完后调用该方法结束刷新
                        myRefreshListView.setRefreshing(false);
                    }
                },2000);
            }
        });
        //设置加载监听器
//        myRefreshListView.
    }

    @Override
    public void getJsonString(String jsonString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("newlist");
            for (int i = 0; i < jsonArray.length(); i++) {
                MostnewListInfo mostnewListInfo = new MostnewListInfo();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int attchcount = jsonObject1.getInt("attachcount");
                List<String> attachList = new ArrayList<>();
                if (attchcount != 0) {
                    JSONObject imageList = jsonObject1.getJSONObject("attachlist");
                    Iterator<String> keys = imageList.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        String imageUrl = imageList.getString(key);
                        attachList.add(imageUrl);
                    }
                }

                mostnewListInfo.setAttachList(attachList);
                mostnewListInfo.setAttachcount(attchcount);
                mostnewListInfo.setAuthor(jsonObject1.getString("author"));
                mostnewListInfo.setAuthorid(jsonObject1.getString("authorid"));
                mostnewListInfo.setDateline(jsonObject1.getString("dateline"));
                mostnewListInfo.setForum_icon(jsonObject1.getString("forum_icon"));
                mostnewListInfo.setPostcommentcount(jsonObject1.getString("postcommentcount"));
                mostnewListInfo.setForum_name(jsonObject1.getString("forum_name"));
                mostnewListInfo.setReplies(jsonObject1.getString("replies"));
                mostnewListInfo.setSubject(jsonObject1.getString("subject"));
                mostnewListInfo.setSummary(jsonObject1.getString("summary"));
                mostnewListInfo.setRecommend_add(jsonObject1.getString("recommend_add"));
                beanList.add(mostnewListInfo);
            }

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
