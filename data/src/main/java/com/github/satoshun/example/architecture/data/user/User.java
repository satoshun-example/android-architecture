package com.github.satoshun.example.architecture.data.user;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@AutoValue
public abstract class User {

  @SerializedName("login") abstract String login();

  @SerializedName("id") abstract int id();

  @SerializedName("avatar_url") abstract String avatarUrl();

  @SerializedName("gravatar_id") abstract String gravatarId();

  @SerializedName("url") abstract String url();

  @SerializedName("html_url") abstract String htmlUrl();

  @SerializedName("followers_url") abstract String followersUrl();

  @SerializedName("following_url") abstract String followingUrl();

  @SerializedName("gists_url") abstract String gistsUrl();

  @SerializedName("starred_url") abstract String starredUrl();

  @SerializedName("subscriptions_url") abstract String subscriptionsUrl();

  @SerializedName("organizations_url") abstract String organizationsUrl();

  @SerializedName("repos_url") abstract String reposUrl();

  @SerializedName("events_url") abstract String eventsUrl();

  @SerializedName("received_events_url") abstract String receivedEventsUrl();

  @SerializedName("type") abstract String type();

  @SerializedName("site_admin") abstract boolean siteAdmin();

  @SerializedName("name") abstract String name();

  @SerializedName("company") abstract String company();

  @SerializedName("blog") abstract String blog();

  @SerializedName("location") abstract String location();

  @SerializedName("email") abstract String email();

  @SerializedName("hireable") abstract boolean hireable();

  @SerializedName("bio") abstract Object bio();

  @SerializedName("public_repos") abstract int publicRepos();

  @SerializedName("public_gists") abstract int publicGists();

  @SerializedName("followers") abstract int followers();

  @SerializedName("following") abstract int following();

  @SerializedName("created_at") abstract Date createdAt();

  @SerializedName("updated_at") abstract Date updatedAt();

  public static TypeAdapter<User> typeAdapter(Gson gson) {
    return new AutoValue_User.GsonTypeAdapter(gson);
  }
}
