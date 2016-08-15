package com.github.satoshun_example.architecture.data.user;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserRepository implements UserDataSource {

  @NonNull private final UserDataSource localDataSource;
  @NonNull private final UserDataSource remoteDataSource;

  public UserRepository(@NonNull UserDataSource localDataSource,
                        @NonNull UserDataSource remoteDataSource) {
    this.localDataSource = localDataSource;
    this.remoteDataSource = remoteDataSource;
  }

  @NonNull
  @Override public Observable<User> getUser(int id) {
    return localDataSource.getUser(id)
            .onErrorResumeNext(remoteDataSource.getUser(id))
            .doOnNext(new Action1<User>() {
              @Override public void call(User user) {
                localDataSource.saveUser(user);
              }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<List<User>> getUsers() {
    return remoteDataSource.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull User user) {
    return Observable.merge(localDataSource.saveUser(user), remoteDataSource.saveUser(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<Void> deleteUser(int id) {
    return Observable.merge(localDataSource.deleteUser(id), remoteDataSource.deleteUser(id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }
}
