package dev.mbkaue.comunicador;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author kauebarbosa
 */
public class ClientEmail {

    private final Mensagem objMENSSAGE;
    private BeanCredentials env = new BeanCredentials();

    private Session configuracoesSession() {

        Properties props = new Properties();

        props.put("mail.transport.protocol", env.strEMAIL_PROTOCOL);
        props.put("mail.smtp.host", env.strEMAIL_SMTP_HOST);
        props.put("mail.smtp.port", env.strEMAIL_PORT);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        env.strEMAIL_FROM,
                        env.strEMAIL_PASSWORD);
            }

        });

        session.setDebug(true);
        return session;

    }

    public void sendEmail() {

        try {

            Message message = new MimeMessage(configuracoesSession());
            message.setFrom(new InternetAddress(env.strEMAIL_FROM));

            Address[] toUser = InternetAddress.parse(
                    objMENSSAGE.getStrDestino()
            );

            message.setContent(objMENSSAGE.getStrTextoMensagem(), "text/html");
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(objMENSSAGE.getStrAssunto());
            message.setText(objMENSSAGE.getStrHtmlMensagem());
            message.setReplyTo(toUser);

            Transport transport = configuracoesSession().getTransport(env.strEMAIL_PROTOCOL);

            transport.connect(
                    env.strEMAIL_SMTP_HOST,
                    env.strEMAIL_USERNAME,
                    env.strEMAIL_PASSWORD
            );

            message.saveChanges();

            transport.sendMessage(message, toUser);
            transport.close();

        } catch (MessagingException e) {
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        }
    }

    public ClientEmail(Mensagem objMENSSAGE) throws EmailException {
        this.objMENSSAGE = objMENSSAGE;
    }
}
