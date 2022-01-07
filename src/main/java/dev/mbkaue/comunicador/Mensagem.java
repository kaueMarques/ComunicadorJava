package dev.mbkaue.comunicador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;

/**
 *
 * @author kauebarbosa
 */
@Data
public class Mensagem {

    private String strNome;
    private String strTextoMensagem;
    private String strAssunto;
    private String strDestino;

    public Mensagem(String strTextoMensagem, String strDestino) {
        this.strTextoMensagem = strTextoMensagem;
        this.strDestino = strDestino;
    }

    public Mensagem(String strTextoMensagem, String strDestino, String strNome, String strAssunto) throws IOException {
        this.strNome = strNome;
        this.strTextoMensagem = strTextoMensagem;
        this.strAssunto = strAssunto;
        this.strDestino = strDestino;

    }

    public String getStrHtmlMensagem() {
        
        String strPureHTML = "";
        Path strFilePath = Paths.get("files/menssagem.html");

        try (Stream<String> streamHTML = Files.lines(strFilePath, StandardCharsets.UTF_8)) {

            List listDataHTML = streamHTML
                    .collect(Collectors.toCollection(ArrayList::new));

            for (Object line : listDataHTML) {
                strPureHTML += line;
            }

            strPureHTML = strPureHTML.replace("{nome}", this.strNome);
            strPureHTML = strPureHTML.replace("{menssagem}", this.strTextoMensagem);
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return strPureHTML;
    }

}
