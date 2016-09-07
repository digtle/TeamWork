package com.phone1000.groupproject.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
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
    private Context mContext;
    private BannerAdapter adapter;
    private int pagePosition;
    private int index;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            bannerViewPage.setCurrentItem(index%5);
            sendEmptyMessageDelayed(0,3000);
        }
    };
    public FindBanner( Context mContext) {
        this.mContext = mContext;
    }

    public View getHeaderView (View headerview){
        headerview = LayoutInflater.from(mContext).inflate(R.layout.main_page_headerview,null,false);
        ButterKnife.bind(this,headerview);
        mHandler.sendEmptyMessage(0);
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
                String title = bannerList.get(4-position).getTitle();
                titleTv.setText(title);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //请求网络数据，获取json数据
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(DigtleUrl.MAIN_PAGE_BANNER_URL,null,this);
        return headerview;
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
            adapter = new BannerAdapter();
            bannerViewPage.setAdapter(adapter);
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
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            String imageurl = bannerList.get(4-position).getPic_url();
            Picasso.with(mContext).load(imageurl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
