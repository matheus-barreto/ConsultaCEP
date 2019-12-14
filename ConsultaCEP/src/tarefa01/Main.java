package tarefa01;

import java.util.Scanner;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        int cep = 0;
        String entradaCep;
        boolean erroEntrada;

        do {

            System.out.println("Digite o seu CEP:");
            Scanner leia = new Scanner(System.in);
            entradaCep = leia.nextLine(); //Recebe String contendo o CEP

            try {

                cep = Integer.parseInt(entradaCep); // Converte de String para Inteiro

            } catch (NumberFormatException ex) {//Se houver erro na converção: o CEP assume valor negativo

                cep = -1;

            }

            if (cep > 99999999 || cep < 01000000 || entradaCep.length() != 8) {
            //Testa se valor do cep está entre a faixa válida e se contem 8 digitos

                System.out.println("O cep deve conter apenas 8 números e sem hífen ou espaços em branco.");
                erroEntrada = true;

            } else {

                erroEntrada = false;

            }

        } while (erroEntrada);

        String jsonCep = new ChamarAPI().conectarAPI("https://viacep.com.br/ws/" + entradaCep + "/json/");
        //Chama a função que conecta com a API do CEP, passando a URL de a se contatar
        if (!"ERRO".equals(jsonCep)) { //Resposta da API foi diferente de 200, houve erro de comunicação

            if (jsonCep.contains("erro")) {//Houve erro no json de resposta da API do CEP

                System.out.println("O CEP " + cep + " tem um formato válido, porém não possue um endereço associado ao mesmo.");

            } else {

                JSONObject objCep = new JSONObject(jsonCep); //Cria Objeto JSON para facilitar operações
                
                System.out.println("Cep: " + objCep.getString("cep"));
                System.out.println("Logradouro: " + objCep.getString("logradouro"));
                System.out.println("Bairro: " + objCep.getString("bairro")); //Impressão na tela de alguns campos de resposta
                System.out.println("Cidade: " + objCep.getString("localidade"));
                System.out.println("Estado: " + objCep.getString("uf"));
               
            }
        } else {

            System.out.println("O CEP não pode ser validado, verifique se o CEP foi informado corretamente e tente novamente.");

        }

    }
}
