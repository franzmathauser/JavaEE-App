package de.bloodink.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher extends Sha512Hasher {

    private final String salt;

    public PasswordHasher() {
        super();
        SecureRandom sr = new SecureRandom();
        this.salt = "" + sr.nextLong();

    }

    public PasswordHasher(String salt) {
        super();
        this.salt = salt;

    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        int power = Integer.parseInt(salt.charAt(salt.length() - 1) + "");
        int rounds = (int) Math.pow(2, power);

        password = password + salt;
        String hash = super.hashPassword(password.getBytes());

        // multiple hashing
        for (int i = 0; i < rounds; i++) {
            if (i % 2 == 0) {
                hash = password + hash;
            } else {
                hash = hash + salt;
            }

            hash = super.hashPassword(hash.getBytes());
        }

        return hash;
    }

    public boolean verifyPassword(String password, String hashString)
            throws NoSuchAlgorithmException {

        return hashPassword(password).equals(hashString);

    }

    public String getSalt() {
        return salt;
    }
}
