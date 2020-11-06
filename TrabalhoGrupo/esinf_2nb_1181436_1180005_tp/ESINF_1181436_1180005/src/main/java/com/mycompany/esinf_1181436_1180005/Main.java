package com.mycompany.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        List<Pais> listaPaises = new ArrayList<>();
        Map<Pais, Set<Pais>> paisesFronteiras = new HashMap<>();

        //1. Carregar e guardar a informação relativa aos países e respetivas 
        //   fronteiras a partir dos ficheiros de texto fornecidos.
        CarregarDados carrDados = new CarregarDados();
        carrDados.carregarPaises(listaPaises);
        carrDados.carregarFronteiras(listaPaises, paisesFronteiras);
        System.out.println("Exercício 1:\nCarregamento de dados: (Pais) - [Fronteiras]");
        for (Pais pais : listaPaises) {
            System.out.print("\t( " + pais.getNome() + " ) :  [ ");
            for (Pais fronteira : paisesFronteiras.get(pais))
                System.out.print(fronteira.getNome() +" " );
            System.out.println("]");
        }


        // 2. Devolver numa lista os países de um continente com mais de N
        //    milhões de habitantes, ordenada por ordem crescente de população,
        //    por exemplo para o continente europa, países com mais de 50 
        //    milhões habitantes:
        Continente continente = Continente.americasul;
        int populacao = 40;
        List<Pais> paises = Filtros.filtroContinentePopulacao(listaPaises, continente, populacao);
        System.out.println("\nExercício 2:\n" + "Países da \"" + continente + "\" com mais de " + populacao + " mil. habitantes:");
        for (Pais pais : paises)
            System.out.println("\t" + pais.getNome() + "\t(pop.:" + pais.getPopulacao() + ")");

        // 3. Devolver, numa estrutura de dados apropriada, para um continente, 
        //    os países agrupados pelo mesmo número de países fronteiras, por 
        //    ordem decrescente do número de fronteiras. Por exemplo, para o
        //    continente europa:
        System.out.println("\nExercício 3:\n" +
                "Países da " + continente + " agrupados pelo mesmo número de fronteiras:\n");
        SortedMap<Integer, Set<Pais>> paisesAgrupados = Filtros.filtroNumeroPaisesFronteira(paisesFronteiras, continente);
       for (Integer i : paisesAgrupados.keySet())
           System.out.println("\t " + i + " -> " + paisesAgrupados.get(i));

        // 4. Apresentar o número mínimo de fronteiras que é necessário
        //    atravessar para chegar de um país origem a um país destino.
        System.out.println("\nExercício 4:");
        Pais origem = carrDados.getPaisByName("espanha", listaPaises);
        Pais destino = carrDados.getPaisByName("russia", listaPaises);
        int dist = Caminhos.caminhoMaisCurto(origem, destino, paisesFronteiras);
        System.out.println("Percurso mais curto entre " + origem.getNome() + " e "
                + destino.getNome() + ": " + dist + " fronteiras");
        dist = Caminhos.caminhoMaisCurto_v2(origem, destino, paisesFronteiras);
        System.out.println("Percurso mais curto " + origem.getNome() + "-"
                + destino.getNome() + ": " + dist + " fronteiras");
    }
}
