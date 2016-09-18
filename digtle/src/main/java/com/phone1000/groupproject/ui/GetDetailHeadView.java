package com.phone1000.groupproject.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.TimeForamt;
import com.phone1000.groupproject.customview.CustomListview;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.view.IjsonView;
import com.squareup.picasso.MemoryPolicy;
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
 * Created by ${USER_NAME} on 2016/9/13.
 */
public class GetDetailHeadView implements IjsonView {
    private JsonHttpUtils jsonHttpUtils;
    private String tid;
    private Context mContex;
    private CircleImageView authorLogo;
    private TextView titleTv,userNameTv;
    private TextView summaryTv;
    private TextView timeDataTv;
    private CustomListview imageList;
    private TextView thumbTv;
    private GridView imageGrid;
    private TextView commentNumber;
    private GridAdapter gridAdapter;
    private ImageListAdapter imageListAdapter;
    private List<String> imageUrlList  = new ArrayList<>();
    private List<String> thumbUserList = new ArrayList<>();
    public GetDetailHeadView(String tid, Context mContex) {
        this.tid = tid;
        this.mContex = mContex;
    }
   private Handler mHandler = new  Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           imageList.setSelection(ListView.FOCUS_DOWN);
       }
   };
    private void initDatas() {
        //加载数据
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(DigtleUrl.getDetailUrl(DigtleUrl.FIND_ARTICLE_DETAIL_URL,tid,DigtleUrl.FIND_ARTICLE_DETAIL_FOOTURL),null,this,JsonHttpUtils.REQUEST_METHOD_GET);
        //

    }
   public  View  initHeaderView(View headerView) {
        //加载头部视图
        headerView = LayoutInflater.from(mContex).inflate(R.layout.a,null,false);
        authorLogo = (CircleImageView) headerView.findViewById(R.id.home_avatar_image);
        titleTv = (TextView) headerView.findViewById(R.id.detail_title_tv);
        userNameTv = (TextView) headerView.findViewById(R.id.user_name_tv);
        summaryTv = (TextView) headerView.findViewById(R.id.article_summay);
        timeDataTv = (TextView) headerView.findViewById(R.id.time_data);
        imageList = (CustomListview) headerView.findViewById(R.id.pic_image_list);
       thumbTv = (TextView) headerView.findViewById(R.id.thumbs_number_tv);
        imageGrid = (GridView) headerView.findViewById(R.id.logo_grid);
        commentNumber = (TextView) headerView.findViewById(R.id.new_comment_number);
       //设置适配器

       gridAdapter = new GridAdapter();
       imageListAdapter = new ImageListAdapter();
       //绑定适配器
       imageGrid.setAdapter(gridAdapter);
       imageList.setAdapter(imageListAdapter);

       // 加载数据
        initDatas();
        return  headerView;

    }
    private int dp2pix(int size){
        float scale = mContex.getResources().getDisplayMetrics().density;
        return  (int)(size*scale+0.5f);
    }
    @Override
    public void getJsonString(String jsonString) {
        //解析数据
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject returnData = jsonObject.getJSONObject("returnData");
            JSONObject thread_info = returnData.getJSONObject("thread_info");
            JSONObject attachment_data = thread_info.getJSONObject("attachment_data");
            JSONObject likeList = thread_info.getJSONObject("likelist");
            Iterator<String> imageKeys = attachment_data.keys();
            String key = null;
            String thumbKey = null;
            while (imageKeys.hasNext()) {
                key = imageKeys.next();
                String imageUrl = attachment_data.getString(key);
                imageUrlList.add(imageUrl);
            }
            Iterator<String> thumbKeys = likeList.keys();
            while (thumbKeys.hasNext()) {
                thumbKey = thumbKeys.next();
                JSONObject user = likeList.getJSONObject(thumbKey);
                String thumbUrl = user.getString("avatar");
                thumbUserList.add(thumbUrl);
            }
            String authorLogoUrl = thread_info.getString("author_avatar");
            String authorName = thread_info.getString("author");
            String title = thread_info.getString("subject");
            String summary = thread_info.getString("message");
            String recommend_add = thread_info.getString("recommend_add");
            String recommends = thread_info.getString("recommends");
            String datelinew = thread_info.getString("dateline");
            Picasso.with(mContex).load(authorLogoUrl).into(authorLogo);
            userNameTv.setText(authorName);
            titleTv.setText(title);
            summaryTv.setText(summary);
            timeDataTv.setText(TimeForamt.createTime(datelinew));

            thumbTv.setText(recommend_add + "人赞了");
            commentNumber.setText(recommends);
            gridAdapter.notifyDataSetChanged();
            imageListAdapter.notifyDataSetChanged();
            imageList.setSelection(ListView.FOCUS_DOWN);
//            mHandler.sendEmptyMessageDelayed(0,500);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
        class GridAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return thumbUserList == null ? 0 : thumbUserList.size();
            }

            @Override
            public Object getItem(int position) {
                return thumbUserList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
              View view = LayoutInflater.from(mContex).inflate(R.layout.detail_grid_item,null,false);
                CircleImageView logoImage = (CircleImageView) view.findViewById(R.id.detail_item_image);
                String imageUrl = thumbUserList.get(position);
              Picasso.with(mContex).load(imageUrl).placeholder(R.drawable.user_pic_default).into(logoImage);
               return view;

            }
        }
        class  ImageListAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return imageUrlList == null ?0:imageUrlList.size();
            }

            @Override
            public Object getItem(int position) {
                return imageUrlList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                ViewHolder viewHolder =null;
                if(convertView == null){
                    view = LayoutInflater.from(mContex).inflate(R.layout.image_list_item,null,false);
                    viewHolder = new ViewHolder(view);
                }else{
                    viewHolder = (ViewHolder) view.getTag();
                }
                String imageUrl = imageUrlList.get(position);
                viewHolder.imageView.setImageResource(R.drawable.article_default);
                Picasso.with(mContex).load(imageUrl).memoryPolicy(MemoryPolicy.NO_STORE).into(viewHolder.imageView);
                return view;
            }
            class ViewHolder{
                @BindView(R.id.image_item)
                ImageView imageView;
                public  ViewHolder(View view){
                    view.setTag(this);
                    ButterKnife.bind(this,view);
                }
            }
        }

        }


