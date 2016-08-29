package com.github.satoshun.example.architecture.data.repo;

import android.support.annotation.NonNull;

import com.github.satoshun.example.architecture.data.AutoValueGsonTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RepoDataSource {

  @GET("/users/{username}/repos")
  @NonNull Observable<List<Repo>> getRepositories(@Path("username") String userName);

  class Factory {
    public static RepoDataSource create() {
      OkHttpClient.Builder builder = new OkHttpClient.Builder()
              .addInterceptor(new HttpLoggingInterceptor());
      Gson gson = new GsonBuilder()
              .registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
              .create();
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("https://api.github.com/")
              .addConverterFactory(GsonConverterFactory.create(gson))
              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .client(builder.build())
              .build();
      return retrofit.create(RepoDataSource.class);
    }
  }
}
