package com.kidslearning.kidsplay.smartkids.derdroid.preschool.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity.video.VideoPlayActivity;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.customclasses.Constant;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.model.ModelVideo;

import java.util.ArrayList;

/**
 * Created by Naynesh Patel on 12-Feb-19.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    Context context;
    ArrayList<ModelVideo> arrOfVideoList;

    public VideoListAdapter(Context context, ArrayList<ModelVideo> arrOfVideoList) {
        this.context = context;
        this.arrOfVideoList = arrOfVideoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item_video_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context)
                .load(arrOfVideoList.get(i).getVideoThumb())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(viewHolder.imgThumbnail);

        viewHolder.txtVideoTitle.setText(arrOfVideoList.get(i).getVideoTitle());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Constant.VIDEO_ID = arrOfVideoList.get(i).getVideoId();
                context.startActivity(new Intent(context, VideoPlayActivity.class).putExtra("Position",i).putExtra("ArrayOfVideo",arrOfVideoList));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrOfVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgThumbnail;
        TextView txtVideoTitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtVideoTitle = view.findViewById(R.id.txtVideoDescription);
            cardView = view.findViewById(R.id.cardViewMain);
            imgThumbnail = view.findViewById(R.id.ivThumbnailView);
        }
    }
}