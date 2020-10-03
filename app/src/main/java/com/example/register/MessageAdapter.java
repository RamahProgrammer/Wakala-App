package com.example.register;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    Context context;
    ArrayList<messageModel> messageModelArrayList;

    public MessageAdapter(Context context, ArrayList<messageModel> messageModelArrayList) {
        this.context = context;
        this.messageModelArrayList = messageModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(messageModelArrayList.get(position).getFrom());
        holder.description.setText(messageModelArrayList.get(position).getSms());

    //    holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, Read_News.class);
//                intent.putExtra("news_id",news.get(position).getId().toString());
//                intent.putExtra("news_id",news.get(position).getId().toString());
//                intent.putExtra("news_id",news.get(position).getId().toString());
//                context.startActivity(intent);
//            }
     //   });
    }

    @Override
    public int getItemCount() {
        return messageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, post_at;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
