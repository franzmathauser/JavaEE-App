package de.bloodink.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * PasswordHasher can be used to transform clear text into a one-way
 * Hashfunction or verify a haseh value with with clear text. SHA-512 is used to
 * generate the Hash the value.
 * 
 * @author Franz Mathauser
 */
public class Sha512Hasher {

    /**
     * Hash algorithm.
     */
    public static final String HASH_ALGORITHM = "SHA-512";

    /**
     * Creates a hash value from input param.
     * 
     * @param passwordBytes
     *            cleartext
     * @return hashed value
     * @throws NoSuchAlgorithmException
     *             hash algorithm not found
     */
    public String hashPassword(byte[] passwordBytes)
            throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] hashBytes = digest.digest(passwordBytes);
        String hashString = Base64.encode(hashBytes);
        return hashString;

    }

    /**
     * Verifyes a hashvalue with cleartext.
     * 
     * @param passwordBytes
     *            cleartext
     * @param hashString
     *            hashed value
     * @return result
     * @throws NoSuchAlgorithmException
     *             hash algorithm not found
     */
    public boolean verifyPassword(byte[] passwordBytes, String hashString)
            throws NoSuchAlgorithmException {

        return this.hashPassword(passwordBytes).equals(hashString);
    }
}
