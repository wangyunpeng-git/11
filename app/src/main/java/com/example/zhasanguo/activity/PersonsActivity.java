package com.example.zhasanguo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhasanguo.MainActivity;
import com.example.zhasanguo.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PersonsActivity extends AppCompatActivity {
    public static  final String PERSON_IMAGE_ID = "person_image_id";
    ImageView imageButton;
    FrameLayout mFlBack;
    FrameLayout mFlFront;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack;

    TextView person_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        final Intent intent = getIntent();
        int personId = intent.getIntExtra(PERSON_IMAGE_ID, 0);
        String imageName = intent.getStringExtra("image_name");
        int nImageID = getDrawableId(this, imageName);
        ImageView personImageView = findViewById(R.id.person_image_view);
        Glide.with(this).load(personId).into(personImageView);
        //Glide.with(this).load(nImageID).into(personImageView);
        imageButton = findViewById(R.id.main_bt);
        mFlFront = findViewById(R.id.main_fl_front);
        mFlBack = findViewById(R.id.main_fl_back);
        person_context = findViewById(R.id.person_context);


        //String[] persons = getResources().getStringArray(R.array.persons);
        //Log.i("@_@", "test");
        setAnimators();
        setCameraDistance();



    }
    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.anim_in);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.anim_out);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageButton.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageButton.setClickable(true);
            }
        });
    }
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlFront.setCameraDistance(scale);
        mFlBack.setCameraDistance(scale);
    }

    public void imageBT(View view) {

        if (!mIsShowBack) {
           // imageButton.setImageResource(R.drawable.btn_renwujieshao);
            imageButton.setImageResource(R.drawable.btn_return_n);
            mRightOutSet.setTarget(mFlFront);
            mLeftInSet.setTarget(mFlBack);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
        } else { // 背面朝
           // imageButton.setImageResource(R.drawable.btn_return_n);
            imageButton.setImageResource(R.drawable.btn_renwujieshao);

            mRightOutSet.setTarget(mFlBack);
            mLeftInSet.setTarget(mFlFront);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }
     public int getDrawableId(Context paramContext, String paramString) {
         return paramContext.getResources().getIdentifier(paramString,
                 "drawable", paramContext.getPackageName());
     }

}
