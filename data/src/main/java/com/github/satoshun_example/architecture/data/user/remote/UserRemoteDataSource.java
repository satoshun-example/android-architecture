package com.github.satoshun_example.architecture.data.user.remote;

import android.support.annotation.NonNull;

import com.github.satoshun_example.architecture.data.user.User;
import com.github.satoshun_example.architecture.data.user.UserDataSource;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class UserRemoteDataSource implements UserDataSource {

  @NonNull
  @Override public Observable<User> getUser(int id) {
    User user = new User(id);

    // simulate network access
    return Observable.just(user)
            .delay(2000, TimeUnit.MILLISECONDS);
  }
}
