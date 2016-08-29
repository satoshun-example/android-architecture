package com.github.satoshun.example.architecture.data.user;

public class User3 {

  private final int id;
  private final String nickname;
  private final int age;

  public User3(int id, String nickname, int age) {
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
