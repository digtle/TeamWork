package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.groupproject.R;


/**
 * 小组
 * Created by Administrator on 2016/9/7.
 */
public class TeamFragmentLeft extends Fragment {
    private Context mContext;
    private PullToRefreshListView mPullToRefreshListView;

    //重写抽象工厂方法
    public static TeamFragmentLeft newInstance(){
        TeamFragmentLeft teamFragmentLeft = new TeamFragmentLeft();
        return teamFragmentLeft;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_ftagment_left, container, false);
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.left_prlv);
        initView();
        return view;
    }

    private void initView() {
        //设置加载更多功能
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//同时支持刷新和加载更多功能

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
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
            return null;
        }
    }
}
