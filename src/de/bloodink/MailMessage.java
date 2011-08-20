package de.bloodink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7281618369359482981L;

    private String subject;
    private String content;
    private List<String> recipients;

    public MailMessage() {
        recipients = new ArrayList<String>();
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;

    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String conntent) {
        this.content = conntent;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return "MailMessage [subject=" + subject + ", conntent=" + content
                + ", recipients=" + recipients + "]";
    }

    public void addRecipient(String string) {
        recipients.add(string);

    }

}
