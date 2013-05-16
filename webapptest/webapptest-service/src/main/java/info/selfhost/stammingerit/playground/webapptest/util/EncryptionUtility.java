package info.selfhost.stammingerit.playground.webapptest.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtility {
	private static final Integer HASH_ITERATIONS = 10000;

	public static String encryptStringWithSalt(String password, String salt) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(Base64.decodeBase64(salt));
			byte[] input = digest.digest(password.getBytes("UTF-8"));
			
			for (int count = 0; count < HASH_ITERATIONS; count++) {
				digest.reset();
				input = digest.digest(input);
			}
			
			return Base64.encodeBase64String(input);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] getSalt() {
		try {
			final SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
			final byte[] salt = new byte[8];
		
			rnd.nextBytes(salt);
			return salt;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
