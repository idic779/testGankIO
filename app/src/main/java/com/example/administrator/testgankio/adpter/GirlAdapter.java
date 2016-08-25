package com.example.administrator.testgankio.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.testgankio.ImageAcitivity;
import com.example.administrator.testgankio.R;
import com.example.administrator.testgankio.model.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {
    private Context context;
    private List<Results> girly_list = new ArrayList<>();

    public List<Results> getResults() {
        return girly_list;
    }

    public GirlAdapter(Context context, List<Results> girly_list) {
        this.context = context;
        if (girly_list != null) {
            this.girly_list = girly_list;
        }
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlViewHolder girlViewHolder=new GirlViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girly,parent,false));

        return girlViewHolder;
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, final int position) {
        Glide.with(context).load(girly_list.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageAcitivity.class);
                intent.putExtra("url", girly_list.get(position).getUrl());
                intent.putExtra("desc", girly_list.get(position).getDesc());
                Log.d("测试3", girly_list.get(position).getUrl() + girly_list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return girly_list.size();
    }
    class GirlViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public GirlViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_girly);
        }
    }


}
