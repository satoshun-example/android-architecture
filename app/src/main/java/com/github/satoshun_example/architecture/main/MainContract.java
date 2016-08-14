package com.github.satoshun_example.architecture.main;

import com.github.satoshun_example.architecture.data.user.User;

interface MainContract {
  interface View {
    void showUser(User user);

    void showUserError();
  }

  interface Presenter {
    void subscribe();

    void unsubscribe();
  }
}
