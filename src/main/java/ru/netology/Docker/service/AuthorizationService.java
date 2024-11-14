package ru.netology.Docker.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.netology.Docker.controller.Authorities;
import ru.netology.Docker.exception.InvalidCredentials;
import ru.netology.Docker.exception.UnauthorizedUser;
import ru.netology.Docker.repository.UserRepository;

import java.util.List;
@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String name, String password) {
        if (!StringUtils.hasLength(name) || !StringUtils.hasLength(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(name, password);
        if (CollectionUtils.isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown name " + name);
        }
        return userAuthorities;
    }
}

