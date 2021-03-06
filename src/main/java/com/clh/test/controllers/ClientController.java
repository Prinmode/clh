package com.clh.test.controllers;

import java.util.List;
import javax.validation.Valid;

import com.clh.test.payload.requests.CreateClientRequest;
import com.clh.test.payload.requests.UpdateClientRequest;
import com.clh.test.payload.resources.ClientResource;
import com.clh.test.services.ClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  private final static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

  @Autowired
  private ClientService clientService;

  @ApiOperation(value = "Get all clients. Authorized roles: user, admin.", response = ClientResource.class)
  @GetMapping()
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public List<ClientResource> getClients() {
    return this.clientService.getClients();
  }

  @ApiOperation(value = "Get one client by id. Authorized roles: user, admin.", response = ClientResource.class)
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<ClientResource> getClient(@PathVariable String id) {
    ClientResource client = this.clientService.find(id);
    if (client == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.ok(client);
  }

  @ApiOperation(value = "Create a client. Authorized roles: only admin.", response = ClientResource.class)
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ADMIN')")
  public ClientResource createClient(@Valid @RequestBody CreateClientRequest request) {
    return this.clientService.create(request);
  }

  @ApiOperation(value = "Update a client. Authorized roles: only admin.", response = ClientResource.class)
  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ClientResource putPerson(@PathVariable("id") String id, @Valid @RequestBody UpdateClientRequest person) {
    return this.clientService.update(id, person);
  }

  @ApiOperation(value = "Delete a client. Authorized roles: only admin.", response = ClientResource.class)
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteClient(@PathVariable String id) {
    this.clientService.delete(id);
  }

}