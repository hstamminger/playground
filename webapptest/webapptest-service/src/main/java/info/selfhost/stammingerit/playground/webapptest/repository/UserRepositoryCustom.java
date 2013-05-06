package info.selfhost.stammingerit.playground.webapptest.repository;

import info.selfhost.stammingerit.playground.webapptest.entities.User;

import java.util.List;

public interface UserRepositoryCustom {
    User findUserByUsername(String username);
    void create(User user);
    User authenticateAndGet(String userName, String password);
}
