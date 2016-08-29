package com.github.satoshun.example.architecture.data.repo;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;


public class RepoRepository implements RepoDataSource {

  @NonNull private final RepoDataSource remoteDataSource;

  public RepoRepository(@NonNull RepoDataSource remoteDataSource) {
    this.remoteDataSource = remoteDataSource;
  }

  @NonNull
  @Override public Observable<List<Repo>> getRepositories(@NonNull String userName) {
    return remoteDataSource.getRepositories(userName);
  }
}
