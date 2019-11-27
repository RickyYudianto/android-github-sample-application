package com.ricky.application.userDetails;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ricky.application.R;
import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.Repository;
import com.ricky.application.utils.webservice.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsView extends AppCompatActivity implements IUserDetailsPresentation.view, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.progress_relative_layout) RelativeLayout progressRelativeLayout;
    @BindView(R.id.details_refresh_layout) SwipeRefreshLayout detailsRefreshLayout;
    @BindView(R.id.user_repo_recycle_view) RecyclerView recyclerView;
    @BindView(R.id.user_photo) CircleImageView userPhoto;
    @BindView(R.id.user_login) TextView username;
    @BindView(R.id.user_name) TextView name;
    @BindView(R.id.user_location) TextView location;
    @BindView(R.id.user_company) TextView company;
    @BindView(R.id.user_followers) TextView followers;
    @BindView(R.id.user_following) TextView following;

    private LinearLayoutManager mLayoutManager;
    private RepositoryAdapter adapter;
    private UserDetailsPresenter userDetailsPresenter = new UserDetailsPresenter();
    private Intent intent;

    private boolean loadData = true;
    private boolean loadRepo = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_details_activity);

        ButterKnife.bind(this);
        userDetailsPresenter.setView(this);

        setVisibilityLayout();
        initRecyclerView();
        detailsRefreshLayout.setOnRefreshListener(this);

        intent = getIntent();
        userDetailsPresenter.loadUserDetails(intent.getStringExtra(Constant.LOGIN_KEY));
    }

    @Override
    public void onLoadUserDetails(User user) {
        loadData = false;
        Resources res = getResources();

        Picasso.get().load(user.getAvatarUrl()).centerInside()
                .resize(Constant.IMAGE_SIZE, Constant.IMAGE_SIZE)
                .into(userPhoto);

        username.setText(String.format(res.getString(R.string.user_login), user.getLogin()));
        name.setText(String.format(res.getString(R.string.user_name), user.getName() == null? Constant.EMPTY_STRING : user.getName()));
        location.setText(String.format(res.getString(R.string.user_location), user.getLocation() == null? Constant.EMPTY_STRING : user.getLocation()));
        company.setText(String.format(res.getString(R.string.user_company), user.getCompany() == null? Constant.EMPTY_STRING : user.getCompany()));
        followers.setText(String.format(res.getString(R.string.user_followers), user.getFollowers()));
        following.setText(String.format(res.getString(R.string.user_following), user.getFollowing()));
    }

    @Override
    public void onLoadUserRepos(List<Repository> repositoryList) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            adapter = new RepositoryAdapter(this, repositoryList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            loadRepo = false;
            setVisibilityLayout();
        }, Constant.LOAD_DATA_DELAY);
    }

    @Override
    public void onErrorLoadUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loadData = false;
    }

    @Override
    public void onErrorLoadRepositoryList(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loadRepo = false;
    }

    @Override public void onDestroy() {
        super.onDestroy();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
    }

    @Override
    public void onRefresh() {
        userDetailsPresenter.loadUserDetails(intent.getStringExtra(Constant.LOGIN_KEY));
        userDetailsPresenter.loadUserRepos(intent.getStringExtra(Constant.LOGIN_KEY));
        loadData = true;
        loadRepo = true;

        setVisibilityLayout();
        detailsRefreshLayout.setRefreshing(false);
    }

    private void setVisibilityLayout() {
        if (loadData || loadRepo) {
            progressRelativeLayout.setVisibility(View.VISIBLE);
            detailsRefreshLayout.setVisibility(View.INVISIBLE);
        } else {
            progressRelativeLayout.setVisibility(View.GONE);
            detailsRefreshLayout.setVisibility(View.VISIBLE);
        }
    }
}
