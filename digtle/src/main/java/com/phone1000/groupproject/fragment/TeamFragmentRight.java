package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.phone1000.groupproject.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TeamFragmentRight extends Fragment {
    private Context mContext;
    private View view;
    private List<String> datas = new ArrayList<>();

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
//        loadDatas();
        initView();
        return view;
    }

    private void loadDatas() {
        // 模拟一些数据
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }
    }

    private void initView() {
        final SwipeRefreshLayout myRefreshListView = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        ListView mListView = (ListView) view.findViewById(R.id.list_view);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        //设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
//                        datas.add(new Date().toGMTString());
                        //刷新适配器

                        //更新完后调用该方法结束刷新
                        myRefreshListView.setRefreshing(false);
                    }
                },1500);
            }
        });
        //设置加载监听器
//        myRefreshListView.
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null){
                view =LayoutInflater.from(mContext).inflate(R.layout.team_fragment_item,parent,false);
            }
            return view;
        }
    }
}
