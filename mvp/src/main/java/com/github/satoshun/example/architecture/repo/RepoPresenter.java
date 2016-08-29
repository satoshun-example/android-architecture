package com.github.satoshun.example.architecture.repo;

import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.repo.Repo;

class RepoPresenter implements RepoContract.Presenter {

  @NonNull private final RepoContract.View view;
  @NonNull private final Repo repo;

  RepoPresenter(@NonNull RepoContract.View view, @NonNull Repo repo) {
    this.view = view;
    this.repo = repo;
  }

  @Override public void subscribe() {
    view.renderRepo(repo);
  }

  @Override public void unsubscribe() {
    // no-op
  }
}
