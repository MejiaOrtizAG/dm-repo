package com.digitalmedia.users.service;

import com.digitalmedia.users.model.KeycloakUser;
import com.digitalmedia.users.model.dto.UserListKeycloak;
import com.digitalmedia.users.repository.IUserKeycloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserKeycloakServiceImpl implements IUserKeycloakService{
    @Autowired
    private IUserKeycloakRepository userKeycloackRepository;

    @Override
    public List<UserListKeycloak> getUsersNotAdmin() {
        return userKeycloackRepository.getUsersNotAdmin();
    }

    @Override
    public KeycloakUser getUserById(String imdbId) {
        return userKeycloackRepository.getUserById(imdbId);
    }

    @Override
    public KeycloakUser getUserByUsername(String username) {
        return userKeycloackRepository.getUserByUsername(username);
    }
}
