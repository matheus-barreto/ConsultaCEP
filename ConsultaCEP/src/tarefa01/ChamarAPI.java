package tarefa01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author matheusbarreto
 */
public class ChamarAPI {

    public String conectarAPI(String url) {

        try {

            StringBuilder buffer = new StringBuilder();
            String resposta;
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection(); //Abre conexão com a URL

            int responseCode = conn.getResponseCode(); //Recebe o código de resposta

            if (responseCode == 200) {// Conexão foi bem sucedida.

                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        //Cria um buffer para receber os dados da resposta da solicitação
                    while ((resposta = in.readLine()) != null) {//Enquanto o buffer tiver linhas
                        
                        buffer.append(resposta); //Concatena as linhas

                    }
                }

                return buffer.toString();//Retorna um String de resposta

            } else {

                return "ERRO"; //Retorna quando a conexão não é bem sucedida

            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
