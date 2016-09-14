package com.phone1000.groupproject.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.CommentListBean;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${USER_NAME} on 2016/9/13.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {
    private List<CommentListBean> commentListBeanList;
    private Context mContext;

    public ExpandListAdapter(List<CommentListBean> commentListBeanList, Context mContext) {
        this.commentListBeanList = commentListBeanList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        Log.d("tag",commentListBeanList.size()+"");
        return commentListBeanList == null ? 0: commentListBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
     List<CommentListBean.PostComment>  postCommentList =new ArrayList<>();
        Log.d("tag",groupPosition+"");
        if(groupPosition < commentListBeanList.size()){
            postCommentList.addAll(commentListBeanList.get(groupPosition).getPostCommentList());
        }
        return postCommentList == null? 0:(postCommentList.size());
    }

    @Override
    public Object getGroup(int groupPosition) {

        return commentListBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return commentListBeanList.get(groupPosition).getPostCommentList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view =convertView;
        ParentViewHolder viewHolder = null;
        if(view ==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.expandle_list_parent,null,false);
            viewHolder = new ParentViewHolder(view);
        }
        else{
            viewHolder = (ParentViewHolder) view.getTag();
        }
        CommentListBean commentListBean = commentListBeanList.get(groupPosition);
        viewHolder.authorNameTv.setText(commentListBean.getAuthor());
        viewHolder.contentTv.setText(Html.fromHtml(commentListBean.getMessage()));
        String imageUrl = DigtleUrl.getUserLogoUrl(commentListBean.getAuthorid());
        Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.user_pic_default).into(viewHolder.userLogo);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view =convertView;
        ChirldViewHolder viewHolder = null;
        if(view ==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.expandle_list_chilrd,null,false);
            viewHolder = new ChirldViewHolder(view);
        }
        else{
            viewHolder = (ChirldViewHolder) view.getTag();
        }
        CommentListBean.PostComment postComment = commentListBeanList.get(groupPosition).getPostCommentList().get(childPosition);
        viewHolder.authorNameTv.setText(postComment.getAuthor());
        viewHolder.contentTv.setText(Html.fromHtml(postComment.getComment()));
        String imageUrl = DigtleUrl.getUserLogoUrl(postComment.getAuthorid());
        Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.user_pic_default).into(viewHolder.userLogo);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class ParentViewHolder{
        @BindView(R.id.comment_logo)
        CircleImageView userLogo;
        @BindView(R.id.comment_name)
        TextView authorNameTv;
        @BindView(R.id.commnt_content)
        TextView contentTv;
        @BindView(R.id.tiem_data)
        TextView dataTv;
        public ParentViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
    class ChirldViewHolder{
        @BindView(R.id.post_comment_logo)
        CircleImageView userLogo;
        @BindView(R.id.post_comment_name)
        TextView authorNameTv;
        @BindView(R.id.post_commnt_content)
        TextView contentTv;
        @BindView(R.id.post_tiem_data)
        TextView dataTv;
        public  ChirldViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
