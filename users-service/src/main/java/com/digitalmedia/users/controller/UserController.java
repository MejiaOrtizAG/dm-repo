package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.KeycloakUser;
import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserListKeycloak;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.service.IUserKeycloakService;
import com.digitalmedia.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final IUserService userService;
  private final IUserKeycloakService userKeycloakService;

  @GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }

  @PostMapping("/me")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_admin') or hasAuthority('GROUP_admin')")
  public List<UserListKeycloak> getUsersNotAdmin() {
    return userKeycloakService.getUsersNotAdmin();
  }

  @GetMapping("/admin/{userId}")
  @PreAuthorize("hasRole('ROLE_admin') or hasAuthority('GROUP_admin')")
  public KeycloakUser getUsersKeycloakById(@PathVariable String userId) {
    return userKeycloakService.getUserById(userId);
  }

  @GetMapping("/username/{username}")
  public KeycloakUser getUsersKeycloakByUsername(@PathVariable String username) {
    return userKeycloakService.getUserByUsername(username);
  }
}
