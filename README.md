<div align="center" class="Header">

 		
<h1>Comunicador Java</h1>
	

<p align="center"> <i> Pacote para facilitar envio de SMS e E-MAIL</i> </p>
	
![java](https://img.shields.io/badge/java-red.svg?style=for-the-badge&logo=java&logoColor=white)
![java](https://img.shields.io/badge/maven-purple.svg?style=for-the-badge&logo=apache&logoColor=white)
 

</div>



<div align="left" class="FullDescription">

 		
<h1>📖 Descrição</h1>
		
<p>
Pacote criado para facilitar o envio de Menssagens se utilizando de plataformas como, Facilita Móvel e também o SMTP da Locaweb, mas também é possível usar em outros servios de email.
</p>
 
<br>

<p>
Depreciado, hoje toda a tecnologia foi atualizada para microservico, está página está servindo como meu portfólio de documentação de projeto. 
</p>
 
</div>


<br>

<div align="left" class="Implementacao">

<h1>🧰 Implementacao</h1>

<p>
Para começar a usar, vá para a classe <b>BeanCredentials.java</b> e coloque suas credenciais nas variáveis, assim como o exemplo abaixo: 
</p>

```java
//Exemplo

package dev.mbkaue.comunicador;

import lombok.Getter;

/**
 *
 * @author kaueMarques
 */
@Getter
public class BeanCredentials {

//  Email  
    protected final String strEMAIL_FROM = "email@provedor.com";
    protected final String strEMAIL_USERNAME = "usuario_email@provedor.com";
    protected final String strEMAIL_PASSWORD = "SuaSenha";
    protected final String strEMAIL_SMTP_HOST = "seuhost.com.br";
    protected final String strEMAIL_PORT = "0000";
    protected final String strEMAIL_PROTOCOL = "0000";

//  SMS
    protected final String strSMS_LOGIN = "LoginFacilitaMovelLogin";
    protected final String strSMS_PASSWORD = "FacilitaMovelSenha";

}

```

<h2>Para enviar Email</h2>

```java
//Exemplo

package dev.mbkaue.comunicador;

import java.io.IOException;
import org.apache.commons.mail.EmailException;

public class Implementacao {
    
    public static void main(String[] args) throws IOException, EmailException {
        
        Menssagem objMenssagemEmail = new Menssagem(
                "Seu Texto", 
                "seuDetino@provedor.com", 
                "Nome da pessoa", 
                "Assunto do email");
        
        Sender enviarEmail = new Sender(objMenssagemEmail);
        enviarEmail.email();
                
    }
}
```

<h3>Customização de template de email</h3>
<span>Para customizar o email é apenas nescessario mudar o arquivo <b>menssagem.html</b> que está na pasta <b>files</b>.
<br>
Foi configurado apenas duas variaveis no arquivo, caso queria colocar mais, faça a modificação na classe <b> Menssagem.java</b>
</span>


<br>
<h2>Para enviar SMS</h2>
<span><b>Observação: </b>Para o envio de SMS é necessário ter créditos no Facilita Móvel</span>

<br>

```java
//Exemplo

package dev.mbkaue.comunicador;

import java.io.IOException;

public class Implementacao {

    public static void main(String[] args) throws IOException {
        
        Menssagem objMenssagemSMS = new Menssagem(
                "Seu Texto", //Limite de até 160 caracteres no texto de sms
                "12991000000"); 

        Sender enviarSMS = new Sender(objMenssagemSMS);
        enviarSMS.sms();
                    
    }
}
```

</div>
