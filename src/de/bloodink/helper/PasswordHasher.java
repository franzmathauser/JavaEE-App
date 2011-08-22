package de.bloodink.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.log4j.Logger;

public class PasswordHasher extends Sha512Hasher {

    private final Long salt;

    private final Date createDate;

    private static final Logger log = Logger.getLogger(PasswordHasher.class);

    public PasswordHasher() {
        super();
        SecureRandom sr = new SecureRandom();
        this.salt = sr.nextLong();
        long seconds = (new Date().getTime() / 1000) * 1000;
        this.createDate = new Date(seconds);
    }

    public PasswordHasher(Long salt, Date createDate) {
        super();
        this.salt = salt;
        this.createDate = createDate;

    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        int power = (int) Math.abs((salt % 10));

        int rounds = (int) Math.pow(2, power);
        long millis = createDate.getTime();

        if (log.isDebugEnabled()) {
            log.debug("salt:" + salt + " power:" + power + " millis:" + millis
                    + " rounds:" + rounds);
        }

        String hash = password;

        // multiple hashing
        for (int i = 0; i < rounds; i++) {

            if (i % 3 == 0) {
                hash = hash + salt;
            } else if (i % 3 == 1) {
                hash = password + hash;
            } else {
                hash = millis + hash;
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

    public Date getCreateDate() {
        return createDate;
    }
}
