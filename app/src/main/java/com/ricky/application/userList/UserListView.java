package com.ricky.application.userList;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.utils.Constant;
import com.ricky.application.utils.webservice.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListView extends Fragment implements IUserListPresentation.view {

    @BindView(R.id.user_list_recycle_view) RecyclerView recyclerView;
    private UserAdapter adapter;
    private UserListPresenter userListPresenter = new UserListPresenter();

    private boolean loadData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View userListView =
                inflater.inflate(R.layout.user_list_container, container, false);
        ButterKnife.bind(this, userListView);
        userListPresenter.setView(this);

        initRecyclerView();
        userListPresenter.loadUserList(-1);

        return userListView;
    }

    @Override
    public void onLoadUserList(List<User> userList) {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            adapter = new UserAdapter(getActivity(),userList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            loadData = false;
        }, Constant.LOAD_DATA_DELAY);
    }

    @Override
    public void onErrorLoadUserList(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        loadData = false;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        userListPresenter.onDestroy();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (!loadData && mLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    userListPresenter.loadUserList(adapter.getLastUser().getId());
                    loadData = true;
                }
            }
        });
    }

    public int getLastUserId() {
        return adapter.getLastUser().getId();
    }
}
