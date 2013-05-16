package info.selfhost.stammingerit.playground.webapptest.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.makeAccessible;
import static org.springframework.util.ReflectionUtils.setField;
import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.repository.UserRepository;

import java.lang.reflect.Field;

import org.junit.Test;

public class UserServiceTest {

    @Test
    public void testCreateUser() throws Exception{
        User user = new User();
        user.setPassword("1234");
        user.setUsername("testUser");
        UserRepository userDAO = mock(UserRepository.class);
        UserService userService = new UserService();
        setUserDAOOnUserService(userDAO, userService);
        userService.create(user);
        verify(userDAO).create(user);
    }

    @Test
    public void testAuthenticateAndGetUser() throws Exception{
        //needed cause we are testing the password encryption
        User user = new User();
        user.setPassword("1234");
        user.setUsername("testUser");
        UserRepository userDAO = mock(UserRepository.class);
        UserService userService = new UserService();
        setUserDAOOnUserService(userDAO, userService);
        userService.create(user);
        verify(userDAO).create(user);
        //actual signin test
        when(userDAO.findUserByUsername("testUser")).thenReturn(user);
        userService.authenticateAndGet("testUser", "1234");
        verify(userDAO).authenticateAndGet("testUser", "1234");
    }

    private void setUserDAOOnUserService (UserRepository userDAO, UserService userService) {
        Field userDAOField = findField(UserService.class, "userRepository");
        makeAccessible(userDAOField);
        setField(userDAOField, userService, userDAO);
    }
}
