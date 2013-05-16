package info.selfhost.stammingerit.playground.webapptest.repository;

import info.selfhost.stammingerit.playground.webapptest.entities.QUser;
import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.util.EncryptionUtility;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;

import com.mysema.query.jpa.impl.JPAQuery;

public class UserRepositoryImpl implements UserRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public User findUserByUsername(String username) {
		return new JPAQuery(em).from(QUser.user).where(QUser.user.username.eq(username)).singleResult(QUser.user);
	}

	@Override
	public Collection<User> findUsersByUsernamePattern(String usernamePattern) {
		return new JPAQuery(em).from(QUser.user).where(QUser.user.username.like(usernamePattern)).list(QUser.user);
	}

	@Override
	public void create(User user) {
		try {
			user.setSalt(Base64.encodeBase64String(EncryptionUtility.getSalt()));
			user.setPassword(EncryptionUtility.encryptStringWithSalt(user.getPassword(), user.getSalt()));
		
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
			if (user != null && (user.getPassword().equals(EncryptionUtility.encryptStringWithSalt(password, user.getSalt())))) {
				return user;
			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
