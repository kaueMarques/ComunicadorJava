package dev.mbkaue.comunicador;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author kaueMarques
 */


public class Sender {

    private final Menssagem objMENSAGEM;

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

    public Sender(Menssagem objMENSAGEM) {
        this.objMENSAGEM = objMENSAGEM;
    }

}
