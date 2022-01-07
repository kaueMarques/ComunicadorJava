package dev.mbkaue.comunicador;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author kauebarbosa
 */


public class Sender {

    private final Mensagem objMENSAGEM;

    public void email() throws EmailException, IOException {
        ClientEmail clienteEmail = new ClientEmail(objMENSAGEM);
        clienteEmail.sendEmail();
    }

    public void sms() throws UnsupportedEncodingException {
        ClientSMS clienteSMS = new ClientSMS(
                objMENSAGEM,
                objMENSAGEM.getStrDestino());
        clienteSMS.sendSMS();
    }

    public Sender(Mensagem objMENSAGEM) {
        this.objMENSAGEM = objMENSAGEM;
    }

}
