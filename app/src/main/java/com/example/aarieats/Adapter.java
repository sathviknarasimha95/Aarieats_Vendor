package com.example.aarieats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aarieats.models.MainMenuItems;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<MainMenuItems> mData;

    private Context mContext;

    private ItemOnClickListener mListener;

    public Adapter(List<MainMenuItems> mData, Context mContext, ItemOnClickListener itemOnClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(mData.get(position).getMenuName());
        holder.thumbnail.setImageResource(mData.get(position).getThumbnail());

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position,mData.get(position),holder.thumbnail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView thumbnail;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.version_title_id);
            thumbnail = itemView.findViewById(R.id.version_img_id);
        }
    }

}
