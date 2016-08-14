package com.github.satoshun_example.architecture.data.user;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepository implements UserDataSource {

  @NonNull private final UserDataSource remoteDataSource;

  public UserRepository(@NonNull UserDataSource remoteDataSource) {
    this.remoteDataSource = remoteDataSource;
  }

  @NonNull
  @Override public Observable<User> getUser(int id) {
    Observable<User> remote = remoteDataSource.getUser(id);
    return remote
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }
}
