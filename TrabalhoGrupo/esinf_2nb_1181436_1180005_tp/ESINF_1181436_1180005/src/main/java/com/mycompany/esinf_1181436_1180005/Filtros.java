package com.mycompany.esinf_1181436_1180005;

import java.util.*;

/**
 * Metodos estáticos para solução dos exercícios
 */
public class Filtros {

    /**
     * Exercicio 2 Método devolve uma lista com os países de um continente com
     * populacao maior o fornecido como parâmetro
     *
     * @param paises Lista de países
     * @param continente Continente a que pertencem os países
     * @param populacao População
     * @return Países de um determinado continente com uma população maior do
     * que a inserida
     */
    public static List<Pais> filtroContinentePopulacao(List<Pais> paises, Continente continente, int populacao) {
        // set de países com interface SortedSet. Países adicionados são ordenados por
        // população
        SortedSet<Pais> setPaises = new TreeSet<Pais>(new Comparator<Pais>() {
            @Override
            public int compare(Pais p1, Pais p2) {
                return Double.compare(p1.getPopulacao(), p2.getPopulacao());
            }
        });
        // filtro dos continentes e população
        Iterator<Pais> it = paises.iterator();
        while (it.hasNext()) {
            Pais pais = it.next();
            if (pais.getContinente().equals(continente) && (pais.getPopulacao() >= populacao)) {
                setPaises.add(pais);
            }
        }
        // conversão de SortedSet para arraylist
        return new ArrayList<Pais>(setPaises);
    }

    /**
     * Exercício 3 Devolve, para um continente, um mapa ordenado cuja chave é
     * num número de fronteiras e o valor é um set de países com o número de
     * fronteiras descrito pela chave
     *
     * @param paisesFronteiras Estrutura de dados com as fronteiras de cada país
     * @param continente Continente a que os países pertencem
     * @return Estrutura de dados com os países e o seu número de fronteiras
     */
    public static SortedMap<Integer, Set<Pais>> filtroNumeroPaisesFronteira(Map<Pais, Set<Pais>> paisesFronteiras,
            Continente continente) {
        SortedMap<Integer, Set<Pais>> map = new TreeMap<>(Collections.reverseOrder());
        paisesFronteiras.forEach((pais, setFronteiras) -> {
            if (continente.equals(pais.getContinente())) {
                int nFronteiras = setFronteiras.size();
                if (!map.containsKey(nFronteiras)) {
                    map.put(nFronteiras, new HashSet<Pais>());
                }
                map.get(nFronteiras).add(pais);
            }
        });
        return map;
    }

}
