package com.github.satoshun.example.architecture.data.user;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserRepository implements UserDataSource3 {

  @NonNull private final UserDataSource3 localDataSource;
  @NonNull private final UserDataSource3 remoteDataSource;

  public UserRepository(@NonNull UserDataSource3 localDataSource,
                        @NonNull UserDataSource3 remoteDataSource) {
    this.localDataSource = localDataSource;
    this.remoteDataSource = remoteDataSource;
  }

  @NonNull
  @Override public Observable<User3> getUser(int id) {
    return localDataSource.getUser(id)
            .onErrorResumeNext(remoteDataSource.getUser(id))
            .doOnNext(new Action1<User3>() {
              @Override public void call(User3 user) {
                localDataSource.saveUser(user);
              }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<List<User3>> getUsers() {
    return remoteDataSource.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull User3 user) {
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
