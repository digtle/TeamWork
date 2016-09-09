package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.phone1000.groupproject.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class FindFragment extends Fragment {

    private View view;
    private Context mContext;

    //重写抽象工厂方法
    public static FindFragment newInstance(){
        FindFragment findFragment = new FindFragment();
        return findFragment;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:

            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_fragment, container, false);
        initView();
        return view;
    }
    //初始化控件
    private void initView() {
        SwipeRefreshLayout myRefreshListView = (SwipeRefreshLayout) view.findViewById(R.id.swap_layout);
        ListView mListView = (ListView) view.findViewById(R.id.list_view);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        //设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //加载数据
//                loadDatas();
                //刷新适配器
                mHandler.sendEmptyMessageDelayed(1,2000);
            }
        });
    }



    //ListView的适配器
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
                view = LayoutInflater.from(mContext).inflate(R.layout.find_fragment_item, parent, false);
            }
            return view;
        }
    }
}
