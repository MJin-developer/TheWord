package kor.co.mu.jin.theword;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter2Main extends RecyclerView.Adapter {

    ArrayList<CustomList> items;
    Context context;

    public RecyclerAdapter2Main(ArrayList<CustomList> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycleritem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        CustomList customList = items.get(position);
        Glide.with(context).load(customList.imgUrl).into(viewHolder.imageView);
        viewHolder.textView.setText(customList.title);
    }

    @Override
    public int getItemCount() {
        if(items.size() > 4){
            return 4;
        }else {
            return  items.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomList customList = items.get(getLayoutPosition());
                    Intent intent = new Intent(context, popularItemClickActivity.class);
                    intent.putExtra("TITLE", customList.title);
                    context.startActivity(intent);
                }
            });
        }
    }
}
