package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.KeycloakUser;
import com.digitalmedia.users.model.dto.UserListKeycloak;

import java.util.List;

public interface IUserKeycloakRepository {

    List<UserListKeycloak> getUsersNotAdmin();

    KeycloakUser getUserById(String imdbId);

    KeycloakUser getUserByUsername(String username);
}
