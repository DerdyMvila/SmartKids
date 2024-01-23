package com.kidslearning.kidsplay.smartkids.derdroid.preschool.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity.video.ListVideoActivity;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.customclasses.Constant;

/**
 * Created by Naynesh Patel on 11-Feb-19.
 */
public class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.ViewHolder> {

    Context context;
    String[] videocategory;
    int[] tumbnailList;

    public VideoCategoryAdapter(Context context, String[] videocategory, int[] tumbnailList) {
        this.context = context;
        this.videocategory = videocategory;
        this.tumbnailList = tumbnailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(tumbnailList[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgHomeCategory);
        viewHolder.lloutHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Constant.VIDEO_CATEGORY_ID=String.valueOf(i);
                context.startActivity(new Intent(context, ListVideoActivity.class).putExtra("Category",videocategory[i]));
            }
        });
    }

    @Override
    public int getItemCount() {
        return videocategory.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeCategory;
        LinearLayout lloutHome;


        public ViewHolder(@NonNull View view) {
            super(view);
            imgHomeCategory = view.findViewById(R.id.imgHomeCategory);
            lloutHome = view.findViewById(R.id.lloutCardHome);
        }
    }
}
