package de.bloodink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Pojo to collect Mail Information.
 * 
 * @author Franz Mathauser
 */
public class MailMessage implements Serializable {

    /**
     * Serializable Generated Value.
     */
    private static final long serialVersionUID = 7281618369359482981L;

    /**
     * Mail Subject.
     */
    private String subject;

    /**
     * Mail Content.
     */
    private String content;

    /**
     * List of Mail Recipients.
     */
    private final List<String> recipients;

    /**
     * Default Consructor.
     */
    public MailMessage() {
        recipients = new ArrayList<String>();
    }

    /**
     * Getter subject.
     * 
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter mail content.
     * 
     * @return content
     */
    public String getContent() {
        return content;

    }

    /**
     * Getter recipients list.
     * 
     * @return list of recipients
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets mail subject.
     * 
     * @param s
     *            subject
     */
    public void setSubject(String s) {
        this.subject = s;
    }

    /**
     * Sets mail content.
     * 
     * @param c
     *            content
     */
    public void setContent(String c) {
        this.content = c;
    }

    @Override
    public String toString() {
        return "MailMessage [subject=" + subject + ", conntent=" + content
                + ", recipients=" + recipients + "]";
    }

    /**
     * Add a recipient to the list.
     * 
     * @param string
     *            email address
     */
    public void addRecipient(String string) {
        recipients.add(string);

    }

}
