package com.github.satoshun_example.architecture.data.user;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

public interface UserDataSource {
  @NonNull Observable<User> getUser(int id);

  @NonNull Observable<List<User>> getUsers();

  @NonNull Observable<Void> saveUser(@NonNull User user);

  @NonNull Observable<Void> deleteUser(int id);
}
