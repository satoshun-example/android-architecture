package com.github.satoshun.example.architecture.repo;

import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.repo.Repo;

interface RepoContract {

  interface View {
    void renderRepo(@NonNull Repo repo);
  }

  interface Presenter {
    void subscribe();

    void unsubscribe();
  }
}
