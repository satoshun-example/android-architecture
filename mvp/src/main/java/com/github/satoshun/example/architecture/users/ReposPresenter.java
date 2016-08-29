package com.github.satoshun.example.architecture.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.satoshun.example.architecture.data.repo.Repo;
import com.github.satoshun.example.architecture.data.repo.RepoDataSource;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class ReposPresenter implements ReposContract.Presenter, Observer<List<Repo>> {

  private static final String TAG = ReposPresenter.class.getSimpleName();
  private static final String DEFAULT_USER = "satoshun";

  @NonNull private final ReposContract.View view;
  @NonNull private final RepoDataSource dataSource;
  @NonNull private final CompositeSubscription subscriptions;

  ReposPresenter(@NonNull ReposContract.View view, @NonNull RepoDataSource dataSource) {
    this.view = view;
    this.dataSource = dataSource;
    this.subscriptions = new CompositeSubscription();
  }

  @Override public void subscribe() {
    fetchData();
  }

  @Override public void unsubscribe() {
    subscriptions.clear();
  }

  @Override public void refresh() {
    subscriptions.clear();
    fetchData();
  }

  private void fetchData() {
    view.showProgressIndicator();
    subscriptions.add(dataSource.getRepositories(DEFAULT_USER)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this));
  }

  @Override public void onCompleted() {
  }

  @Override public void onError(Throwable e) {
    Log.e(TAG, String.valueOf(e));
    view.hideProgressIndicator();

    // TODO: send error message to View
  }

  @Override public void onNext(List<Repo> repos) {
    view.renderRepos(repos);
    view.hideProgressIndicator();
  }
}
