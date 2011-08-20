README


JavaMail Sessions
=================
jdbc-ressource: jdbc/javamail
Mail-Host: smtp.gmail.com
Default-User: franz.mathauser@googlemail.com
Default Return Address: info@blood-ink.de
Status: True

Protocol Store: imap
Store Protocol Class: com.sun.mail.imap.IMAPStore
Transport Protocol: smpt
Transport Protocol Class: com.sun.mail.smtp.SMTPTransport
Debug: False

additional-properties
---------------------
mail.smtp.socketFactory.port: 465
mail.smtp.port: 465
mail.smtp.username: franz.mathauser@googlemail.com
mail.smtp.auth: true
mail.smtp.password: <password>
mail.smtp.socketFactory.class: javax.net.ssl.SSLSocketFactory


JMS
==================
asadmin create-jms-resource --restype javax.jms.ConnectionFactory jdbc/jmsConnectionFactory
asadmin create-jms-resource --restype javax.jms.Queue jdbc/jmsQueue