package com.clh.test.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.clh.test.payload.requests.SigninRequest;
import com.clh.test.payload.requests.SignupRequest;
import com.clh.test.payload.resources.JwtResource;
import com.clh.test.payload.resources.UserResource;
import com.clh.test.security.jwt.JwtUtils;
import com.clh.test.security.services.UserDetailsImpl;
import com.clh.test.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  @ApiOperation(value = "Login user. To authorize, prefix the word \"Bearer \" (including space) to the generated token.", response = UserResource.class)
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest request) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity
        .ok(new JwtResource(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @ApiOperation(value = "User register, available roles: user, admin (respect lowercase). Use only the roles node.", response = UserResource.class)
  @PostMapping("/signup")
  public ResponseEntity<UserResource> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    UserResource user = userService.signup(signUpRequest);
    return ResponseEntity.ok(user);
  }
}
