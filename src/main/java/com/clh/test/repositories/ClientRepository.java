package com.clh.test.repositories;

import com.clh.test.model.Client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
  Client findByIdentityCode(String identityCode);

  Client findByEmail(String email);

  Boolean existsByIdentityCode(String identityCode);

  Boolean existsByEmail(String email);
}
