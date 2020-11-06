package com.mycompany.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //1. Construir o grafo de países e respetivas fronteiras a partir da 
        //informação fornecida nos ficheiros de texto. A capital de um país tem 
        //ligação direta com as capitais dos países com os quais faz fronteira. 
        //O cálculo da distância em Kms entre duas capitais deverá ser feito 
        //através das suas coordenadas.
        Graph<Pais, Fronteira> mapa = new Graph<>(false);
        CarregarDados cd = new CarregarDados();
        cd.carregarPaises(mapa);
        cd.carregarFronteiras(mapa);

        //2. Colorir o mapa de tal modo que países vizinhos não partilhem a 
        //mesma cor e usando o menor número possível de cores.
//        System.out.println("2.");
//        Map<Pais, Integer> colors = GraphAlgorithms.colorMap(mapa);
//        colors.forEach((p, color) -> {
//            System.out.println("Pais: " + p + "\t- cor: " + color);
//        });
        System.out.println("2.");
        int[] cores = GraphAlgorithms.DSatur(mapa);
        int index = 0;
        for (Pais p : mapa.allkeyVerts()) {
            System.out.println("Pais: " + p.getNome() + "\t\t- cor: " + cores[index]);
            index++;
        }

        //3. Calcular o caminho mais curto entre duas capitais, deve indicar as 
        //capitais incluídas no caminho e a respetiva distância em kms.
        LinkedList<Pais> caminho = new LinkedList<>();

        //Capital Origem
        String capital1 = "paris";
        //Capital Destino
        String capital2 = "moscovo";

        Pais pais1 = Utils.getPaisByCapital(mapa, capital1);
        Pais pais2 = Utils.getPaisByCapital(mapa, capital2);

        double dist = GraphAlgorithms.shortestPath(mapa, pais1, pais2, caminho);
        System.out.println("\n3. Caminho mais curto que liga " + capital1 + " a " + capital2 + ":");
        Pais paisAnterior = null;
        boolean flagFirst = true;
        int distancia = 0;
        for (Pais ps : caminho) {
            if (flagFirst) {
                paisAnterior = ps;
                flagFirst = false;
            } else {
                distancia = (int) mapa.getEdge(ps, paisAnterior).getWeight();
                System.out.println(paisAnterior.getCapital() + "(" + paisAnterior + ")"
                        + " - "
                        + ps.getCapital() + "(" + ps + ")"
                        + " - " + distancia + " km");
                paisAnterior = ps;
            }
        }
        System.out.println("A distância total é de " + (int) dist + " km.\n");

        //4. Calcular o caminho mais curto entre duas capitais passando 
        //obrigatoriamente por outras capitais indicadas.
        //Capital Origem
        capital1 = "paris";
        //Capital Destino
        capital2 = "moscovo";

        Pais p1 = Utils.getPaisByCapital(mapa, capital1);
        Pais p2 = Utils.getPaisByCapital(mapa, capital2);

        //Capitais a ter no caminho
        ArrayList<String> capitais = new ArrayList<>();
        capitais.add("lisboa");
        capitais.add("roma");
        capitais.add("budapeste");

        //Converter capitais em países
        ArrayList<Pais> paises = new ArrayList<>();
        for (String cap : capitais) {
            Pais p = Utils.getPaisByCapital(mapa, cap);
            paises.add(p);
        }

        //Distancia mais curta em vOrig e vDest a passar nos intermédios
        ArrayList<Pais> path = new ArrayList<>();
        double distanciaIntermedios = GraphAlgorithms.caminhoMaisCurtoComIntermedios(mapa, p1, p2, paises, path);
        System.out.println("4. A distancia mais curta entre " + capital1 + " e " + capital2 + " passando por "
                + capitais + " é de " + (int) distanciaIntermedios + " km.\n O caminho é: " + path);

        //5. Determinar o circuito de menor comprimento que parte de uma capital 
        //origem e visita outras capitais uma única vez, voltando à capital 
        //inicial. Utilize a heurística do vizinho mais próximo: a próxima cidade
        //a ser visitada é a mais próxima ainda não visitada. Identifique qual o 
        //número máximo de cidades possíveis de visitar usando esta heurística.
        System.out.println("5.");
        int maxCycle = -1;
        Pais max = null;
        for (Pais p : mapa.allkeyVerts()) {
            List<Pais> cycle = GraphAlgorithms.nearestNeighborCycle(p, mapa);
            if (cycle.size() > maxCycle) {
                maxCycle = cycle.size() - 1;
                max = p;
            }
            System.out.println("País: " + p + " \ncircuito: " + cycle);
        }
        System.out.println("O número máximo de cidades visitadas é " + maxCycle + " e começa em " + max);
    }
}
