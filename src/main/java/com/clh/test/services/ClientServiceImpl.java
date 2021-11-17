package com.clh.test.services;

import java.util.List;

import com.clh.test.exceptions.ResourceAlreadyExistsException;
import com.clh.test.exceptions.ResourceNotFoundException;
import com.clh.test.model.Client;
import com.clh.test.payload.requests.CreateClientRequest;
import com.clh.test.payload.requests.UpdateClientRequest;
import com.clh.test.payload.resources.ClientResource;
import com.clh.test.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository repository;

  public ClientServiceImpl() {
  }

  @Override
  public ClientResource find(String id) {
    Client client = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    return ClientResource.createFromEntity(client);
  }

  @Override
  public ClientResource findByIdentityCode(String identityCode) {
    Client client = this.repository.findByIdentityCode(identityCode);
    if (client == null) {
      throw new ResourceNotFoundException("Client not found");
    }
    return ClientResource.createFromEntity(client);
  }

  @Override
  public List<ClientResource> getClients() {
    List<Client> clients = this.repository.findAll();
    return ClientResource.createFormEntityList(clients);
  }

  @Override
  public ClientResource create(CreateClientRequest request) {
    if (this.repository.findByIdentityCode(request.getIdentityCode()) != null) {
      throw new ResourceAlreadyExistsException(
          "Client with identity code \"" + request.getIdentityCode() + "\" already exists");
    }

    Client client = new Client();
    client.setIdentityCode(request.getIdentityCode()).setFirstName(request.getFirstName())
        .setLastname(request.getLastName()).setEmail(request.getEmail()).setAddress(request.getAddress());
    client = this.repository.save(client);
    return ClientResource.createFromEntity(client);
  }

  @Override
  public ClientResource update(String id, UpdateClientRequest request) {
    Client client = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

    if (!client.getIdentityCode().equalsIgnoreCase(request.getIdentityCode())) { // if the identity code is
                                                                                 // different
      if (this.repository.findByIdentityCode(request.getIdentityCode()) != null) {
        throw new ResourceAlreadyExistsException(
            "Client with identity code \"" + request.getIdentityCode() + "\" already exists");
      }
    }

    if (!client.getEmail().equalsIgnoreCase(request.getEmail().toLowerCase())) { // if the email is different
      if (this.repository.findByEmail(request.getEmail()) != null) {
        throw new ResourceAlreadyExistsException(
            "Client with email \"" + request.getIdentityCode() + "\" already exists");
      }
    }

    client.setIdentityCode(request.getIdentityCode()).setFirstName(request.getFirstName())
        .setLastname(request.getLastName()).setEmail(request.getEmail()).setAddress(request.getAddress());
    client = this.repository.save(client);
    return ClientResource.createFromEntity(client);
  }

  @Override
  public void delete(String id) {
    this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    this.repository.deleteById(id);
    return;
  }

}
