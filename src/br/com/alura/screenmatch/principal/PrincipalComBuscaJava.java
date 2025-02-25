package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;

public class PrincipalComBuscaJava {
    public static void main(String[] args) throws IOException, InterruptedException{

        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite nome do filme para buscar: ");
        var busca = leitura.nextLine();
        
        String chave = "http://www.omdbapi.com/?t=matrix&apikey=3343c4a4";
        String endereco = "http://www.omdbapi.com/?t=" + busca + "&apikey=3343c4a4";

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create(endereco))
         .build();
    
         HttpResponse<String> response = client
     .send(request, HttpResponse.BodyHandlers.ofString());

     
     String json = response.body();

     Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
    
    TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
    System.out.println(meuTituloOmdb);
    
    Titulo meuTitulo = new Titulo(meuTituloOmdb);
    System.out.println("titulo convertido");
    System.out.println(meuTitulo);

    }
}
