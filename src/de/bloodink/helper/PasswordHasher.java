package de.bloodink.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class PasswordHasher {

	public static final String HASH_ALGORITHM = "SHA-256";

	public String hashPassword(byte[] passwordBytes)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		byte[] hashBytes = digest.digest(passwordBytes);
		String hashString = Base64.encode(hashBytes);
		return hashString;
	}

	public boolean verifyPassword(byte[] passwordBytes, String hashString)
			throws NoSuchAlgorithmException {
		return hashPassword(passwordBytes).equals(hashString);
	}
}
