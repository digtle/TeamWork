package com.phone1000.groupproject.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.FirstBannerInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.view.IjsonView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${USER_NAME} on 2016/9/7.
 * 加载头部viewpager的类
 */
public class FindBanner implements IjsonView{
    @BindView(R.id.first_banner)
    ViewPager bannerViewPage;
    @BindView(R.id.main_page_selector_ll)
    LinearLayout selectorll;
    @BindView(R.id.first_banner_title)
    TextView titleTv;
    private  static   JsonHttpUtils jsonHttpUtils ;
    private List<FirstBannerInfo> bannerList = new ArrayList<>();
    private View view ;
    private Context mContext;
    private BannerAdapter adapter;
    private int pagePosition;

    public FindBanner(View view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
    }

    public View getHeaderView (){
        view = LayoutInflater.from(mContext).inflate(R.layout.main_page_headerview,null,false);
        ButterKnife.bind(this,view);
        adapter = new BannerAdapter();
        bannerViewPage.setAdapter(adapter);
        //获取viewpager的item
        bannerViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取点击时的item的position
                   pagePosition = position;
                for(int i = 0; i<selectorll.getChildCount();i++){
                    ImageView imageView = (ImageView) selectorll.getChildAt(i);
                    imageView.setEnabled(false);
                }
                ImageView imageView1 = (ImageView) selectorll.getChildAt(position);
                imageView1.setEnabled(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置viewpager的点击跳转事件
        bannerViewPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //将点击时的item的id传递给另外一个activity
                String aid = bannerList.get(4-pagePosition).getId();
                if(aid != null){
                    Intent intent = new Intent();
                }
                return true;
            }
        });
        //请求网络数据，获取json数据
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(DigtleUrl.MAIN_PAGE_BANNER_URL,null,this);
        return view;
    }


    @Override
    public void getJsonString(String jsonString) {
        //使用接口回调获取
        try {
            JSONObject bannerObject = new JSONObject(jsonString);
            JSONObject returnList = bannerObject.getJSONObject("returnData");
            JSONObject blockList = returnList.getJSONObject("blocklist");
            JSONObject value274= blockList.getJSONObject("274");
            Iterator<String> iterator = value274.keys();
            String key = null;
            String imageurl = null;
            String id = null;
            String title = null;
            while (iterator.hasNext()){
                key =  iterator.next();
                JSONObject jsonObject = value274.getJSONObject(key);
                imageurl = jsonObject.getString("pic_url");
                title = jsonObject.getString("title");
                id = jsonObject.getString("id");
                bannerList.add(new FirstBannerInfo(id,title,imageurl));
            }
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    class BannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return bannerList == null ? 0:bannerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //设置viewpager中的图片,title
            ImageView imageView = new ImageView(mContext);
            String title = bannerList.get(4-position).getTitle();
            String imageurl = bannerList.get(4-position).getPic_url();
            Picasso.with(mContext).load(imageurl).into(imageView);
            titleTv.setText(title);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
