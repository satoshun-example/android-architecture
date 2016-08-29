package com.github.satoshun.example.architecture.users;

import com.github.satoshun.example.architecture.data.repo.Repo;

import java.util.List;

interface ReposContract {

  interface View {
    void renderRepos(List<Repo> repos);

    void showProgressIndicator();

    void hideProgressIndicator();
  }

  interface Presenter {
    void subscribe();

    void unsubscribe();

    void refresh();
  }
}
