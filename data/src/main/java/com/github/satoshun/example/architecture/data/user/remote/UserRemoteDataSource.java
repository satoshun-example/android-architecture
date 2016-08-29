package com.github.satoshun.example.architecture.data.user.remote;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.user.User3;
import com.github.satoshun.example.architecture.data.user.UserDataSource3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class UserRemoteDataSource implements UserDataSource3 {

  private final static List<User3> data;

  static {
    data = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      data.add(new User3(i, "name:" + i, (i + 10) % 80));
    }
  }

  @NonNull
  @Override public Observable<User3> getUser(final int id) {
    // simulate network access
    return Observable.create(new Observable.OnSubscribe<User3>() {
      @Override public void call(Subscriber<? super User3> subscriber) {
        User3 user = data.get(id);
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
  @Override public Observable<List<User3>> getUsers() {
    // simulate network access
    return Observable.just(data)
            .delay(5000, TimeUnit.MILLISECONDS);
  }

  @NonNull
  @Override public Observable<Void> saveUser(@NonNull final User3 user) {
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
        for (User3 user : data) {
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
