package info.selfhost.stammingerit.playground.webapptest.service;

import static info.selfhost.stammingerit.playground.webapptest.service.UserService.AuthenticationResult.BAD_CREDENTIALS;
import static info.selfhost.stammingerit.playground.webapptest.service.UserService.AuthenticationResult.SUCCESSFUL;
import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.repository.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(String userId){
        return userRepository.findOne(userId);
    }
    
    public List<User> findUsersByUsernamePattern(String usernamePattern) {
    	return new ArrayList<>(userRepository.findUsersByUsernamePattern(usernamePattern));
    }

    public void create(User user){
        userRepository.create(user);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public AuthenticationServiceResult authenticateAndGet(String userName, String password) {
    	final AuthenticationServiceResult result = new AuthenticationServiceResult();
    	result.user = userRepository.authenticateAndGet(userName, password);
    	result.authenticationResult = ((result.user != null) ? SUCCESSFUL : BAD_CREDENTIALS);
    	
        return result;
    }

	public List<User> findAllUsers() {
		final List<User> result = new ArrayList<>();
		for (final User user : userRepository.findAll()) {
			result.add(user);
		}
		return result;
	}

	
	public static enum AuthenticationResult {
		SUCCESSFUL,
		UNKNOWN_USER,
		BAD_CREDENTIALS
	}
	
	
	public static class AuthenticationServiceResult implements Serializable {
		private static final long serialVersionUID = 1L;

		public AuthenticationResult authenticationResult;
		public User user;
	}
	
}
