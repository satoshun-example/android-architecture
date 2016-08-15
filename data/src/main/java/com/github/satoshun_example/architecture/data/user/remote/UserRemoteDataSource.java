package com.github.satoshun_example.architecture.data.user.remote;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.github.satoshun_example.architecture.data.user.User;
import com.github.satoshun_example.architecture.data.user.UserDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class UserRemoteDataSource implements UserDataSource {

  private final static List<User> data;

  static {
    data = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      data.add(new User(i));
    }
  }

  @NonNull
  @Override public Observable<User> getUser(final int id) {
    // simulate network access
    return Observable.create(new Observable.OnSubscribe<User>() {
      @Override public void call(Subscriber<? super User> subscriber) {
        User user = data.get(id);
        if (user == null) {
          subscriber.onError(new NoSuchElementException());
          return;
        }
        subscriber.onNext(user);
        subscriber.onCompleted();
      }
    }).delay(2000, TimeUnit.MILLISECONDS);
  }

  @NonNull
  @Override public Observable<List<User>> getUsers() {
    // simulate network access
    return Observable.just(data)
            .delay(5000, TimeUnit.MILLISECONDS);
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull final User user) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        data.add(user);
        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    }).delay(2000, TimeUnit.MILLISECONDS);
  }

  @NonNull
  @Override public Observable<Void> deleteUser(final int id) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        for (User user : data) {
          if (user.getId() == id) {
            data.remove(user);
            subscriber.onNext(null);
            subscriber.onCompleted();
            return;
          }
        }
        subscriber.onError(new Resources.NotFoundException());
      }
    }).delay(2000, TimeUnit.MILLISECONDS);
  }
}
