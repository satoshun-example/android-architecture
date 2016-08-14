package com.github.satoshun_example.architecture.data.user;

import android.support.annotation.NonNull;

import rx.Observable;

public interface UserDataSource {
  @NonNull Observable<User> getUser(int id);
}
