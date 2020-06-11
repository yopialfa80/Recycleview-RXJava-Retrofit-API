package com.github.recycleviewrxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<User> contactList;
    private ArrayList<User> contactListFiltered;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userStats;
        public ImageView userPicture;

        public MyViewHolder(View view) {
            super(view);
            userPicture = view.findViewById(R.id.userPicture);
            userName = view.findViewById(R.id.userName);
            userStats = view.findViewById(R.id.userStats);
        }
    }

    public RecycleAdapter(Context context, ArrayList<User> contactList) {
        this.context = context;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new RecycleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecycleAdapter.MyViewHolder holder, final int position) {
        final User contact = contactListFiltered.get(position);
        Glide.with(context)
                .load(contact.getPicture())
                .into(holder.userPicture);

        holder.userName.setText(contact.getName());
        holder.userStats.setText(contact.getStats());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }
}
