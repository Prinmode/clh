package com.clh.test.payload.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clh.test.model.User;

public class UserResource {
  private String Id;
  private String username;
  private String email;
  private String role;

  public String getId() {
    return Id;
  }

  public UserResource setId(String id) {
    Id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserResource setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserResource setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getRole() {
    return role;
  }

  public UserResource setRole(String role) {
    this.role = role;
    return this;
  }

  public static UserResource createFromEntity(User item) {
    UserResource res = new UserResource();
    res.setId(item.getId()).setUsername(item.getUsername()).setEmail(item.getEmail())
        .setRole(item.getRoles().iterator().next().getName().name());
    ;
    return res;
  }

  public static List<UserResource> createFormEntityList(List<User> items) {
    if (items == null) {
      return Collections.emptyList();
    }

    List<UserResource> resources = new ArrayList<>();
    for (User item : items) {
      resources.add(UserResource.createFromEntity(item));
    }
    return resources;
  }

}