package com.github.satoshun_example.architecture.data.user.local;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.github.satoshun_example.architecture.data.user.User;
import com.github.satoshun_example.architecture.data.user.UserDataSource;

import java.util.List;

import rx.Observable;

public class UserLocalDataSource implements UserDataSource {

  @NonNull private final static SparseArray<User> cache = new SparseArray<>();

  @NonNull
  @Override public Observable<User> getUser(final int id) {
    User user = cache.get(id);
    if (user == null) {
      return Observable.error(new Resources.NotFoundException());
    }
    return Observable.just(user);
  }

  @NonNull
  @Override public Observable<List<User>> getUsers() {
    return Observable.error(new Resources.NotFoundException());
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull final User user) {
    cache.put(user.getId(), user);
    return Observable.just(null);
  }

  @NonNull
  @Override public Observable<Void> deleteUser(final int id) {
    if (cache.get(id) == null) {
      return Observable.error(new Resources.NotFoundException());
    }
    cache.delete(id);
    return Observable.just(null);
  }
}
