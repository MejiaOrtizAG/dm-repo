package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.KeycloakUser;
import com.digitalmedia.users.model.dto.UserListKeycloak;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserKeycloakRepository implements IUserKeycloakRepository {

    @Autowired
    private Keycloak keycloakClient;

    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public List<UserListKeycloak> getUsersNotAdmin() {
        List<UserRepresentation> userRepresentationList = keycloakClient.realm(realm).users().list();
        List<UserRepresentation> usersEnabled = userRepresentationList.stream().filter(userRepresentation ->
                        !keycloakClient.realm(realm).users().get(userRepresentation.getId())
                                .groups().stream().map(grop -> grop.getName())
                                .collect(Collectors.toList()).contains("admin"))
                .collect(Collectors.toList());
        return usersEnabled.stream().map(this::toUserKeycloakList).collect(Collectors.toList());
    }
    @Override
    public KeycloakUser getUserById(String imdbId) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm).users()
                .get(imdbId).toRepresentation();
        return toUserKeycloak(userRepresentation);
    }

    @Override
    public KeycloakUser getUserByUsername(String username) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm).users()
                .search(username).get(0);
        return toUserKeycloak(userRepresentation);
    }

    private UserListKeycloak toUserKeycloakList(UserRepresentation userRepresentation){
        return new UserListKeycloak(userRepresentation.getUsername(), userRepresentation.getEmail());
    }

    private KeycloakUser toUserKeycloak(UserRepresentation userRepresentation){
        return new KeycloakUser(userRepresentation.getUsername(), userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail());
    }
}
