package com.clh.test.services;

import java.util.List;

import com.clh.test.payload.requests.SignupRequest;
import com.clh.test.payload.requests.UpdateUserRequest;
import com.clh.test.payload.resources.UserResource;

public interface UserService {
  public abstract UserResource find(String id);

  public abstract UserResource findByUsername(String username);

  public abstract List<UserResource> getUsers();

  public abstract UserResource signup(SignupRequest signinRequest);

  public abstract UserResource update(String id, UpdateUserRequest request);

  public abstract void delete(String id);
}
