package com.github.satoshun_example.architecture.data.user;

public class User {

  private final int id;
  private final String nickname;
  private final int age;

  public User(int id, String nickname, int age) {
    this.id = id;
    this.nickname = nickname;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public int getAge() {
    return age;
  }
}
