JavaEE Project
==============
This is my first real JavaEE Application. Running on Glassfish-Server 3.1 and MySql.

Configuration Settings

JavaMail Sessions
-----------------
The following data was set up in Glassfish to have mail support.

* jdbc-ressource: jdbc/javamail
* Mail-Host: smtp.gmail.com
* Default-User: franz.mathauser@googlemail.com
* Default Return Address: info@blood-ink.de
* Status: True
* Protocol Store: imap
* Store Protocol Class: com.sun.mail.imap.IMAPStore
* Transport Protocol: smpt
* Transport Protocol Class: com.sun.mail.smtp.SMTPTransport
* Debug: False

additional-properties:

* mail.smtp.socketFactory.port: 465
* mail.smtp.port: 465
* mail.smtp.username: franz.mathauser@googlemail.com
* mail.smtp.auth: true
* mail.smtp.password: (password)
* mail.smtp.socketFactory.class: javax.net.ssl.SSLSocketFactory


JMS
---
The following set up a jms connection factory and a queue.

The queue is used to send asynchronious mails.

1. asadmin create-jms-resource --restype javax.jms.ConnectionFactory jdbc/jmsConnectionFactory
2. asadmin create-jms-resource --restype javax.jms.Queue jdbc/jmsQueue

Log4j
-----
Log4j is a Logging-Framework for Java-Environment. The jar is included in 'WebContent/WEB-INF/lib directory'.

The configuration is done by the file:

    WebContent/WEB-INF/log4j.properties

Make sure to point the log file to a valid directory on your system.

### Change the Loglevel on Runtime
1. change propertiesfile and request: https://localhost:8181/HelloWorld/init?reloadPropertiesFile=ok
2. set Loglevel Mode via Request: https://localhost:8181/HelloWorld/init?logLevel=DEBUG

MySQL
-----
Mysql-Conector is placed into GLASSFISH_HOME/lib folder.

The JDBC-Ressource is deployed via:

    WEBContent/WEB-INF/sun-resources.xml
    
Change connection setting if needed.