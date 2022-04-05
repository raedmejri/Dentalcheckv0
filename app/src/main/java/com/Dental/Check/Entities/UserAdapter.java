package com.Dental.Check.Entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Dental.Check.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterVh> {
    private List<User> users;
    private Context context;
    private ClickedItem clickedItem;

    public UserAdapter(ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }
    public void setData(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserAdapter.UserAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVh holder, int position) {
        User userResponce= users.get(position);

        String username = userResponce.getName();
        String prefix;
        if(userResponce.isIs_active())
        {
            prefix="A";

        }
        else {
            prefix="D";
        }
        holder.prefix.setText(prefix);
        holder.username.setText(username);
        holder.imageMore.setOnClickListener(v ->
                clickedItem.clickedUser(userResponce));

    }
    public  interface ClickedItem{
        public void clickedUser(User user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserAdapterVh extends RecyclerView.ViewHolder {
        TextView username;
        TextView prefix;
        ImageView imageMore;
        public UserAdapterVh(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            prefix = itemView.findViewById(R.id.t1);
            imageMore = itemView.findViewById(R.id.imageMore);

        }
    }
}
