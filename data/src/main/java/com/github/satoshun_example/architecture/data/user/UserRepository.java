package com.github.satoshun_example.architecture.data.user;

import android.support.annotation.NonNull;

import java.util.List;

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

  @NonNull
  @Override public Observable<List<User>> getUsers() {
    Observable<List<User>> remote = remoteDataSource.getUsers();
    return remote
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull User user) {
    Observable<Void> remote = remoteDataSource.saveUser(user);
    return remote
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<Void> deleteUser(int id) {
    Observable<Void> remote = remoteDataSource.deleteUser(id);
    return remote
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }
}
