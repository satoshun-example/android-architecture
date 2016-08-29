package com.github.satoshun.example.architecture.user;

import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.user.User3;
import com.github.satoshun.example.architecture.data.user.UserDataSource3;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

class UserPresenter implements UserContract.Presenter {

  @NonNull private final UserContract.View view;
  @NonNull private final UserDataSource3 dataSource;
  @NonNull private final CompositeSubscription subscriptions;

  UserPresenter(@NonNull UserContract.View view, @NonNull UserDataSource3 dataSource) {
    this.view = view;
    this.dataSource = dataSource;
    this.subscriptions = new CompositeSubscription();
  }

  @Override public void subscribe() {
    subscriptions.clear();
    subscriptions.add(dataSource.getUser(100)
            .subscribe(new Subscriber<User3>() {
              @Override
              public void onCompleted() {
              }

              @Override
              public void onError(Throwable e) {
                view.showUserError();
              }

              @Override
              public void onNext(User3 user) {
                view.showUser(user);
              }
            }));
  }

  @Override public void unsubscribe() {
    subscriptions.clear();
  }
}
