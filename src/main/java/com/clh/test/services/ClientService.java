package com.clh.test.services;

import java.util.List;

import com.clh.test.payload.requests.CreateClientRequest;
import com.clh.test.payload.requests.UpdateClientRequest;
import com.clh.test.payload.resources.ClientResource;

public interface ClientService {
  public abstract ClientResource find(String id);

  public abstract ClientResource findByIdentityCode(String identityCode);

  public abstract List<ClientResource> getClients();

  public abstract ClientResource create(CreateClientRequest request);

  public abstract ClientResource update(String id, UpdateClientRequest request);

  public abstract void delete(String id);
}
