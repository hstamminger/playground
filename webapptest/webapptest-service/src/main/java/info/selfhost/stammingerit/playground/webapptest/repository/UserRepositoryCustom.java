package info.selfhost.stammingerit.playground.webapptest.repository;

import info.selfhost.stammingerit.playground.webapptest.entities.User;

import java.util.Collection;

public interface UserRepositoryCustom {
	User findUserByUsername(String username);
	Collection<User> findUsersByUsernamePattern(String usernamePattern);
	void create(User user);
	User authenticateAndGet(String userName, String password);
}
