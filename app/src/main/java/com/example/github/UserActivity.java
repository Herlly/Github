package com.example.github;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.github.domain.GetUserItem;
import com.example.github.fragment.InfoFragment;
import com.example.github.fragment.MyFragmentPagerAdapter;
import com.example.github.fragment.RepositoryFragment;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UserActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    private RadioGroup radioGroup;
    private RadioButton repository;
    private RadioButton Info;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    private String user_name,user_image,user_join_time;
    private String string_json;
    private String avatar_url,html_url,bio,created_at;
    private CircleImageView User_Image;
    private TextView user_joinTime;
    private TextView mUser_name;
    private GetUserItem mGetUserItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent=getIntent();
        user_name = intent.getStringExtra("User_name");
        user_image=intent.getStringExtra("User_Iamge");
        Toolbar toolbar=findViewById(R.id.user_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        loadjson(user_name);

    }
    private void bindView(){
        myFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager()) ;
        Fragment fragment1 = InfoFragment.newInstance(mGetUserItem.getFollowers(),mGetUserItem.getFollowing(),mGetUserItem.getBio());
        Fragment fragment2=RepositoryFragment.newInstance(string_json,user_name);
        myFragmentPagerAdapter.addFrag(fragment1);
        myFragmentPagerAdapter.addFrag(fragment2);
        CircleImageView circleImageView=(CircleImageView)getSupportFragmentManager().findFragmentById(R.id.fragment).getView().findViewById(R.id.head_image);
        user_joinTime=(TextView)getSupportFragmentManager().findFragmentById(R.id.fragment).getView().findViewById(R.id.join_time);
        mUser_name = (TextView)getSupportFragmentManager().findFragmentById(R.id.fragment).getView().findViewById(R.id.user_name);
        Glide.with(this).load(user_image).into(circleImageView);
        mUser_name.setText(user_name);
        radioGroup = (RadioGroup)findViewById(R.id.radio_tab);
        radioGroup.setOnCheckedChangeListener(this);
        Info = (RadioButton)findViewById(R.id.info);
        repository = (RadioButton)findViewById(R.id.repository);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        Info.setChecked(true);
        viewPager.addOnPageChangeListener(this);
    }
    public void loadjson(final String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("https://api.github.com/users/"+name);
                    HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(100000);
                    connection.setReadTimeout(100000);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String json=reader.readLine();
                    Gson gson=new Gson();
                    mGetUserItem = gson.fromJson(json,GetUserItem.class);
                    connection.disconnect();
                    URL new_url=new URL("https://api.github.com/users/"+name+"/repos");
                    HttpsURLConnection new_connetion=(HttpsURLConnection)  new_url.openConnection();
                    new_connetion.setRequestMethod("GET");
                    new_connetion.setConnectTimeout(100000);
                    new_connetion.setReadTimeout(100000);
                    InputStream new_in=new_connetion.getInputStream();
                    BufferedReader new_reader=new BufferedReader(new InputStreamReader(new_in));
                    //将请求回来的string直接传入fragment
                    string_json=new_reader.readLine();
                    updateUI(mGetUserItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void updateUI(final GetUserItem getUserItem){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bindView();
               user_join_time="Joined at "+handleString(getUserItem.getCreated_at());
                user_joinTime.setText(user_join_time);
            }
        });
    }
public String handleString(String s){
    s=s.substring(0,10);
    return s;
}
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.info:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.repository:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==2){
            switch ((viewPager.getCurrentItem())){
                case PAGE_ONE:
                    Info.setChecked(true);
                    break;
                case PAGE_TWO:
                    repository.setChecked(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
