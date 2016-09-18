package com.phone1000.groupproject.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.ArticleRelaterBean;
import com.phone1000.groupproject.bean.CommentListBean;
import com.phone1000.groupproject.bean.ContentBaseUrl;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.TimeForamt;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class MainPageDetailActivity extends AppCompatActivity implements IjsonView {
//    @BindView(R.id.main_page_list)
    ListView listView;
//    @BindView(R.id.header_view_back)
//    ImageButton backeBtn;
//    @BindView(R.id.bookmark)
//    ImageButton bookBtn;
//    @BindView(R.id.comment_ib)
//    ImageButton commentBtn;
//    @BindView(R.id.share_ib)
//    ImageButton shareBtn;
    private String aid;
    private View headView;
    private Context mContext;
    private JsonHttpUtils mJsonhttpUtils;
    private InflaterHeaderView headerViewInflater;
    private RelatedListAdapter relatedAdapter;
    private List<CommentListBean> commentListBeanList = new ArrayList<>();
    private List<ArticleRelaterBean> articleRelaterBeanList = new ArrayList<>();
    private ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pgae_detail);
//        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        mContext = this;
        initAid();
        initHeaderView();
        initView();
        loadDats();
    }

    private void initHeaderView() {
        headView = LayoutInflater.from(this).inflate(R.layout.main_page_detail_headerview,null,false);
        headerViewInflater = new InflaterHeaderView(headView);
        //设置相关属性
//        settings.setDisplayZoomControls(true);
////        settings.setJavaScriptCanOpenWindowsAutomatically(true);
////        settings.setJavaScriptEnabled(true);
////        //在网页加载完图片后在显示
////        settings.setBlockNetworkImage(true);
//        settings.setUseWideViewPort(true);
////        settings.setLoadWithOverviewMode(true);
//        //设置支持缓存
//        settings.setAppCacheEnabled(true);
//        //设置支持缩放
//        settings.setSupportZoom(true);
//        settings.setBuiltInZoomControls(true);
////        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


    }

    private void loadDats() {
        //开启网络请求数据
        mJsonhttpUtils = JsonHttpUtils.newInstance();
        String url = DigtleUrl.getDetailUrl(DigtleUrl.HOME_PAGE_DETAIL_URL,aid,"");
        mJsonhttpUtils.load(url,null,this,JsonHttpUtils.REQUEST_METHOD_GET);

    }

    private void initAid() {
        //获取页面跳转过来的aid
        Intent intent = getIntent();
        aid = intent.getStringExtra("aid");
    }

    private void initView() {
        //添加头部视图
        listView = (ListView) findViewById(R.id.main_page_list);
        listView.addHeaderView(headView);
        relatedAdapter = new RelatedListAdapter();
        listView.setAdapter(relatedAdapter);


    }

    @Override
    public void getJsonString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject returnData = jsonObject.getJSONObject("returnData");
            JSONObject articledata = returnData.getJSONObject("articledata");
            JSONObject article_content = returnData.getJSONObject("article_content");
            JSONObject catdata = returnData.getJSONObject("catdata");
            JSONObject  commentlist = returnData.getJSONObject("commentlist");
            JSONObject relateds =articledata.getJSONObject("relateds");

            String content = article_content.getString("content");
            String dateLine = article_content.getString("dateline");
            String author = articledata.getString("author");
            String title =  articledata.getString("title");
            String bio = articledata.getString("bio");
            String catname = catdata.getString("catname");
            String bannerUrl = articledata.getString("pic");
            String authorId = articledata.getString("authorid");
            String userLogoUrL = DigtleUrl.getUserLogoUrl(authorId);

            Picasso.with(mContext).load(bannerUrl).into(headerViewInflater.titleImage);
            Picasso.with(mContext).load(userLogoUrL).into(headerViewInflater.authorLogo);
            headerViewInflater.titleTv.setText(title);
            headerViewInflater.catNameTv.setText(catname);
            headerViewInflater.authorTv.setText(author);
            headerViewInflater.authorMotoTv.setText(bio);
            headerViewInflater.writerTv.setText(author);
            headerViewInflater.timeTv.setText(TimeForamt.dateInfo(dateLine));


            Iterator<String> commentKesys = commentlist.keys();
            while(commentKesys.hasNext()){
                String commentKey = commentKesys.next();
                JSONObject  commentObject = commentlist.getJSONObject(commentKey);
                CommentListBean commentListBean = new CommentListBean();
                commentListBean.setAuthorid(commentObject.getString("authorid"));
                commentListBean.setMessage(commentObject.getString("message"));
                commentListBean.setAuthor(commentObject.getString("author"));
                commentListBean.setDateLine(commentObject.getString("dateline"));
                commentListBeanList.add(commentListBean);

            }
             headerViewInflater.commentNumberTv.setText(commentListBeanList.size()+"");
             headerViewInflater.checkBtn.setText("查看所有"+commentListBeanList.size()+"条评论");
             CommentListBean commentListBean1 = commentListBeanList.get(0);
             headerViewInflater.commnetName1.setText(commentListBean1.getAuthor());
             headerViewInflater.contentTv1.setText(Html.fromHtml(commentListBean1.getMessage()));
             String useLogoUrl  = DigtleUrl.getUserLogoUrl(commentListBean1.getAuthorid());
             Picasso.with(mContext).load(useLogoUrl).into(headerViewInflater.commnetLogo1);
             headerViewInflater.commentTime1.setText(TimeForamt.dateInfo(commentListBean1.getDateLine()));

            CommentListBean commentListBean2 = commentListBeanList.get(1);
            headerViewInflater.commnetName2.setText(commentListBean2.getAuthor());
            headerViewInflater.contentTv2.setText(Html.fromHtml(commentListBean2.getMessage()));
            String useLogoUrl1  = DigtleUrl.getUserLogoUrl(commentListBean2.getAuthorid());
            Picasso.with(mContext).load(useLogoUrl1).into(headerViewInflater.commnetLogo2);
            headerViewInflater.commentTime2.setText(TimeForamt.dateInfo(commentListBean2.getDateLine()));

            CommentListBean commentListBean3 = commentListBeanList.get(2);
            headerViewInflater.commnetName3.setText(commentListBean3.getAuthor());
            headerViewInflater.contentTv3.setText(Html.fromHtml(commentListBean3.getMessage()));
            String useLogoUrl2  = DigtleUrl.getUserLogoUrl(commentListBean3.getAuthorid());
            Picasso.with(mContext).load(useLogoUrl2).into(headerViewInflater.commnetLogo3);
            headerViewInflater.commentTime3.setText(TimeForamt.dateInfo(commentListBean3.getDateLine()));


            Iterator<String>  relatedsKeys = relateds.keys();
            while(relatedsKeys.hasNext()){
                String relatedsKey = relatedsKeys.next();
                JSONObject relatedsObject = relateds.getJSONObject(relatedsKey);
                ArticleRelaterBean articleRelaterBean = new ArticleRelaterBean();
                articleRelaterBean.setTitleText(relatedsObject.getString(relatedsKey));
                articleRelaterBean.setIaageUrL(relatedsObject.getString("pic_url"));
                articleRelaterBean.setAid(relatedsKey);
                articleRelaterBeanList.add(articleRelaterBean);
            }
            relatedAdapter.notifyDataSetChanged();;

            String detailBaseUrl = ContentBaseUrl.BASE_URL_HEAD+content+ContentBaseUrl.BASE_URL_FOOT;
            WebSettings settings = headerViewInflater.detailWeb.getSettings();
            settings.setUseWideViewPort(true);
            headerViewInflater.detailWeb.loadDataWithBaseURL(null,detailBaseUrl,"text/html","UTF-8",null);
            progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class RelatedListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return articleRelaterBeanList == null ? 0:articleRelaterBeanList.size();
        }

        @Override
        public Object getItem(int position) {

            return articleRelaterBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ImageViewHolder viewHolder = null;
            if(convertView == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.related_image,null,false);
             viewHolder = new ImageViewHolder(view);
            }
            else{
                viewHolder = (ImageViewHolder) view.getTag();
            }
            ArticleRelaterBean articleRelaterBean = articleRelaterBeanList.get(position);
            viewHolder.relatedTv.setText(articleRelaterBean.getTitleText());
            Picasso.with(mContext).load(articleRelaterBean.getIaageUrL()).into(viewHolder.banerImage);
            return view;
        }

    }

}
class ImageViewHolder{
    @BindView(R.id.related_banner_image)
    ImageView banerImage;
    @BindView(R.id.related_title)
    TextView relatedTv;
    public ImageViewHolder(View view){
        view.setTag(this);
        ButterKnife.bind(ImageViewHolder.this,view);
    }
}
class InflaterHeaderView{
    @BindView(R.id.image_iv)
    ImageView titleImage;
    @BindView(R.id.detali_title)
    TextView titleTv;
    @BindView(R.id.detail_info_catname)
    TextView  catNameTv;
    @BindView(R.id.detail_info_author)
    TextView authorTv;
    @BindView(R.id.detail_info_time)
    TextView timeTv;
    @BindView(R.id.detail_web)
    WebView detailWeb;
    @BindView(R.id.author_logo)
    CircleImageView authorLogo;
    @BindView(R.id.writer)
    TextView writerTv;
    @BindView(R.id.author_name)
    TextView authorNameTv;
    @BindView(R.id.follow)
    Button followBtn;
    @BindView(R.id.authot_moto)
    TextView authorMotoTv;
    @BindView(R.id.shuzi_tv)
    TextView commentNumberTv;
    @BindView(R.id.touxiang_civ)
    CircleImageView commnetLogo1;
    @BindView(R.id.biaoti_tv)
    TextView commnetName1;
    @BindView(R.id.shijian_tv)
    TextView commentTime1;
    @BindView(R.id.neirong_tv)
    TextView contentTv1;
    @BindView(R.id.touxiang2_civ)
    CircleImageView commnetLogo2;
    @BindView(R.id.biaoti2_tv)
    TextView commnetName2;
    @BindView(R.id.shijian2_tv)
    TextView commentTime2;
    @BindView(R.id.neirong2_tv)
    TextView contentTv2;
    @BindView(R.id.touxiang3_civ)
    CircleImageView commnetLogo3;
    @BindView(R.id.biaoti3_tv)
    TextView commnetName3;
    @BindView(R.id.shijian3_tv)
    TextView commentTime3;
    @BindView(R.id.neirong3_tv)
    TextView contentTv3;
    @BindView(R.id.chakan_btn)
    Button checkBtn;
    public InflaterHeaderView(View view){
        view.setTag(this);
        ButterKnife.bind(InflaterHeaderView.this,view);
    }

}