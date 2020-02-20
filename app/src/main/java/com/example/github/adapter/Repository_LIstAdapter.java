package com.example.github.adapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.domain.LocalRepository;

import java.util.List;

public class Repository_LIstAdapter extends RecyclerView.Adapter<Repository_LIstAdapter.ViewHolder> {
    private List<LocalRepository> mList;

    public int getmPosition() {
        return mPosition;
    }

    public LocalRepository getLocalRepository(){
        return mList.get(mPosition);
    }
    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    private int mPosition;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item,parent,false);
        return new Repository_LIstAdapter.ViewHolder(view);
    }
    public Repository_LIstAdapter(List<LocalRepository> list){
        this.mList=list;
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        LocalRepository localRepository=mList.get(position);
        holder.repository_name.setText(localRepository.getRepository_name());
        String mid=localRepository.getDescription();
        if(mid==null)holder.description.setText("No Description");
        else holder.description.setText(localRepository.getDescription());
        holder.fork_count.setText(String.valueOf(localRepository.getFork()));
        holder.star_count.setText(String.valueOf(localRepository.getStar()));
        holder.name.setText(localRepository.getUser_name());
        holder.language_name.setText(localRepository.getLanguage());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPosition=holder.getLayoutPosition();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updata() {
        mList.remove(mPosition);
        notifyItemRemoved(mPosition);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView repository_name;
        TextView description;
        TextView fork_count;
        TextView star_count;
        TextView name ;
        TextView language_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repository_name = itemView.findViewById(R.id.repository_name);
            description = itemView.findViewById(R.id.description);
            fork_count = itemView.findViewById(R.id.fork_count);
            star_count = itemView.findViewById(R.id.star_count);
            name=itemView.findViewById(R.id.Rep_name);
            language_name=itemView.findViewById(R.id.language_name);
            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(0,0,0,"删除");
                    menu.add(0,1,0,"打开仓库主页");
                }
            });
        }


    }
}
