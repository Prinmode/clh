package com.clh.test.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CreateClientRequest {

  @NotEmpty
  private String identityCode;
  @NotEmpty
  private String firstName;
  private String lastName;
  @NotEmpty
  @Email
  private String email;
  private String address;

  public String getIdentityCode() {
    return identityCode;
  }

  public void setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}
