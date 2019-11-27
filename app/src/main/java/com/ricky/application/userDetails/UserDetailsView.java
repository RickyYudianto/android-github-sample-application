package com.ricky.application.userDetails;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.userList.IUserListPresentation;
import com.ricky.application.userList.UserAdapter;
import com.ricky.application.userList.UserListPresenter;
import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsView extends AppCompatActivity implements IUserDetailsPresentation.view {

    @BindView(R.id.user_photo) CircleImageView userPhoto;
    @BindView(R.id.user_login) TextView username;
    @BindView(R.id.user_name) TextView name;
    @BindView(R.id.user_location) TextView location;
    @BindView(R.id.user_company) TextView company;
    @BindView(R.id.user_followers) TextView followers;
    @BindView(R.id.user_following) TextView following;

    private UserDetailsPresenter userDetailsPresenter = new UserDetailsPresenter();

    private boolean loadData = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_details_activity);

        ButterKnife.bind(this);
        userDetailsPresenter.setView(this);

        Intent intent = getIntent();
        userDetailsPresenter.loadUserDetails(intent.getStringExtra(Constant.LOGIN_KEY));
    }

    @Override
    public void onLoadUserDetails(User user) {
        Resources res = getResources();

        Picasso.get().load(user.getAvatarUrl()).centerInside()
                .resize(Constant.IMAGE_SIZE, Constant.IMAGE_SIZE)
                .into(userPhoto);

        username.setText(String.format(res.getString(R.string.user_login), user.getLogin()));
        name.setText(String.format(res.getString(R.string.user_name), user.getName() == null? Constant.EMPTY_STRING : user.getName()));
        location.setText(String.format(res.getString(R.string.user_location), user.getLocation() == null? Constant.EMPTY_STRING : user.getLocation()));
        company.setText(String.format(res.getString(R.string.user_company), user.getCompany() == null? Constant.EMPTY_STRING : user.getCompany()));
        followers.setText(String.format(res.getString(R.string.user_followers), String.valueOf(user.getFollowers())));
        following.setText(String.format(res.getString(R.string.user_following), String.valueOf(user.getFollowing())));

    }

    @Override
    public void onErrorLoadUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loadData = false;
    }

    @Override public void onDestroy() {
        super.onDestroy();
    }
}
