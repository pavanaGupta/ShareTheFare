package com.example.ashwanigupta.sharethefare.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendHolder> {

    ArrayList<Friend> friendList=new ArrayList<>();
    Context c;

    public FriendListAdapter(ArrayList<Friend> friendList, Context c) {
        this.friendList = friendList;
        this.c = c;
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= li.inflate(R.layout.list_item_friend, parent, false);

        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {

        holder.tvName.setText(friendList.get(position).getName());
        holder.tvTotal.setText(String.valueOf(friendList.get(position).getTotal()));
        //holder.img.setImageResource(R.drawable.and);
        try {
            if(friendList.get(position).getImage()!=null)
            holder.img.setImageBitmap(BitmapFactory
                    .decodeStream(c.getContentResolver().openInputStream(Uri.parse(friendList.get(position).getImage()))));
            else
                holder.img.setImageResource(R.drawable.def);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public void updateFriends(ArrayList<Friend> friends)
    {
        friendList=friends;
        notifyDataSetChanged();

    }

    static class FriendHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView tvName, tvTotal;

        public FriendHolder(View itemView) {
            super(itemView);

            tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvTotal=(TextView) itemView.findViewById(R.id.tvTotal);
            img=(ImageView) itemView.findViewById(R.id.img);
        }
    }
}
