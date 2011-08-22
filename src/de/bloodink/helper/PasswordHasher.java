package de.bloodink.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher extends Sha512Hasher {

    private final Long salt;

    public PasswordHasher() {
        super();
        SecureRandom sr = new SecureRandom();
        this.salt = sr.nextLong();

    }

    public PasswordHasher(Long salt) {
        super();
        this.salt = salt;

    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        int power = (int) (salt % 10);
        int rounds = (int) Math.pow(2, power);

        String hash = password;

        // multiple hashing
        for (int i = 0; i < rounds; i++) {

            if (i % 2 == 0) {
                hash = hash + salt;
            } else {
                hash = password + hash;
            }
            hash = super.hashPassword(hash.getBytes());
        }

        return hash;
    }

    public boolean verifyPassword(String password, String hashString)
            throws NoSuchAlgorithmException {

        return hashPassword(password).equals(hashString);

    }

    public Long getSalt() {
        return salt;
    }
}
