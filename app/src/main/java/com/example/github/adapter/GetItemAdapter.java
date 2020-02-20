package com.example.github.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.github.CircleImageView;
import com.example.github.R;
import com.example.github.UserActivity;
import com.example.github.domain.GetItem;

import java.util.ArrayList;
import java.util.List;

public class GetItemAdapter extends RecyclerView.Adapter<GetItemAdapter.ViewHolder> {
    private List<GetItem.ItemsBean> mData=new ArrayList<>();
    private Context mcontext;
    @NonNull
    @Override
    public GetItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        mcontext=parent.getContext();
        return new GetItemAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GetItemAdapter.ViewHolder holder, final int position) {
        View itemView=holder.itemView;
        TextView textView=itemView.findViewById(R.id.textView);
        GetItem.ItemsBean itemsBean=mData.get(position);
        textView.setText(itemsBean.getLogin());
        CircleImageView circleImageView= itemView.findViewById(R.id.circleImageView);
        Glide.with(itemView.getContext()).load(mData.get(position).getAvatar_url()).into(circleImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetItem.ItemsBean getItem=mData.get(position);
                String name=getItem.getLogin();
                String user_image=getItem.getAvatar_url();
                Intent starter =new Intent(mcontext,UserActivity.class);
                starter.putExtra("User_name",name);
                starter.putExtra("User_Iamge",user_image);
                mcontext.startActivity(starter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(GetItem getItem) {
        mData.clear();
        mData.addAll(getItem.getItems());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
