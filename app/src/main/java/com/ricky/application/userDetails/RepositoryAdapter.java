package com.ricky.application.userDetails;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ricky.application.R;
import com.ricky.application.utils.Utility;
import com.ricky.application.utils.webservice.models.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.MyViewHolder> {

    private Context context;
    private List<Repository> repositoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name) TextView repoName;
        @BindView(R.id.repo_description) TextView repoDescription;
        @BindView(R.id.repo_star) TextView repoStar;
        @BindView(R.id.repo_watcher) TextView repoWatcher;
        @BindView(R.id.repo_created) TextView repoCreated;


        public MyViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }

    public RepositoryAdapter(Context context, List<Repository> repositoryList) {
        this.context = context;
        this.repositoryList = repositoryList;
    }

    @Override
    public RepositoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_repos_layout, parent, false);

        return new RepositoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RepositoryAdapter.MyViewHolder holder, final int position) {
        Repository repository = repositoryList.get(position);
        Resources res = context.getResources();

        holder.repoName.setText(repository.getName());
        holder.repoDescription.setText(repository.getDescription());
        holder.repoStar.setText(String.format(res.getString(R.string.repo_star), repository.getStars()));
        holder.repoWatcher.setText(String.format(res.getString(R.string.repo_watcher), repository.getWatchers()));
        holder.repoCreated.setText(Utility.convertDate(repository.getCreatedDate()));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }
}
