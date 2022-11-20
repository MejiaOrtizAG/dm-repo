package com.digitalmedia.users.service;

import com.digitalmedia.users.model.KeycloakUser;
import com.digitalmedia.users.model.dto.UserListKeycloak;

import java.util.List;

public interface IUserKeycloakService {
    List<UserListKeycloak> getUsersNotAdmin();
    KeycloakUser getUserById(String imdbId);
    KeycloakUser getUserByUsername(String username);
}
