package com.github.satoshun.example.architecture.data.user;

import com.github.satoshun.example.architecture.data.AutoValueGsonTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UserDataSource {

  @GET("/users/{username}")
  Observable<User> getUser(@Path("username") String userName);

  class Factory {
    public static UserDataSource create() {
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
      return retrofit.create(UserDataSource.class);
    }
  }
}
