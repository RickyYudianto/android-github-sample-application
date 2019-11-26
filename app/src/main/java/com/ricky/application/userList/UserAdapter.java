package com.ricky.application.userList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.utils.webservice.models.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private User[] userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_photo) ImageView userPhoto;
        @BindView(R.id.user_id) TextView userId;
        @BindView(R.id.user_name) TextView username;


        public MyViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }


    public UserAdapter(User[] userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final User user = userList[position];

        holder.userId.setText(String.valueOf(user.getId()));
        holder.username.setText(user.getLogin());

        Picasso.get().load(user.getAvatarUrl()).centerInside().resize(300, 300).into(holder.userPhoto);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Context context = holder.itemView.getContext();
//                Intent intent = new Intent(context, UserDetails.class);
//                intent.putExtra("user", user);
//                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.length;
    }
}
