package com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.adapter.HomeCategoriesAdapter;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils.Utils;

public class HomeActivity extends AppCompatActivity {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        context = this;
        initDefine();
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }



    public int[] mainCategoryList;
    String[] homeCategoryTitles;
    TextView txtExamTitle;
    int type=1;
    private void initDefine() {
        rvHomeCategories = findViewById(R.id.rvHomeCategories);
        txtExamTitle = findViewById(R.id.txtTitleSubHome);
        Intent intent=getIntent();
        type=intent.getIntExtra("Type",1);

        if(type==1){
            txtExamTitle.setText("Preschool Kids Learning");
        }else if(type==2){
            txtExamTitle.setText("Look and Choose Quiz");
        }else if(type==3){
            txtExamTitle.setText("Listen and Guess");
        }
        homeCategoryTitles = new String[]{"Alphabets", "Numbers", "Colors", "Shapes", "Animals", "Birds", "Flowers", "Fruits", "Month", "Vegetable", "Body Parts", "Clothes", "Country", "Food", "Geometry", "House", "Jobs", "School", "Sports", "Vehicle"};
        mainCategoryList = new int[]{R.drawable.home_alphabet, R.drawable.home_number, R.drawable.home_color, R.drawable.home_shape, R.drawable.home_animal, R.drawable.home_birds, R.drawable.home_flower, R.drawable.home_fruits, R.drawable.home_month, R.drawable.home_vegetable, R.drawable.home_body_parts, R.drawable.home_clothes, R.drawable.home_country, R.drawable.home_food, R.drawable.home_geometry, R.drawable.home_house, R.drawable.home_jobs, R.drawable.home_school, R.drawable.home_sports, R.drawable.home_vehicle};
        setRvAdapter();
    }


    HomeCategoriesAdapter homeCategoriesAdapter;
    RecyclerView rvHomeCategories;

    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rvHomeCategories.setLayoutManager(gridLayoutManager);
        homeCategoriesAdapter = new HomeCategoriesAdapter(context, mainCategoryList,homeCategoryTitles,type);
        rvHomeCategories.setAdapter(homeCategoriesAdapter);
    }

    public void onClickBack(View view) {
        finish();
    }
}
