package com.github.satoshun.example.architecture.data.user;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

public interface UserDataSource3 {
  @NonNull Observable<User3> getUser(int id);

  @NonNull Observable<List<User3>> getUsers();

  @NonNull Observable<Void> saveUser(@NonNull User3 user);

  @NonNull Observable<Void> deleteUser(int id);
}
