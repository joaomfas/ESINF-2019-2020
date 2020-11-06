package org.isep.ipp.pt.esinf_1181436_1180005;

import java.io.InputStream;
import java.util.*;

public class CarregarDados {

    /**
     *
     * Carregamento dos países contidos em ficheiro para um Grafo
     *
     * @return Retorna um ArrayList com as instâncias de País carregadas
     */
    public ArrayList<Pais> carregarPaises() {
        ArrayList<Pais> listaPaises = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        InputStream is = classloader.getResourceAsStream("paises.txt");
        try (Scanner scan = new Scanner(is)) {
            while (scan.hasNextLine()) {
                String[] dados = scan.nextLine().trim().split(",");
                dados[1] = dados[1].trim();
                Pais pais = new Pais(dados[0].trim(), dados[1], Double.parseDouble(dados[2].trim()),
                        dados[3].trim(), Double.parseDouble(dados[4].trim()), Double.parseDouble(dados[5].trim()));
                listaPaises.add(pais);
            }
        }
        return listaPaises;
    }

    /**
     *
     * Carregamento das fronteiras
     *
     * @param listaPaises ArrayList com as instâncias de país
     */
    public void carregarFronteiras(ArrayList<Pais> listaPaises) {
        for (Pais pais : listaPaises) {

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("fronteiras.txt");
            Scanner scan = new Scanner(is);

            while (scan.hasNextLine()) {
                String[] dados = scan.nextLine().trim().split(",");
                dados[0] = dados[0].trim();
                dados[1] = dados[1].trim();
                if (pais.getNome().equals(dados[0]) || pais.getNome().equals(dados[1])) {
                    Pais p1 = getPaisByName(dados[0], listaPaises);
                    Pais p2 = getPaisByName(dados[1], listaPaises);

                    if (p1.equals(pais)) {
                        pais.addFronteira(p2);
                    }
                    if (p2.equals(pais)) {
                        pais.addFronteira(p1);
                    }
                }
            }
            scan.close();
        }
    }

    /**
     *
     * Retorna uma instância de país com um determinado nome
     *
     * @param nomePais Nome do país
     * @param listaPaises Lista de países carregados
     * @return Instância de país com o nome
     */
    private Pais getPaisByName(String nomePais, ArrayList<Pais> listaPaises) {
        for (Pais p : listaPaises) {
            if (p.getNome().equals(nomePais)) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * Retorna um array list com as instâncias de país que pertencem a um
     * determinado continente
     *
     * @param continente Continente
     * @param listaPaises Lista de países
     * @return array list com as instâncias de país que pertencem a um
     * determinado continente
     */
    public ArrayList<Pais> getPaisesByContinente(String continente, ArrayList<Pais> listaPaises) {
        ArrayList<Pais> paisesContinente = new ArrayList<>();
        for (Pais p : listaPaises) {
            if (p.getContinente().equalsIgnoreCase(continente)) {
                paisesContinente.add(p);
            }
        }
        return paisesContinente;
    }
}
