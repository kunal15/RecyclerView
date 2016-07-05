package com.khatrigmail.kunal15.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 03-07-2016.
 */
public class Row extends RecyclerView.Adapter<Row.MessageModelHolder> {

    ArrayList<MessageModel> messageModelHolder = new ArrayList<MessageModel>();
    Context c;

    public Row(ArrayList<MessageModel> messageModelHolder, Context c) {
        this.messageModelHolder = messageModelHolder;
        this.c = c;
    }

    @Override
    public Row.MessageModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        MessageModelHolder messageModelHolder = new MessageModelHolder(view);
        return messageModelHolder;
    }

    @Override
    public void onBindViewHolder(Row.MessageModelHolder holder, final int position) {
        holder.tvName.setText(messageModelHolder.get(position).getPersonName());
        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + messageModelHolder.get(position).getMobile()));
                callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(callIntent);
            }
        });
        holder.ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,InfoActivity.class);
                i.putExtra("Name",messageModelHolder.get(position).getPersonName());
                i.putExtra("Number",messageModelHolder.get(position).getMobile());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageModelHolder.size();
    }
    public class MessageModelHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivInfo,ivCall;
        public MessageModelHolder(View itemView) {
            super(itemView);
            ivInfo = (ImageView) itemView.findViewById(R.id.ivInfo);
            ivCall = (ImageView) itemView.findViewById(R.id.ivCall);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
