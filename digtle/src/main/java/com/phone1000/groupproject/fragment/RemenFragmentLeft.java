package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.adapter.TeamNewListAdapter;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.presenter.impl.IsecondBannerImpl;
import com.phone1000.groupproject.view.IjsonView;
import com.phone1000.groupproject.view.IsecondBannerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 小组
 * Created by Administrator on 2016/9/7.
 */
public class RemenFragmentLeft extends Fragment{

    private Context mContext;

    //重写抽象工厂方法
    public static RemenFragmentLeft newInstance(){
        RemenFragmentLeft remenFragmentLeft = new RemenFragmentLeft();
        return remenFragmentLeft;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remen_ftagment_left, container, false);
        ListView mListView = (ListView) view.findViewById(R.id.list_view);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        return view;
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
                view = LayoutInflater.from(mContext).inflate(R.layout.remen_fragment_left_item,parent,false);
            }
            return view;
        }
    }
}
