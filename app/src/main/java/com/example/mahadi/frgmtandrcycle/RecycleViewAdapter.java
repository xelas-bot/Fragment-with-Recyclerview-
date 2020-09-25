package com.example.mahadi.frgmtandrcycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mahadi on 3/11/2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    Context context;
    List<Card> cardList;

    public RecycleViewAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(cardList.get(position).getName());
        holder.phone_num.setText(cardList.get(position).getPhn());
        //holder.imageView.setImageResource(contactList.get(position).getPhoto());
        holder.endTime.setText(cardList.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone_num;
        TextView endTime;
       // ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_contact);
            phone_num = (TextView) itemView.findViewById(R.id.ph_number);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            //imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
