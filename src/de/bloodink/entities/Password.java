package de.bloodink.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Password
 */
@Entity
public class Password implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String hash;
    private String salt;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false)
    private Date createDate;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean valid;
    private static final long serialVersionUID = 1L;

    public Password() {
        super();
    }

    public Password(String p) {
        this.hash = p;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean getValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Password [Id=" + Id + ", hash=" + hash + ", salt=" + salt
                + ", createDate=" + createDate + ", valid=" + valid + "]";
    }

}
