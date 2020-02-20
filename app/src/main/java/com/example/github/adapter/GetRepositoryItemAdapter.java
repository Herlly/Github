package com.example.github.adapter;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.github.CircleImageView;
import com.example.github.R;
import com.example.github.UserActivity;
import com.example.github.domain.GetRepositoryItem;
import java.util.List;
public class GetRepositoryItemAdapter extends RecyclerView.Adapter<GetRepositoryItemAdapter.ViewHolder> {
   private List<GetRepositoryItem> mGetRepositoryItems;
   private String json,user_name;
   private Context mcontext;
   private CircleImageView circleImageView;
   private int mPosition;
    @NonNull
    @Override
    public GetRepositoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View RepositoryItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item,parent,false);
            return new GetRepositoryItemAdapter.ViewHolder(RepositoryItem);
    }
    public GetRepositoryItem getRepositoryItem(){
        return mGetRepositoryItems.get(mPosition);
    }
    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }
public GetRepositoryItemAdapter( Context context,List<GetRepositoryItem> list,String user_image){
        this.mcontext=context;
        this.mGetRepositoryItems=list;
        this.user_name=user_image;
}
    @Override
    public void onBindViewHolder(@NonNull final GetRepositoryItemAdapter.ViewHolder holder, int position) {
        GetRepositoryItem getRepositoryItem=mGetRepositoryItems.get(position);
        holder.repository_name.setText(getRepositoryItem.getName());
        String mid=getRepositoryItem.getDescription();
        if(mid==null)holder.description.setText("No Description");
        else holder.description.setText(getRepositoryItem.getDescription());
        holder.fork_count.setText(String.valueOf(getRepositoryItem.getForks_count()));
        holder.star_count.setText(String.valueOf(getRepositoryItem.getStargazers_count()));
        holder.name.setText(user_name);
        holder.language_name.setText(getRepositoryItem.getLanguage());
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
        return mGetRepositoryItems.size();
    }

    @Override
   public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener{

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
            circleImageView= ((UserActivity)mcontext).findViewById(R.id.head_image);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0,0,1,"收藏");
            menu.add(0,1,0,"打开仓库主页");
        }
    }
}
