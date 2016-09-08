package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.MainPageAritcleInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.ui.FindBanner;
import com.phone1000.groupproject.view.IjsonView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2016/9/6.
 */
public class HomepageFragment extends Fragment implements IjsonView{
    private View view;
    private View headerView;
    private Context mContext;
    private List<MainPageAritcleInfo> articleList = new ArrayList<>();
    //重写抽象工厂方法
    private  ListAdapter adapter;
    private JsonHttpUtils jsonHttpUtils;
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
        //开启网络获取json数据
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(DigtleUrl.MAIN_PAGE_ARTICLE_URL,null,this);

    }

    private void initHeaderView() {
        //初始化头部视图
        FindBanner findbanner = new FindBanner(mContext);
        headerView = findbanner.getHeaderView(headerView);

    }

    @Override
    public void getJsonString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject returnData = jsonObject.getJSONObject("returnData");
            JSONObject  articleReslut = returnData.getJSONObject("articlelist");
            Iterator<String>   keys = articleReslut.keys();
            String key = null;
            while(keys.hasNext()){
                 key = keys.next();
                JSONObject article =  articleReslut.getJSONObject(key);
                String authorid =article.getString("authorid");
                String  title = article.getString("title");
                String  aid = article.getString("aid");
                String commentnum =article.getString("commentnum");
                long dateline = article.getLong("dateline");
                String summary =article.getString("summary");
                String recommend_add = article.getString("recommend_add");
                String pic_url = article.getString("pic_url");
                String author = article.getString("author");
                articleList.add(new MainPageAritcleInfo(recommend_add,title,aid,authorid,commentnum,dateline,summary,pic_url,author));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }


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
            View view = convertView;
            ViewHolder viewHolder = null;
            if(convertView == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.homepage_fragment_item,null,false);
                viewHolder = new ViewHolder(view);
            }
            else{
                viewHolder = (ViewHolder) view.getTag();
            }

            MainPageAritcleInfo aritcleInfo = articleList.get(position);
            viewHolder.authorNameTv.setText(aritcleInfo.getAuthor());
            viewHolder.titleTv.setText(aritcleInfo.getTitle());
            viewHolder.summaryTv.setText(aritcleInfo.getSummary());
            viewHolder.commentTv.setText(aritcleInfo.getCommentnum());
            viewHolder.likeTv.setText(aritcleInfo.getRecomandnum());
            viewHolder.commentTv.setText(aritcleInfo.getCommentnum());
            String imageUrl = aritcleInfo.getPic_url();
            Picasso.with(mContext).load(imageUrl).into(viewHolder.picImage);

            return view;
        }
        class ViewHolder{
            @BindView(R.id.avatar_image)
            CircleImageView userLogoIv;
            @BindView(R.id.user_name_tv)
            TextView authorNameTv;
            @BindView(R.id.type_name_tv)
            TextView typeTv;
            @BindView(R.id.time_data)
            TextView timeDataTv;
            @BindView(R.id.pic_image)
            ImageView picImage;
            @BindView(R.id.article_title)
            TextView titleTv;
            @BindView(R.id.article_summay)
            TextView summaryTv;
            @BindView(R.id.comment)
            TextView commentTv;
            @BindView(R.id.like)
            TextView likeTv;
            public ViewHolder(View view){
                view.setTag(this);
                ButterKnife.bind(this,view);
            }



        }
    }
}
