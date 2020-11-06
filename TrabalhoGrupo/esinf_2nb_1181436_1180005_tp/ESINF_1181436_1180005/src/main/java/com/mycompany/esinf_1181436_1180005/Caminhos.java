package com.mycompany.esinf_1181436_1180005;

import java.util.*;

public class Caminhos {

    /**
     *
     * Recebe a estrutura de dados com as fronteiras de cada país assim como o
     * país de origem e destino. Instância a Priority Queue e define o seu
     * Comparator. Inicia a estrutura de dados com as distâncias.
     *
     * @param origem País origem
     * @param destino País destino
     * @param paisesFronteiras Map Países/Fronteiras
     * @return número mínimo de fronteiras a passar para se deslocar entre dois
     * países
     */
    public static int caminhoMaisCurto(Pais origem, Pais destino, Map<Pais, Set<Pais>> paisesFronteiras) {

        Set<Pais> lista = new HashSet<Pais>(paisesFronteiras.keySet());
        Map<Pais, Integer> distancias = new HashMap<>();

        PriorityQueue<Pais> listaOrdenada = new PriorityQueue<>(new Comparator<Pais>() {
            @Override
            public int compare(Pais p1, Pais p2) {
                // alternativa:
                // return Double.compare(distancias.get(p1), distancias.get(p2)) ;
                if (distancias.get(p1) > distancias.get(p2)) {
                    return 1;
                } else if (distancias.get(p1) < distancias.get(p2)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (Pais pais : lista) {
            if (pais.equals(origem)) {
                distancias.put(pais, 0);
            } else {
                distancias.put(pais, Integer.MAX_VALUE);
            }
            listaOrdenada.add(pais);
        }

        return algDijkstra(destino, listaOrdenada.poll(), distancias, listaOrdenada, paisesFronteiras);
    }

    /**
     *
     * Algoritmo de Dijkstra para a determinação do caminho mais curto entre
     * dois países.
     *
     * @param paisDestino País destino
     * @param paisAtual País onde o algoritmo se encontra
     * @param distancias Estrutura de dados com as distâncias
     * @param listaOrdenada Fila prioritária com os países em análise
     * @param paisesFronteiras Estrutura de dados com as fronteiras de cada país
     * @return Distância mínima entre dois países
     */
    public static int algDijkstra(Pais paisDestino, Pais paisAtual, Map<Pais, Integer> distancias,
            PriorityQueue<Pais> listaOrdenada, Map<Pais, Set<Pais>> paisesFronteiras) {
        if (paisAtual.equals(paisDestino)) {
            if (distancias.get(paisDestino) == Integer.MAX_VALUE) {
                return -1;
            } else {
                return distancias.get(paisDestino);
            }
        } else {
            Set<Pais> paisesVizinhos = paisesFronteiras.get(paisAtual);
            for (Pais p : paisesVizinhos) {
                if ((distancias.get(p) > (distancias.get(paisAtual) + 1)) && ((distancias.get(paisAtual) != Integer.MAX_VALUE))) {
                    listaOrdenada.remove(p);
                    int temp = (distancias.get(paisAtual) + 1);
                    distancias.put(p, temp);
                    listaOrdenada.add(p);
                }
            }
            Pais paisSeguinte = (Pais) listaOrdenada.poll();
            return algDijkstra(paisDestino, (Pais) paisSeguinte, distancias, listaOrdenada, paisesFronteiras);
        }
    }

    /**
     * versão do algoritmo de caminho mais curto sem recursividade cada nó é
     * relacionado com uma distancia até a origem (distancias) adiciona-se um nó
     * de origem à uma lista prioritária (baseada em Heap) - prioriza menor
     * distância da origem enquanto há elementos na lista, compara-se os
     * vizinhos do nó no topo da lista e atualizam-se as distâncias. o vizinho
     * com menor distância é adicionado à lista de prioridades.
     *
     * @param origem País origem
     * @param destino País destino
     * @param paisesFronteiras Estrutura de dados com as fronteiras de cada país
     * @return Distância entre os países
     */
    public static int caminhoMaisCurto_v2(Pais origem, Pais destino, Map<Pais, Set<Pais>> paisesFronteiras) {
        Map<Pais, Integer> distancias = new HashMap<>();
        for (Pais pais : paisesFronteiras.keySet()) {
            distancias.put(pais, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        PriorityQueue<Pais> listaPrioritara = new PriorityQueue<>(new Comparator<Pais>() {
            @Override
            public int compare(Pais p1, Pais p2) {
                return Integer.compare(distancias.get(p1), distancias.get(p2));
            }
        });
        listaPrioritara.add(origem);
        while (!listaPrioritara.isEmpty()) {
            Pais pais = listaPrioritara.poll();
            Set<Pais> vizinhos = paisesFronteiras.get(pais);
            for (Pais vizinho : vizinhos) {
                Integer peso = 1; // distancia entre países tem peso sempre 1
                if (distancias.get(vizinho) > distancias.get(pais) + peso) {
                    distancias.put(vizinho, distancias.get(pais) + peso);
                    listaPrioritara.add(vizinho);
                }
            }
        }
        if (distancias.get(destino) == Integer.MAX_VALUE) {
            return -1;
        }
        return (distancias.get(destino));
    }
}
