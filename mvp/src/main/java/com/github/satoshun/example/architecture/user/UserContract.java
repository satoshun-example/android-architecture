package com.github.satoshun.example.architecture.user;

import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.user.User3;

interface UserContract {

  interface View {
    void showUser(@NonNull User3 user);

    void showUserError();
  }

  interface Presenter {
    void subscribe();

    void unsubscribe();
  }
}
