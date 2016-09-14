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
public class TeamFragmentLeft extends Fragment implements IsecondBannerView,IjsonView{
 @BindView(R.id.left_prlv)
 ListView newsList;
    @BindView(R.id.team_left_swap)
    SwipeRefreshLayout swapLayout;
    private Context mContext;
private View headerView;
    private ViewPager leftFragmentBanner;
    private TextView titleTv;
    private Button joinGroupBtn;
    private LinearLayout linearLayout;
    private SecondBannerAdapter bannerAdapter;
    private int cutrentPage;
    private List<LeftBannerInfo.BannerlistBean> bannerList = new ArrayList<>();
    private IsecondBannerImpl mIsecondBannerImpl;
    private int page = 1 ;
    private int perPage = 20;
    private JsonHttpUtils jsonHttpUtils;
    private TeamNewListAdapter listAdapter;
    private List<MostnewListInfo>  newlistBeenList = new ArrayList<>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cutrentPage++;
            leftFragmentBanner.setCurrentItem(cutrentPage%4);
            sendEmptyMessageDelayed(0,3000);
        }
    };
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
        ButterKnife.bind(this,view);
        initHeaderView();
        initView();
        loadDatas();
        return view;
    }

    private void loadDatas() {
        //开启网络下载获取数据
        jsonHttpUtils = JsonHttpUtils.newInstance();

        jsonHttpUtils.load(DigtleUrl.GROUP_MOST_NEW_URL,null,this,JsonHttpUtils.REQUEST_METHOD_GET);

    }

    private void initHeaderView() {
        //初始化listview的头部视图
        headerView = LayoutInflater.from(mContext).inflate(R.layout.team_fragmet_headview,null,false);
        leftFragmentBanner = (ViewPager) headerView.findViewById(R.id.team_new_banner);
        titleTv = (TextView) headerView.findViewById(R.id.team_new_title);
        joinGroupBtn = (Button) headerView.findViewById(R.id.team_start_gruop);
        linearLayout  = (LinearLayout) headerView.findViewById(R.id.team_new_selector_ll);
        //viewpager绑定适配器
        bannerAdapter = new SecondBannerAdapter();
        leftFragmentBanner.setAdapter(bannerAdapter);
        //请求数据
        mIsecondBannerImpl = new IsecondBannerImpl(this);
        mIsecondBannerImpl.querySecondBanner();
        //设置viewpager与底部的图标的联合滚动的视图
        leftFragmentBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                        cutrentPage =position;
                for(int i = 0; i< linearLayout.getChildCount();i++){
                    ImageView imageView = (ImageView)  linearLayout.getChildAt(i);
                    imageView.setEnabled(false);
                }
                ImageView imageView1 = (ImageView)  linearLayout.getChildAt(position);
                imageView1.setEnabled(true);
                String title = bannerList.get(position).getTitle();
                titleTv.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {
        //添加头部视图
         newsList.addHeaderView(headerView);
        //创建适配器
     listAdapter = new TeamNewListAdapter(mContext,newlistBeenList);
        //绑定适配器
        newsList.setAdapter(listAdapter);
        //设置下拉刷新监听器
        swapLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swapLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        newlistBeenList.clear();
                        jsonHttpUtils.load(DigtleUrl.GROUP_MOST_NEW_URL,null,TeamFragmentLeft.this,JsonHttpUtils.REQUEST_METHOD_GET);
                        //更新完后调用该方法结束刷新

                    }
                },2000);
            }
        });
    }


    @Override
    public void getSecondBanner(LeftBannerInfo leftBannerInfo) {
        //获取头部视图的数据更新
             bannerList.addAll(leftBannerInfo.getBannerlist());
        //刷新适配器
             bannerAdapter.notifyDataSetChanged();
             mHandler.sendEmptyMessage(0);
    }

    @Override
    public void getJsonString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("newlist");
            for(int i = 0; i < jsonArray.length();i++){
                MostnewListInfo mostnewListInfo = new MostnewListInfo();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int attchcount = jsonObject1.getInt("attachcount");
                List<String>  attachList = new ArrayList<>();
                if(attchcount != 0){
                    JSONObject imageList = jsonObject1.getJSONObject("attachlist");
                    Iterator<String> keys = imageList.keys();
                    while (keys.hasNext()){
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
                newlistBeenList.add(mostnewListInfo);
            }
            listAdapter.notifyDataSetChanged();
            swapLayout.setRefreshing(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class SecondBannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return bannerList == null ? 0:bannerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            //获取viewpaer视图
            String imageUrl = bannerList.get(position).getPic();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片
            Picasso.with(mContext).load(imageUrl).tag(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
