package com.github.satoshun.example.architecture.users;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.satoshun.example.architecture.R;
import com.github.satoshun.example.architecture.data.repo.Repo;
import com.github.satoshun.example.architecture.databinding.RepoItemBinding;
import com.github.satoshun.example.architecture.databinding.ReposFragBinding;
import com.github.satoshun.example.architecture.repo.RepoActivity;

import java.util.Collections;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ReposFragment extends Fragment implements ReposContract.View {

  static ReposFragment newInstance() {
    return new ReposFragment();
  }

  private ReposContract.Presenter presenter;
  private ReposFragBinding binding;
  private ReposAdapter adapter;

  public ReposFragment() {
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater,
                                     @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.repos_frag, container, false);
    return binding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new ReposAdapter(getContext());
    binding.content.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.content.setAdapter(adapter);
  }

  @Override public void onResume() {
    super.onResume();

    presenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();

    presenter.unsubscribe();
  }

  @Override public void renderRepos(List<Repo> repos) {
    adapter.setItems(repos);
    adapter.notifyDataSetChanged();
  }

  void setPresenter(@NonNull ReposContract.Presenter presenter) {
    this.presenter = presenter;
  }


  private static class ReposAdapter extends RecyclerView.Adapter<ViewHolder> {

    @NonNull private final Context context;

    @NonNull private List<Repo> repos = Collections.emptyList();

    ReposAdapter(@NonNull Context context) {
      this.context = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      RepoItemBinding binding = DataBindingUtil.inflate((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE),
              R.layout.repo_item,
              parent,
              false);
      return new ViewHolder(binding);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      final Repo repo = getItem(position);
      holder.binding.setModel(repo);
      holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Intent intent = RepoActivity.getCallingIntent(context, repo);
          context.startActivity(intent);
        }
      });
    }

    @Override public int getItemCount() {
      return repos.size();
    }

    void setItems(List<Repo> repos) {
      this.repos = repos;
    }

    private Repo getItem(int index) {
      return repos.get(index);
    }
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {

    private final RepoItemBinding binding;

    ViewHolder(RepoItemBinding binding) {
      super(binding.getRoot());

      this.binding = binding;
    }
  }
}