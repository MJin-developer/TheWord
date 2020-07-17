package kor.co.mu.jin.theword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class subwordAdapter extends RecyclerView.Adapter {

    ArrayList<subwordList> lists;
    Context context;

    public subwordAdapter(ArrayList<subwordList> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.subworditem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        subwordList subwordList = lists.get(position);
        Glide.with(context).load(subwordList.imgurl).into(viewHolder.circleImageView);
        viewHolder.name.setText(subwordList.name);
        viewHolder.content.setText(subwordList.content);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView name;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.subword_img);
            name = itemView.findViewById(R.id.subword_name);
            content = itemView.findViewById(R.id.subword_content);

        }
    }
}
