package com.ricky.application.userList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.userDetails.UserDetailsView;
import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_photo) CircleImageView userPhoto;
        @BindView(R.id.user_id) TextView userId;
        @BindView(R.id.user_login) TextView username;


        public MyViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;

        Collections.sort(userList, (o1, o2) -> o1.getId() - o2.getId());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final User user = userList.get(position);
        Resources res = context.getResources();

        holder.userId.setText(String.format(res.getString(R.string.user_id), user.getId()));
        holder.username.setText(String.format(res.getString(R.string.user_login), user.getLogin()));

        Picasso.get().load(user.getAvatarUrl()).centerInside()
                .resize(Constant.IMAGE_SIZE, Constant.IMAGE_SIZE)
                .into(holder.userPhoto);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, UserDetailsView.class);
                intent.putExtra(Constant.LOGIN_KEY, user.getLogin());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public User getLastUser() {
        return userList.get(userList.size() - 1);
    }
}
