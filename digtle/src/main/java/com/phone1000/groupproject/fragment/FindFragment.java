package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.adapter.GroupGridAdapter;
import com.phone1000.groupproject.adapter.TeamNewListAdapter;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.HorzotalGroupInfo;
import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.presenter.impl.IfindGroupImpl;
import com.phone1000.groupproject.presenter.impl.IthirdBannnerImpl;
import com.phone1000.groupproject.ui.TeamNewAritcleActivity;
import com.phone1000.groupproject.view.IfindGroupView;
import com.phone1000.groupproject.view.IjsonView;
import com.phone1000.groupproject.view.IthirdBannerView;
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
 * Created by Administrator on 2016/9/6.
 */
public class FindFragment extends Fragment implements IthirdBannerView,IjsonView,IfindGroupView {
@BindView(R.id.find_list_view)
    ListView  findListView;
    @BindView(R.id.find_swap_layout)
    SwipeRefreshLayout swapLayout;
    private View view;
    private View headerView;
    private Context mContext;
    private ViewPager findBanner;
    private ViewPager findGroupViewpager;
    private HorizontalScrollView horizontal;
    private LinearLayout selectorLayout;
    private Button seeAllBtn,seeMoreBtn;
    private IthirdBannnerImpl mIthirdBannerImpl;
    private IfindGroupImpl mIfindGroupImpl;
    private ImageView bannerImage;
    private TextView textTitle;
    private GridView groupGridView;
    private JsonHttpUtils mJsonhttpUtils;
    private List<LeftBannerInfo.BannerlistBean> bannerList = new ArrayList<>();
    private ViewPagerAdapter bannerAdapter;
    private TeamNewListAdapter listAdapter;
    private int index;
    private LinearLayout.LayoutParams layoutParams ;
    private GroupGridAdapter gridAdapter;
    private List<HorzotalGroupInfo.NewgroupBean> newgroupBeanList = new ArrayList<>();
    private List<MostnewListInfo>  newlistBeenList = new ArrayList<>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            findBanner.setCurrentItem(index%3);
           sendEmptyMessageDelayed(1,3000);

        }
    };
    //重写抽象工厂方法
    public static FindFragment newInstance(){
        FindFragment findFragment = new FindFragment();
        return findFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = getContext();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.find_fragment, container, false);
        ButterKnife.bind(this,view);
        lodaDatas();
        initHeaderView();
        initView();
        return view;
    }
private int dp2pix(int size){
    float scale = mContext.getResources().getDisplayMetrics().density;
    return  (int)(scale*size+0.5f);
}
    private void lodaDatas() {
        //获取头部viewpager的数据
        mIthirdBannerImpl = new IthirdBannnerImpl(this);
        mIthirdBannerImpl.getThirdBanner();
        //记载小组数据
        mIfindGroupImpl = new IfindGroupImpl(this);
        mIfindGroupImpl.getGroupInfo();
        //获取listview的数据
        mJsonhttpUtils = JsonHttpUtils.newInstance();
        mJsonhttpUtils.load(DigtleUrl.FIND_ARTITLE_INGO_URL,null,this,JsonHttpUtils.REQUEST_METHOD_GET);
    }

    private void initHeaderView() {
        headerView  = LayoutInflater.from(mContext).inflate(R.layout.find_fragment_headerview,null,false);

        findBanner = (ViewPager) headerView.findViewById(R.id.find_banner);
        findGroupViewpager = (ViewPager) headerView.findViewById(R.id.find_froup_viewpage);
        //获取小组的视图
        groupGridView = (GridView) headerView.findViewById(R.id.horizontal_horizontal_grid);
        layoutParams = new LinearLayout.LayoutParams(dp2pix(1250),dp2pix(240));
        groupGridView.setLayoutParams(layoutParams);
        gridAdapter = new GroupGridAdapter(newgroupBeanList,mContext);
        groupGridView.setAdapter(gridAdapter);

        seeAllBtn = (Button) headerView.findViewById(R.id.see_all_btn);
        seeMoreBtn = (Button) headerView.findViewById(R.id.see_more_btn);
       //绑定头部的viewpager
        bannerAdapter = new ViewPagerAdapter();
        findBanner.setAdapter(bannerAdapter);
        mHandler.sendEmptyMessage(1);
        findBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        //添加头部视图
       findListView.addHeaderView(headerView);
       listAdapter = new TeamNewListAdapter(mContext,newlistBeenList);
        findListView.setAdapter(listAdapter);
        //设置下拉刷新监听器
        swapLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swapLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        newlistBeenList.clear();
                        mJsonhttpUtils.load(DigtleUrl.FIND_ARTITLE_INGO_URL,null,FindFragment.this,JsonHttpUtils.REQUEST_METHOD_GET);
                        //更新完后调用该方法结束刷新

                    }
                },2000);
            }
        });
        findListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tid = newlistBeenList.get(position-1).getTid();
                Intent intent = new Intent(mContext, TeamNewAritcleActivity.class);
                intent.putExtra("tid",tid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void quereyThirdBanner(LeftBannerInfo leftBannerInfo) {
        //获取头部viewpager的数据
            bannerList.addAll(leftBannerInfo.getBannerlist());
           bannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getJsonString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject  returnData = jsonObject.getJSONObject("returnData");
            JSONArray jsonArray = returnData.getJSONArray("hotlist");
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
                mostnewListInfo.setTid(jsonObject1.getString("tid"));
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

    @Override
    public void getFindGroup(HorzotalGroupInfo horzotalGroupInfo) {
        //获取小组的数据
                  newgroupBeanList.addAll(horzotalGroupInfo.getNewgroup());
                gridAdapter.notifyDataSetChanged();
    }

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return bannerList == null ?0:bannerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view== object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view =LayoutInflater.from(mContext).inflate(R.layout.find_fragment_headerone_item,null,false);
            bannerImage = (ImageView) view.findViewById(R.id.find_banner);
            textTitle = (TextView) view.findViewById(R.id.title_tv);
           LeftBannerInfo.BannerlistBean bannerBean = bannerList.get(position);
            String imageUrl = bannerBean.getPic();
            Picasso.with(mContext).load(imageUrl).into(bannerImage);
            textTitle.setText(bannerBean.getTitle());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
              container.removeView((View) object);
        }
    }



}
