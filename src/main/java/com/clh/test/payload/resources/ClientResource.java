package com.clh.test.payload.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.clh.test.model.Client;

public class ClientResource {
  private String id;
  private String identityCode;
  private String firstName;
  private String lastname;
  private String email;
  private String address;
  private Date createdAt;
  private Date updatedAt;

  public String getId() {
    return id;
  }

  public ClientResource setId(String id) {
    this.id = id;
    return this;
  }

  public String getIdentityCode() {
    return identityCode;
  }

  public ClientResource setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public ClientResource setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public ClientResource setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public ClientResource setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ClientResource setAddress(String address) {
    this.address = address;
    return this;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public ClientResource setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public ClientResource setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public static ClientResource createFromEntity(Client client) {
    ClientResource res = new ClientResource();
    res.setId(client.getId()).setIdentityCode(client.getIdentityCode()).setFirstName(client.getFirstName())
        .setLastname(client.getLastname()).setEmail(client.getEmail()).setAddress(client.getAddress())
        .setCreatedAt(client.getCreatedAt()).setUpdatedAt(client.getUpdatedAt());
    return res;
  }

  public static List<ClientResource> createFormEntityList(List<Client> items) {
    if (items == null) {
      return Collections.emptyList();
    }

    List<ClientResource> resources = new ArrayList<>();
    for (Client item : items) {
      resources.add(ClientResource.createFromEntity(item));
    }
    return resources;
  }
}
