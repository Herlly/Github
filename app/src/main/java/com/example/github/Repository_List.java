package com.example.github;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.github.adapter.Repository_LIstAdapter;
import com.example.github.domain.LocalRepository;
import org.litepal.LitePal;
import java.util.List;
public class Repository_List extends AppCompatActivity {

    private Repository_LIstAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository__list);
        RecyclerView recyclerView=this.findViewById(R.id.Rep_List);
        List<LocalRepository> list= LitePal.findAll(LocalRepository.class);
        adapter = new Repository_LIstAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Toolbar toolbar=findViewById(R.id.Rep_List_toolbar);
        toolbar.setTitle("收藏");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        LocalRepository localRepository=adapter.getLocalRepository();
       switch (item.getItemId()){
           case 0:
               LitePal.deleteAll(LocalRepository.class,"repository_name=?",localRepository.getRepository_name());
               adapter.updata();
               break;
           case 1:
               Intent intent=new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(localRepository.getHtml_url()));
               startActivity(intent);
               break;
       }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
