package com.phone1000.groupproject;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class TeamHeadDetailsActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_head_details);
        initView();
    }

    private void initView() {
        mSwipeRefreshView = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mListView = (ListView) findViewById(R.id.details_prlv);

        //设置适配器
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);

        //添加 顶/底 部视图
        View mHeadView = LayoutInflater.from(this).inflate(R.layout.activity_team_head_details_headview, null);
        View mFooterView = LayoutInflater.from(this).inflate(R.layout.activity_team_head_details_belowview, null);
        mListView.addHeaderView(mHeadView);
        mListView.addFooterView(mFooterView);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 8;
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
                view = LayoutInflater.from(TeamHeadDetailsActivity.this).inflate(R.layout.team_fragment_item, parent, false);
            }
            return view;
        }
    }
}
