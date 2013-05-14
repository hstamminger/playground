package info.selfhost.stammingerit.playground.webapptest.service;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.repository.UserRepository;

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

    public void create(User user){
        userRepository.create(user);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User authenticateAndGet(String userName, String password) {
        return userRepository.authenticateAndGet(userName, password);
    }
}
