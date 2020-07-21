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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter5 extends RecyclerView.Adapter {

    ArrayList<MYStoryItemList> items;
    Context context;

    public RecyclerAdapter5(ArrayList<MYStoryItemList> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycleritem2, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        MYStoryItemList myStoryItemList = items.get(position);
        Glide.with(context).load(myStoryItemList.profileImg).into(viewHolder.profileImg);
        viewHolder.title.setText(myStoryItemList.title);
        viewHolder.name.setText(myStoryItemList.name);
        String num = myStoryItemList.showNum + "";
        viewHolder.showNum.setText(num);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        CircleImageView profileImg;
        TextView name;
        TextView showNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.my_title);
            profileImg = itemView.findViewById(R.id.my_profileimg);
            name = itemView.findViewById(R.id.my_name);
            showNum = itemView.findViewById(R.id.my_num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MYStoryItemList myStoryItemList = items.get(getLayoutPosition());
                    Intent intent = new Intent(context, MYStoryItemClickActivity.class);
                    intent.putExtra("TITLE", myStoryItemList.title);
                    context.startActivity(intent);

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference rootref = firebaseDatabase.getReference();
                    final DatabaseReference dataref = rootref.child("A MYSTORY");
                    dataref.orderByChild("title").equalTo(myStoryItemList.title).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                MYStoryItemList myStoryItemList1 = snapshot.getValue(MYStoryItemList.class);
                                int a = myStoryItemList1.showNum;
                                a++;
                                String s = snapshot.getKey();
                                Map<String, Object> map = new HashMap<>();
                                map.put(s + "/showNum", a);
                                dataref.updateChildren(map);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });

        }
    }
}
