package com.clh.test.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clh.test.exceptions.ResourceAlreadyExistsException;
import com.clh.test.exceptions.ResourceNotFoundException;
import com.clh.test.model.ERole;
import com.clh.test.model.Role;
import com.clh.test.model.User;
import com.clh.test.payload.requests.SignupRequest;
import com.clh.test.payload.requests.UpdateUserRequest;
import com.clh.test.payload.resources.UserResource;
import com.clh.test.repositories.UserRepository;
import com.clh.test.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Override
  public UserResource find(String id) {
    User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return UserResource.createFromEntity(user);
  }

  @Override
  public UserResource findByUsername(String username) {
    User user = repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return UserResource.createFromEntity(user);
  }

  @Override
  public List<UserResource> getUsers() {
    List<User> users = repository.findAll();
    return UserResource.createFormEntityList(users);
  }

  @Override
  public UserResource signup(SignupRequest request) {
    if (repository.existsByUsername(request.getUsername())) {
      throw new ResourceAlreadyExistsException("Username already exists");
    }

    if (repository.existsByEmail(request.getEmail())) {
      throw new ResourceAlreadyExistsException("Email already exists");
    }

    User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));

    Set<String> strRoles = request.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new ResourceNotFoundException("Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new ResourceNotFoundException("Role is not found."));
          roles.add(adminRole);

          break;

        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new ResourceNotFoundException("Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    User result = repository.save(user);
    return UserResource.createFromEntity(result);
  }

  @Override
  public UserResource update(String id, UpdateUserRequest request) {
    User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    if (!user.getUsername().equalsIgnoreCase(request.getUsername())) {
      if (repository.existsByUsername(request.getUsername())) {
        throw new ResourceAlreadyExistsException("Username already exists");
      }
    }

    if (!user.getEmail().equalsIgnoreCase(request.getEmail())) {
      if (repository.existsByEmail(request.getEmail())) {
        throw new ResourceAlreadyExistsException("Email already exists");
      }
    }

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(encoder.encode(request.getPassword()));

    user = repository.save(user);
    return UserResource.createFromEntity(user);
  }

  @Override
  public void delete(String id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return;
    }
    throw new ResourceNotFoundException("User not found");
  }

}
