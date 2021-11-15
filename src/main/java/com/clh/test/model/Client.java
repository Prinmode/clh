package com.clh.test.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class Client {

  @Id
  private String id;
  private String identityCode;
  private String firstName;
  private String lastname;
  private String email;
  private String address;
  private Date createdAt = new Date();
  private Date updatedAt;

  public Client() {
  }

  public Client(String id, String identityCode, String firstName, String lastname, String email, String address) {
    this.id = id;
    this.identityCode = identityCode;
    this.firstName = firstName;
    this.lastname = lastname;
    this.email = email;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public Client setId(String id) {
    this.id = id;
    return this;
  }

  public String getIdentityCode() {
    return identityCode;
  }

  public Client setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Client setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public Client setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Client setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Client setAddress(String address) {
    this.address = address;
    return this;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Client setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public Client setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  @Override
  public String toString() {
    return this.getIdentityCode() + " - " + this.getFirstName() + " " + this.getLastname();
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, createdAt, email, firstName, id, identityCode, lastname, updatedAt);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Client other = (Client) obj;
    return Objects.equals(address, other.address) && Objects.equals(createdAt, other.createdAt)
        && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
        && Objects.equals(id, other.id) && Objects.equals(identityCode, other.identityCode)
        && Objects.equals(lastname, other.lastname) && Objects.equals(updatedAt, other.updatedAt);
  }

}
