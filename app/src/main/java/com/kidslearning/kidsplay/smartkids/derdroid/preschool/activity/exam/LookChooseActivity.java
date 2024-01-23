package com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity.exam;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.adapter.ExamQuestionAdapter;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.model.LearningDataModel;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LookChooseActivity extends AppCompatActivity {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);
        getSupportActionBar().hide();
        context = this;
        initDefine();
    }



    ImageView iVQuestion;
    int correctPosition;
    Random random;

    TextView txtTitleSubHome;
    private void initDefine() {
        iVQuestion = findViewById(R.id.iVQuestion);
        rv_exam = findViewById(R.id.rv_exam);
        txtTitleSubHome = findViewById(R.id.txtTitleSubHome);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rv_exam.setLayoutManager(gridLayoutManager);
        Intent intent = getIntent();
        prepareDataForLearning(intent.getIntExtra("categoryPosition", 0));
        txtTitleSubHome.setText(intent.getStringExtra("SubCate"));
        getRandomArray();
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }

    ArrayList<ArrayList<LearningDataModel>> arrOfPrevious = new ArrayList<>();
//    ArrayList<Integer> arrOfPreviousPosition = new ArrayList<>();

    private void getRandomArray() {
        random = new Random();
        examQuestionAnswerList = new ArrayList<>();
        correctPosition = random.nextInt(learningDataModelArrayList.size());
        examQuestionAnswerList.add(learningDataModelArrayList.get(correctPosition));
        Glide.with(context)
                .load(learningDataModelArrayList.get(correctPosition).image)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iVQuestion);

        do {
            int number = random.nextInt(learningDataModelArrayList.size());
            if (!examQuestionAnswerList.contains(learningDataModelArrayList.get(number))) {
                examQuestionAnswerList.add(learningDataModelArrayList.get(number));
                examQuestionAnswerList.get(examQuestionAnswerList.size() - 1).setTrue(false);
            }
        }
        while (examQuestionAnswerList.size() != 4);
        Collections.shuffle(examQuestionAnswerList);
        setRvAdapter(isPreviousShow);
    }

    RecyclerView rv_exam;
    public ArrayList<LearningDataModel> examQuestionAnswerList;
    ExamQuestionAdapter examQuestionAdapter;


    private void setRvAdapter(boolean isPreviousShow) {
        if (isPreviousShow) {
            LearningDataModel learningDataModel = null;
            for (int i = 0; i < examQuestionAnswerList.size(); i++) {
                if (examQuestionAnswerList.get(i).isTrue()) {
                    learningDataModel = examQuestionAnswerList.get(i);
                    Glide.with(context)
                            .load(learningDataModel.image)
                            .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into(iVQuestion);
                    break;
                }
            }
            examQuestionAdapter = new ExamQuestionAdapter(context, examQuestionAnswerList, learningDataModel);
        } else {
            examQuestionAdapter = new ExamQuestionAdapter(context, examQuestionAnswerList, learningDataModelArrayList.get(correctPosition));
        }
        rv_exam.setAdapter(examQuestionAdapter);
    }

    public void onClickNext(View view) {
        getRandomArray();
    }


    boolean isPreviousShow = false;

    public void onClickPrev(View view) {
        getRandomArray();
    }


    public void onClickBack(View view) {
        finish();
    }

    ArrayList<LearningDataModel> learningDataModelArrayList;

    public void prepareDataForLearning(int i2) {
        if (i2 == 0) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.a, "A for Apple", "Apple"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.b, "B for Ball", "Ball"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.c, "C for Cat", "Cat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.d, "D for Dog", "Dog"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.e, "E for Elephant", "Elephant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.f, "F for Fish", "Fish"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.g, "G for Goat", "Goat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.h, "H for Horse", "Horse"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.i, "I for Ice cream", "Ice cream"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.j, "J for Joker", "Joker"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.k, "K for Kite", "Kite"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.l, "L for Lion", "Lion"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.m, "M for Monkey", "Monkey"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.n, "N for Nest", "Nest"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.o, "O for Orange", "Orange"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.p, "P for Parrot", "Parrot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.q, "Q for Queen", "Queen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.r, "R for Rabbit", "Rabbit"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.s, "S for Sun", "Sun"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.t, "T for Train", "Train"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.u, "U for Umbrella", "Umbrella"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.v, "V for Violin", "Violin"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.w, "W for Watch", "Watch"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.x, "X for Xylophone", "Xylophone"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.y, "Y for Yak", "Yak"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.z, "Z for Zebra", "Zebra"));
        } else if (i2 == 1) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.zero_0, "Zero", "Zero"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.one_1, "One", "One"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.two_2, "Two", "Two"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.three_3, "Three", "Three"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.four_4, "Four", "Four"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.five_5, "Five", "Five"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.six_6, "Six", "Six"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.seven_7, "Seven", "Seven"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eight_8, "Eight", "Eight"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nine_9, "Nine", "Nine"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ten_10, "Ten", "Ten"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eleven_11, "Eleven", "Eleven"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.twelve_12, "Twelve", "Twelve"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.thirteen_13, "Thirteen", "Thirteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fourteen_14, "Fourteen", "Fourteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fifteen_15, "Fifteen", "Fifteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sixteen_16, "Sixteen", "Sixteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.seventeen_17, "Seventeen", "Seventeen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eighteen_18, "Eighteen", "Eighteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nineteen_19, "Nineteen", "Nineteen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.twenty_20, "Twenty", "Twenty"));
        } else if (i2 == 2) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.green, "Green", "Green"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pink, "Pink", "Pink"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.red, "Red", "Red"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.black, "Black", "Black"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.aqua, "Aqua", "Aqua"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.blue, "Blue", "Blue"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.brown, "Brown", "Brown"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.slate, "Slate", "Slate"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.violet, "Violet", "Violet"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.white, "White", "White"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.yellow, "Yellow", "Yellow"));
        } else if (i2 == 3) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.circle, "Circle", "Circle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.square, "Square", "Square"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.decagon, "Decagon", "Decagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ellipse, "Ellipse", "Ellipse"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hexagon, "Hexagon", "Hexagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.triangle, "Triangle", "Triangle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.octagon, "Octagon", "Octagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.parallelogram, "Parallelogram", "Parallelogram"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pentagon, "Pentagon", "Pentagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rectangle, "Rectangle", "Rectangle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rhombus, "Rhombus", "Rhombus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.right_triangle, "Right Triangle", "Right Triangle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.trapezoid, "Trapezoid", "Trapezoid"));
        } else if (i2 == 4) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bear, "Bear", "Bear"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bison, "Bison", "Bison"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.black_leopard, "Black Leopard", "Black Leopard"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cheetah, "Cheetah", "Cheetah"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chimpanzee, "Chimpanzee", "Chimpanzee"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chipmuck, "Chipmuck", "Chipmuck"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cougar, "Cougar", "Cougar"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.deer, "Deer", "Deer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.elephant, "Elephant", "Elephant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fox, "Fox", "Fox"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.giraffe, "Giraffe", "Giraffe"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.gorilla, "Gorilla", "Gorilla"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hedgehog, "Hedgehog", "Hedgehog"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hippopotamus, "Hippopotamus", "Hippopotamus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hyena, "Hyena", "Hyena"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.jackal, "Jackal", "Jackal"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.jaguar, "Jaguar", "Jaguar"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.kangaroo, "Kangaroo", "Kangaroo"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.koala_bear, "Koala Bear", "Koala Bear"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lion, "Lion", "Lion"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.meerkat, "Meerkat", "Meerkat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mongoose, "Mongoose", "Mongoose"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.monkey, "Monkey", "Monkey"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.opossum, "Opossum", "Opossum"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.otter, "Otter", "Otter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.panda, "Panda", "Panda"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.polar_bear, "Polar Bear", "Polar Bear"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.porcupine, "Porcupine", "Porcupine"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.raccoon, "Raccoon", "Raccoon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.red_panda, "Red Panda", "Red Panda"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rhinoceros, "Rhinoceros", "Rhinoceros"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.scimitar_oryx, "Scimitar Oryx", "Scimitar Oryx"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.squirrel, "Squirrel", "Squirrel"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tiger, "Tiger", "Tiger"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.wolf, "Wolf", "Wolf"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.wombat, "Wombat", "Wombat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.zebra, "Zebra", "Zebra"));
        } else if (i2 == 5) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.canary, "Canary", "Canary"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.crow, "Crow", "Crow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dove, "Dove", "Dove"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.duck, "Duck", "Duck"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eagle, "Eagle", "Eagle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hoopoe, "Hoopoe", "Hoopoe"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hornbill, "Hornbill", "Hornbill"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.kingfisher, "Kingfisher", "Kingfisher"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.kite, "Kite", "Kite"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lapwing, "Lapwing", "Lapwing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mynah, "Mynah", "Mynah"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nightingale, "Nightingale", "Nightingale"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.owl, "Owl", "Owl"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.parrot, "Parrot", "Parrot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.peacock, "Peacock", "Peacock"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.peahen, "Peahen", "Peahen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pheasant, "Pheasant", "Pheasant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pigeon, "Pigeon", "Pigeon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.puffin, "Puffin", "Puffin"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.quail, "Quail", "Quail"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.robin, "Robin", "Robin"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sparrow, "Sparrow", "Sparrow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.swallow, "Swallow", "Swallow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.toucan, "Toucan", "Toucan"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.vulture, "Vulture", "Vulture"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.wagtail, "Wagtail", "Wagtail"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.waxwing, "Waxwing", "Waxwing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.woodpecker, "Woodpecker", "Woodpecker"));
        } else if (i2 == 7) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.apple, "Apple", "Apple"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.apricot, "Apricot", "Apricot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.avocado, "Avocado", "Avocado"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.blackberry, "Blackberry", "Blackberry"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.blackcurrant, "Blackcurrant", "Blackcurrant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.blueberry, "Blueberry", "Blueberry"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cherry, "Cherry", "Cherry"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.coconut, "Coconut", "Coconut"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fig, "Fig", "Fig"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.grape, "Grape", "Grape"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.kiwi, "Kiwi", "Kiwi"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lemon, "Lemon", "Lemon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lime, "Lime", "Lime"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lychee, "Lychee", "Lychee"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mango, "Mango", "Mango"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nectarine, "Nectarine", "Nectarine"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.orange, "Orange", "Orange"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.papaya, "Papaya", "Papaya"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.passion, "Passion", "Passion"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.peach, "Peach", "Peach"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pear, "Pear", "Pear"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pineapple, "Pineapple", "Pineapple"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.plum, "Plum", "Plum"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.quince, "Quince", "Quince"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.raspberry, "Raspberry", "Raspberry"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.strawberry, "Strawberry", "Strawberry"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.watermelon, "Watermelon", "Watermelon"));
        } else if (i2 == 6) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.arum_lily, "Arum Lily", "Arum Lily"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.aster, "Aster", "Aster"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bird_of_paradise, "Bird Of Paradise", "Bird Of Paradise"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bougainvillea, "Bougainvillea", "Bougainvillea"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.buttercup, "Buttercup", "Buttercup"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.calendula, "Calendula", "Calendula"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.canna, "Canna", "Canna"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cockscomb, "Cockscomb", "Cockscomb"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.daffodils, "Daffodils", "Daffodils"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dahlia, "Dahlia", "Dahlia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.daisy, "Daisy", "Daisy"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dianthus, "Dianthus", "Dianthus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.gladiolus, "Gladiolus", "Gladiolus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hibiscus, "Hibiscus", "Hibiscus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.jasmine, "Jasmine", "Jasmine"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lavender, "Lavender", "Lavender"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lilac, "Lilac", "Lilac"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lily, "Lily", "Lily"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lotus, "Lotus", "Lotus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.morning_glory, "Morning Glory", "Morning Glory"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nerium_oleander, "Nerium Oleander", "Nerium Oleander"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.orchids, "Orchids", "Orchids"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.peony, "Peony", "Peony"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.periwinkle, "Periwinkle", "Periwinkle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.petunia, "Petunia", "Petunia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.poppy, "Poppy", "Poppy"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.purple_mallow, "Purple Mallow", "Purple Mallow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rose, "Rose", "Rose"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sunflower, "Sunflower", "Sunflower"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tulips, "Tulips", "Tulips"));
        } else if (i2 == 8) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.january, "January", "January"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.february, "February", "February"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.march, "March", "March"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.april, "April", "April"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.may, "May", "May"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.june, "June", "June"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.july, "July", "July"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.august, "August", "August"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.september, "September", "September"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.october, "October", "October"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.november, "November", "November"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.december, "December", "December"));
        } else if (i2 == 9) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.asparagus, "Asparagus", "Asparagus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.broccoli, "Broccoli", "Broccoli"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.brussels_sprouts, "Brussels Sprouts", "Brussels Sprouts"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.carrot, "Carrot", "Carrot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cauliflower, "Cauliflower", "Cauliflower"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cucumber, "Cucumber", "Cucumber"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eggplant, "Eggplant", "Eggplant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.garlic, "Garlic", "Garlic"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lettuce, "Lettuce", "Lettuce"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mint, "Mint", "Mint"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mushroom, "Mushroom", "Mushroom"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.okra, "Okra", "Okra"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.onion, "Onion", "Onion"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.peaspeas, "Peaspeas", "Peaspeas"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.potato, "Potato", "Potato"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.radish, "Radish", "Radish"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.red_cabbage, "Red Cabbage", "Red Cabbage"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.spinach, "Spinach", "Spinach"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.squash, "Squash", "Squash"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.string_beans, "String Beans", "String Beans"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tomato, "Tomato", "Tomato"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.turnip, "Turnip", "Turnip"));
        } else if (i2 == 10) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ankle, "Ankle", "Ankle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.arm, "Arm", "Arm"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chest, "Chest", "Chest"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ear, "Ear", "Ear"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.elbow, "Elbow", "Elbow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eye, "Eye", "Eye"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fingers, "Fingers", "Fingers"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.foot, "Foot", "Foot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hair, "Hair", "Hair"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.knee, "Knee", "Knee"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.leg, "Leg", "Leg"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lips, "Lips", "Lips"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mouth, "Mouth", "Mouth"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.neck, "Neck", "Neck"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nose, "Nose", "Nose"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.shoulder, "Shoulder", "Shoulder"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.stomach, "Stomach", "Stomach"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.thigh, "Thigh", "Thigh"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.thumb, "Thumb", "Thumb"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.toe, "Toe", "Toe"));
        } else if (i2 == 11) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.shirt, "Shirt", "Shirt"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.shoes, "Shoes", "Shoes"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pyjamas, "Pyjamas", "Pyjamas"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sock, "Sock", "Sock"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.gloves, "Gloves", "Gloves"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.skirt, "Skirt", "Skirt"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.slipper, "Slipper", "Slipper"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sweater, "Sweater", "Sweater"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bathrobe, "Bathrobe", "Bathrobe"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.jeans, "Jeans", "Jeans"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.boot, "Boot", "Boot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dress, "Dress", "Dress"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.overalls, "Overalls", "Overalls"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.shorts, "Shorts", "Shorts"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.jacket, "Jacket", "Jacket"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.scarf, "Scarf", "Scarf"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.belt, "Belt", "Belt"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hat, "Hat", "Hat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.glasses, "Glasses", "Glasses"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.suit, "Suit", "Suit"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.veterinarian, "Veterinarian", "Veterinarian"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tailor, "Tailor", "Tailor"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.waiter, "Waiter", "Waiter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.welder, "Welder", "Welder"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.traffic_warden, "Traffic Warden", "Traffic Warden"));
        } else if (i2 == 12) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.argentina, "Argentina", "Argentina"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.austria, "Austria", "Austria"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.belgium, "Belgium", "Belgium"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.brazil, "Brazil", "Brazil"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cambodia, "Cambodia", "Cambodia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.canada, "Canada", "Canada"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.croatia, "Croatia", "Croatia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cuba, "Cuba", "Cuba"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.denmark, "Denmark", "Denmark"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.england, "England", "England"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.france, "France", "France"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.germany, "Germany", "Germany"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.greece, "Greece", "Greece"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.indian, "Indian", "Indian"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.indonesia, "Indonesia", "Indonesia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.italy, "Italy", "Italy"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.japan, "Japan", "Japan"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.laos, "Laos", "Laos"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.malaysia, "Malaysia", "Malaysia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mexico, "Mexico", "Mexico"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.myanmar, "Myanmar", "Myanmar"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.netherlands, "Netherlands", "Netherlands"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pakistan, "Pakistan", "Pakistan"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.philippine, "Philippine", "Philippine"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.portugal, "Portugal", "Portugal"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.russia, "Russia", "Russia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.saudi_arabia, "Saudi Arabia", "Saudi Arabia"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.singapore, "Singapore", "Singapore"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.south_korea, "South Korea", "South Korea"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.spain, "Spain", "Spain"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sweden, "Sweden", "Sweden"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.thailand, "Thailand", "Thailand"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.united_states, "United States", "United States"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.vietnam, "Vietnam", "Vietnam"));
        } else if (i2 == 13) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pizza, "Pizza", "Pizza"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.biscuits, "Biscuits", "Biscuits"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chip, "Chip", "Chip"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cake, "Cake", "Cake"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.noodles, "Noodles", "Noodles"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.water, "Water", "Water"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sandwich, "Sandwich", "Sandwich"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ice_cream, "Ice Cream", "Ice Cream"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.beer, "Beer", "Beer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hamburger, "Hamburger", "Hamburger"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tea, "Tea", "Tea"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ham, "Ham", "Ham"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.yogurt, "Yogurt", "Yogurt"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chocolate, "Chocolate", "Chocolate"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rice, "Rice", "Rice"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.soda, "Soda", "Soda"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.juice, "Juice", "Juice"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.coffee, "Coffee", "Coffee"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bread, "Bread", "Bread"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.soup, "Soup", "Soup"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.butter, "Butter", "Butter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cheese, "Cheese", "Cheese"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.milk, "Milk", "Milk"));
        } else if (i2 == 14) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.arrow, "Arrow", "Arrow"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.circle, "Circle", "Circle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cone, "Cone", "Cone"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.crescent, "Crescent", "Crescent"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cube, "Cube", "Cube"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cuboid, "Cuboid", "Cuboid"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cylinder, "Cylinder", "Cylinder"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.diamond, "Diamond", "Diamond"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.heart, "Heart", "Heart"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hexagon, "Hexagon", "Hexagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.oval, "Oval", "Oval"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.parallelogram, "Parallelogram", "Parallelogram"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pentagon, "Pentagon", "Pentagon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.polygon, "Polygon", "Polygon"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pyramid, "Pyramid", "Pyramid"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.rectangle, "Rectangle", "Rectangle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sphere, "Sphere", "Sphere"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.star, "Star", "Star"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.trapezoid, "Trapezoid", "Trapezoid"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.triangle, "Triangle", "Triangle"));
        } else if (i2 == 15) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bookcase, "Bookcase", "Bookcase"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chair, "Chair", "Chair"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.newspaper, "Newspaper", "Newspaper"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.sofasofa, "Sofa", "Sofa"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.picture, "Picture", "Picture"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.watch, "Watch", "Watch"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.brush, "Brush", "Brush"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.television, "Television", "Television"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.table, "Table", "Table"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.coin, "Coin", "Coin"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.phone, "Phone", "Phone"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bar_stool, "Bar Stool", "Bar Stool"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.laptop, "Laptop", "Laptop"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mirror, "Mirror", "Mirror"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.scissors, "Scissors", "Scissors"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.umbrella, "Umbrella", "Umbrella"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.clock, "Clock", "Clock"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bucket, "Bucket", "Bucket"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cup, "Cup", "Cup"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.key, "Key", "Key"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.door, "Door", "Door"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.glass, "Glass", "Glass"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.armchair, "Armchair", "Armchair"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.window, "Window", "Window"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.knife, "Knife", "Knife"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.wallet, "Wallet", "Wallet"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bottle, "Bottle", "Bottle"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mobile_phone, "Mobile Phone", "Mobile Phone"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bed, "Bed", "Bed"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lock, "Lock", "Lock"));
        } else if (i2 == 16) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.accountant, "Accountant", "Accountant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.architect, "Architect", "Architect"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.astronomer, "Astronomer", "Astronomer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.author, "Author", "Author"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.baker, "Baker", "Baker"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bricklayer, "Bricklayer", "Bricklayer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.butcher, "Butcher", "Butcher"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.carpenter, "Carpenter", "Carpenter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chef, "Chef", "Chef"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cleaner, "Cleaner", "Cleaner"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dentist, "Dentist", "Dentist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.doctor, "Doctor", "Doctor"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.driver, "Driver", "Driver"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dustman, "Dustman", "Dustman"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.electrician, "Electrician", "Electrician"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.engineer, "Engineer", "Engineer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.farmer, "Farmer", "Farmer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.firefighter, "Firefighter", "Firefighter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.florist, "Florist", "Florist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.gardener, "Gardener", "Gardener"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hairdresser, "Hairdresser", "Hairdresser"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.journalist, "Journalist", "Journalist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.judge, "Judge", "Judge"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lawyer, "Lawyer", "Lawyer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lecturer, "Lecturer", "Lecturer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.librarian, "Librarian", "Librarian"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.lifeguard, "Lifeguard", "Lifeguard"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.mechanics, "Mechanics", "Mechanics"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.model, ExifInterface.TAG_MODEL, ExifInterface.TAG_MODEL));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.nurse, "Nurse", "Nurse"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.optician, "Optician", "Optician"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.painter, "Painter", "Painter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pharmacist, "Pharmacist", "Pharmacist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.photographer, "Photographer", "Photographer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pilot, "Pilot", "Pilot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.plumber, "Plumber", "Plumber"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.receptionist, "Receptionist", "Receptionist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.scientist, "Scientist", "Scientist"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.soldier, "Soldier", "Soldier"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.student, "Student", "Student"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tailor, "Tailor", "Tailor"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.traffic_warden, "Traffic Warden", "Traffic Warden"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.veterinarian, "Veterinarian", "Veterinarian"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.waiter, "Waiter", "Waiter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.welder, "Welder", "Welder"));
        } else if (i2 == 17) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.board, "Board", "Board"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.book, "Book", "Book"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chair, "Chair", "Chair"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.compass, "Compass", "Compass"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.computer, "Computer", "Computer"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.desk, "Desk", "Desk"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.dictionary, "Dictionary", "Dictionary"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.eraser, "Eraser", "Eraser"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.globe, "Globe", "Globe"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.map, "Map", "Map"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.notebook, "Notebook", "Notebook"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pen, "Pen", "Pen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.pencil, "Pencil", "Pencil"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ruler, "Ruler", "Ruler"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.school_bag, "School bag", "School bag"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.teacher, "Teacher", "Teacher"));
        } else if (i2 == 18) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.chess, "Chess", "Chess"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.windsurfing, "Windsurfing", "Windsurfing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bowling, "Bowling", "Bowling"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.karate, "Karate", "Karate"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ice_skating, "Ice Skating", "Ice Skating"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.table_tennis, "Table Tennis", "Table Tennis"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.badminton, "Badminton", "Badminton"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.swimming, "Swimming", "Swimming"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.football, "Football", "Football"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.hockey, "Hockey", "Hockey"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.equestrian, "Equestrian", "Equestrian"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.cycling, "Cycling", "Cycling"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.diving, "Diving", "Diving"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.judo, "Judo", "Judo"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.golf, "Golf", "Golf"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.baseball, "Baseball", "Baseball"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.volleyball, "Volleyball", "Volleyball"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.surfing, "Surfing", "Surfing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.skateboarding, "Skateboarding", "Skateboarding"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.skiing, "Skiing", "Skiing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.archery, "Archery", "Archery"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.canoeing, "Canoeing", "Canoeing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.running, "Running", "Running"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.billiards, "Billiards", "Billiards"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fencing, "Fencing", "Fencing"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.tennis, "Tennis", "Tennis"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.basketball, "Basketball", "Basketball"));
        } else if (i2 == 19) {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ambulance, "Ambulance", "Ambulance"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bike, "Bike", "Bike"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.boat, "Boat", "Boat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.bus, "Bus", "Bus"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.car, "Car", "Car"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.container_truck, "Container Truck", "Container Truck"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.fire_truck, "Fire Truck", "Fire Truck"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.helicopter, "Helicopter", "Helicopter"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.motorbike, "Motorbike", "Motorbike"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.plane, "Plane", "Plane"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.police_car, "Police Car", "Police Car"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.ship, "Ship", "Ship"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.subway, "Subway", "Subway"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.train, "Train", "Train"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.truck, "Truck", "Truck"));
        } else {
            learningDataModelArrayList = new ArrayList();
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.a, "A for Apple", "Apple"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.b, "B for Ball", "Ball"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.c, "C for Cat", "Cat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.d, "D for Dog", "Dog"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.e, "E for Elephant", "Elephant"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.f, "F for Fish", "Fish"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.g, "G for Goat", "Goat"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.h, "H for Horse", "Horse"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.i, "I for Ice cream", "Ice cream"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.j, "J for Joker", "Joker"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.k, "K for Kite", "Kite"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.l, "L for Lion", "Lion"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.m, "M for Monkey", "Monkey"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.n, "N for Nest", "Nest"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.o, "O for Orange", "Orange"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.p, "P for Parrot", "Parrot"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.q, "Q for Queen", "Queen"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.r, "R for Rabbit", "Rabbit"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.s, "S for Sun", "Sun"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.t, "T for Train", "Train"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.u, "U for Umbrella", "Umbrella"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.v, "V for Violin", "Violin"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.w, "W for Watch", "Watch"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.x, "X for Xylophone", "Xylophone"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.y, "Y for Yak", "Yak"));
            learningDataModelArrayList.add(new LearningDataModel(R.drawable.z, "Z for Zebra", "Zebra"));
        }
    }

}
