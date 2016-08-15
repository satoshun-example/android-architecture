package com.github.satoshun_example.architecture.main;

import android.support.annotation.NonNull;

import com.github.satoshun_example.architecture.data.user.User;

interface MainContract {
  interface View {
    void showUser(@NonNull User user);

    void showUserError();
  }

  interface Presenter {
    void subscribe();

    void unsubscribe();
  }
}
