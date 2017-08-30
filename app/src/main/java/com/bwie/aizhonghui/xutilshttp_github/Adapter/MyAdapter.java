package com.bwie.aizhonghui.xutilshttp_github.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.aizhonghui.xutilshttp_github.Bean.Mybean;
import com.bwie.aizhonghui.xutilshttp_github.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/8/29.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<Mybean> mlist;

    public MyAdapter(Context context, List<Mybean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context, R.layout.item,null);
        }
        TextView title=view.findViewById(R.id.tv_title);
        TextView thum=view.findViewById(R.id.tv_thum);
        ImageView ivimg=view.findViewById(R.id.iv_img);
        title.setText(mlist.get(i).title);
        thum.setText(mlist.get(i).author_name);
        ImageLoader.getInstance().displayImage(mlist.get(i).pics,ivimg);
        return view;
    }
}
