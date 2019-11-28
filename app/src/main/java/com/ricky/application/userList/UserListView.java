package com.ricky.application.userList;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListView extends AppCompatActivity implements IUserListPresentation.view {
    @BindView(R.id.user_list_error) TextView errorText;
    @BindView(R.id.user_list_recycle_view) RecyclerView recyclerView;

    private LinearLayoutManager mLayoutManager;
    private UserAdapter adapter;
    private UserListPresenter userListPresenter = new UserListPresenter();

    private boolean loadData = true;
    private boolean allDataIsLoaded = false;
    private int lastPositionRecyclerView = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_list_activity);

        ButterKnife.bind(this);
        userListPresenter.setView(this);

        initRecyclerView();
        userListPresenter.loadUserList(-1);
    }

    @Override
    public void onLoadUserList(List<User> userList) {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            adapter = new UserAdapter(this, userList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            loadData = false;

            errorText.setVisibility(View.GONE);
            mLayoutManager.scrollToPosition(lastPositionRecyclerView);
        }, Constant.LOAD_DATA_DELAY);
    }

    @Override
    public void allUsersLoaded() {
        allDataIsLoaded = true;
    }

    @Override
    public void onErrorLoadUserList(List<User> userList, String message) {
        if (userList.size() > 0) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            errorText.setText(message);
            errorText.setVisibility(View.VISIBLE);
        }
        loadData = false;
    }

    @Override public void onDestroy() {
        super.onDestroy();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (!allDataIsLoaded && !loadData && mLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    lastPositionRecyclerView = mLayoutManager.findFirstVisibleItemPosition();
                    userListPresenter.loadUserList(adapter.getLastUser().getId());
                    loadData = true;
                }
            }
        });
    }
}
