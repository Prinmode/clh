package com.clh.test.repositories;

import java.util.Optional;

import com.clh.test.model.ERole;
import com.clh.test.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}