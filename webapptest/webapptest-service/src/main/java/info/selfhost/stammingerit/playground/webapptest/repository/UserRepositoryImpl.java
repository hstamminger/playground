package info.selfhost.stammingerit.playground.webapptest.repository;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.entities.QUser;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private Integer hashIterations = 10000;

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByUsername(String username) {
        JPQLQuery query = new JPAQuery(em);
        return query.from(QUser.user).where(QUser.user.name.eq(username)).singleResult(QUser.user);
    }

    @Override
    public void create(User user) {
        try {
            byte[] salt = getSalt();
            user.setSalt(Base64.encodeBase64String(salt));
            user.setPassword(encryptStringWithSalt(user.getPassword(), user.getSalt()));
            em.persist(user);
            em.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User authenticateAndGet(String userName, String password) {
        try {
            User user = findUserByUsername(userName);
            if (user != null && (user.getPassword().equals(encryptStringWithSalt(password, user.getSalt()))))
                return user;
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encryptStringWithSalt(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(Base64.decodeBase64(salt));
            byte[] input = digest.digest(password.getBytes("UTF-8"));
            for (int count = 0; count < hashIterations; count++) {
                digest.reset();
                input = digest.digest(input);
            }
            return Base64.encodeBase64String(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getSalt() {
        try {
            SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[8];
            rnd.nextBytes(salt);
            return salt;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
