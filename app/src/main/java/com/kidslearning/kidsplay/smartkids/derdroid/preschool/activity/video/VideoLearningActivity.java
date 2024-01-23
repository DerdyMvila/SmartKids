package com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity.video;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.adapter.VideoCategoryAdapter;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils.Utils;

public class VideoLearningActivity extends AppCompatActivity {

    String[] videocategory;
    int[] tumbnailList;

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_learning);
        context = this;
        getSupportActionBar().hide();
        initDefine();
        videocategory = new String[]{"ABC Songs", "Number Songs", "Color Songs", "Animal Songs", "Shape Songs", "Vehicle Songs", "Fruit Songs", "Vegetable Songs", "Day Songs", "Month Songs", "Clothes Songs"};
        tumbnailList = new int[]{R.drawable.vt_abc, R.drawable.vt_number, R.drawable.vt_color, R.drawable.vt_animal, R.drawable.vt_shape, R.drawable.vt_vehicle, R.drawable.vt_fruit, R.drawable.vt_vegetable, R.drawable.vt_day, R.drawable.vt_month, R.drawable.vt_clothes};
        setRvAdapter();

        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }



    private void initDefine() {
        rvVideoCategory = findViewById(R.id.rvVideoCategory);
    }

    VideoCategoryAdapter videoCategoryAdapter;
    RecyclerView rvVideoCategory;

    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
        rvVideoCategory.setLayoutManager(gridLayoutManager);
        videoCategoryAdapter = new VideoCategoryAdapter(context, videocategory, tumbnailList);
        rvVideoCategory.setAdapter(videoCategoryAdapter);
    }

    public void onClickBack(View view) {
        finish();
    }
}
