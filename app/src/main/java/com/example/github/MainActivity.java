package com.example.github;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.github.adapter.GetItemAdapter;
import com.example.github.domain.GetItem;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import org.litepal.LitePal;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private SearchView mSearchView;
    private GetItemAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.list3);
        }
        LitePal.initialize(this);
        initView();
        final NavigationView na_view=(NavigationView)findViewById(R.id.navigation_view);
        na_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                View view=na_view.getHeaderView(0).findViewById(R.id.star);
                if(item.getItemId()==R.id.star){
                    Intent intent=new Intent(MainActivity.this,Repository_List.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    private void initView() {
        RecyclerView recyclerView=this.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        madapter = new GetItemAdapter();
        recyclerView.setAdapter(madapter);
    }

    public void loadJson( final String s){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("https://api.github.com/search/users?q="+s);
                    HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(100000);
                    connection.setReadTimeout(100000);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String json=reader.readLine();
                    Gson gson=new Gson();
                    GetItem getItem=gson.fromJson(json,GetItem.class);
                    updateUI(getItem,getItem.getTotal_count());
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void updateUI(final GetItem getItem,final int count) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"共找到"+count+"个相关用户",Toast.LENGTH_LONG).show();
                madapter.setData(getItem);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item,menu);
        MenuItem search_Item=menu.findItem(R.id.search_item);
        mSearchView =(SearchView) MenuItemCompat.getActionView(search_Item);
        mSearchView.setMaxWidth(1000);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("请输入关键词");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_LONG).show();
                }else{
                    loadJson(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mdrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;

    }

}
