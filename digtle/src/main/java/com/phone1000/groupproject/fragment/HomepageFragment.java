package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.MainPageAritcleInfo;
import com.phone1000.groupproject.ui.FindBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/9/6.
 */
public class HomepageFragment extends Fragment {
    private View view;
    private View headerView;
    private Context mContext;
    private List<MainPageAritcleInfo> articleList = new ArrayList<>();
    //重写抽象工厂方法
    private  ListAdapter adapter;
    @BindView(R.id.homepage_list_view)
    PullToRefreshListView pulllistView;
    public static HomepageFragment newInstance(){
        HomepageFragment homepageFragment = new HomepageFragment();
        return homepageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment, container, false);
        ButterKnife.bind(this,view);
        initHeaderView();
        initView();
        return view;
    }

    private void initView() {
        ListView listview = pulllistView.getRefreshableView();
        listview.addHeaderView(headerView);
        adapter = new ListAdapter();
        pulllistView.setAdapter(adapter);

    }

    private void initHeaderView() {
        //初始化头部视图
        FindBanner findbanner = new FindBanner(mContext);
        headerView = findbanner.getHeaderView(headerView);

    }
    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return articleList == null? 0:articleList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
