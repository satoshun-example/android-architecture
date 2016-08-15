package com.github.satoshun_example.architecture.main;

import android.support.annotation.NonNull;

import com.github.satoshun_example.architecture.data.user.User;
import com.github.satoshun_example.architecture.data.user.UserDataSource;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

class MainPresenter implements MainContract.Presenter {

  @NonNull private final MainContract.View view;

  @NonNull private final UserDataSource dataSource;

  @NonNull private final CompositeSubscription subscriptions;

  MainPresenter(@NonNull MainContract.View view, @NonNull UserDataSource dataSource) {
    this.view = view;
    this.dataSource = dataSource;
    this.subscriptions = new CompositeSubscription();
  }

  @Override public void subscribe() {
    subscriptions.clear();
    subscriptions.add(dataSource.getUser(100)
            .subscribe(new Subscriber<User>() {
              @Override
              public void onCompleted() {
              }

              @Override
              public void onError(Throwable e) {
                view.showUserError();
              }

              @Override
              public void onNext(User user) {
                view.showUser(user);
              }
            }));
  }

  @Override public void unsubscribe() {
    subscriptions.clear();
  }
}
