package dev.mbkaue.comunicador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author kauebarbosa
 */
public class ClientSMS {

    private final Mensagem objMensagem;
    private final String strDESTINO;
    private BeanCredentials env = new BeanCredentials();

    public ClientSMS(Mensagem objMensagem, String strDestino) {

        this.objMensagem = objMensagem;
        this.strDESTINO = strDestino;
        statusAPI(strDestino);

    }

    private String strAuth() {

        String strURL = "http://api.facilitamovel.com.br/api/simpleSend.ft?";
        return strURL + "user=" + env.strSMS_LOGIN + "&password=" + env.strSMS_PASSWORD + "&";

    }

    private String addMensagem() throws UnsupportedEncodingException {

        String strTexto = URLEncoder.encode(objMensagem.getStrTextoMensagem(), "UTF-8");
        return strAuth() + "destinatario=" + strDESTINO + "&msg=" + strTexto;

    }

    public void sendSMS() throws UnsupportedEncodingException {
        requisitaAPI(addMensagem());
    }

    private void consultaSaldo() {
        requisitaAPI("http://api.facilitamovel.com.br/api/checkCreditExpires.ft?user=" + env.strSMS_LOGIN + "&password=" + env.strSMS_PASSWORD);
    }

    private void statusAPI(String strDestinoDoConstrutor) {

        System.out.println(
                "Destinatario\t\tMensagem\n"
                + strDestinoDoConstrutor + "\t\t" + objMensagem.toString() + "\n"
        );

        System.out.println("Saldo:");
        consultaSaldo();

    }

    private void requisitaAPI(String strURL) {

        try {
            String url = strURL;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine = br.readLine();

                System.out.println(
                        inputLine
                        + "\nRequisição OK"
                );

            } else {
                System.out.println("erro " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Erro de leitura\n" + e);
        }

    }
}
