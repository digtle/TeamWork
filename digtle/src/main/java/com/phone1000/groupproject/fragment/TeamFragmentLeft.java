package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.presenter.impl.ImostnewImpl;
import com.phone1000.groupproject.presenter.impl.IsecondBannerImpl;
import com.phone1000.groupproject.view.ImostnewlistView;
import com.phone1000.groupproject.view.IsecondBannerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 小组
 * Created by Administrator on 2016/9/7.
 */
public class TeamFragmentLeft extends Fragment implements IsecondBannerView,ImostnewlistView{
    private Context mContext;
 @BindView(R.id.left_prlv)
    ListView newsList;
private View headerView;
    private ViewPager leftFragmentBanner;
    private TextView titleTv;
    private Button joinGroupBtn;
    private LinearLayout linearLayout;
    private JsonHttpUtils jsonHttputils;
    private SecondBannerAdapter bannerAdapter;
    private int cutrentPage;
    private List<LeftBannerInfo.BannerlistBean> bannerList = new ArrayList<>();
    private IsecondBannerImpl mIsecondBannerImpl;
    private ImostnewImpl mImostnewImpl;
    private int page = 1 ;
    private int perPage = 20;
    private List<MostnewListInfo.NewlistBean>  newlistBeenList = new ArrayList<>();
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
        return view;
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

        mImostnewImpl = new ImostnewImpl(this);


    }


    @Override
    public void getSecondBanner(LeftBannerInfo leftBannerInfo) {
        //获取头部视图的数据更新
             bannerList.addAll(leftBannerInfo.getBannerlist());
        //刷新适配器
             bannerAdapter.notifyDataSetChanged();

    }

    @Override
    public void queryMostNewList(MostnewListInfo mostnewListInfo) {

    }

    class SecondBannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return bannerList == null ? 0:null;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            //获取viewpaer视图
            String imageUrl = bannerList.get(position).getPic();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片
            Picasso.with(mContext).load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }


}
