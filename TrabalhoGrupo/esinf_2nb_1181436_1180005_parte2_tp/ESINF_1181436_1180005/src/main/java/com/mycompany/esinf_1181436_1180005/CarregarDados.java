package com.mycompany.esinf_1181436_1180005;

import java.io.InputStream;
import java.util.*;

public class CarregarDados {

    /**
     * Cria uma instância de CarregarDados
     */
    public CarregarDados() {
    }

    /**
     *
     * Carregamento dos países contidos em ficheiro para um Grafo
     *
     * @param mapa Grafo onde os países serão inseridos
     */
    public void carregarPaises(Graph mapa) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("paises.txt");
        Scanner scan = new Scanner(is);
        while (scan.hasNextLine()) {
            String[] dados = scan.nextLine().trim().split(",");
            dados[1] = dados[1].trim();
            Pais pais = new Pais(dados[0].trim(), dados[1], Double.parseDouble(dados[2].trim()),
                    dados[3].trim(), Double.parseDouble(dados[4].trim()), Double.parseDouble(dados[5].trim()));
            mapa.insertVertex(pais);
        }
        scan.close();
    }

    /**
     *
     * Carregamento das fronteiras para um Grafo
     *
     * @param mapa Grafo com os países e suas fronteiras
     */
    public void carregarFronteiras(Graph mapa) {
        for (Object p : mapa.allkeyVerts()) {
            Pais pais = (Pais) p;

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("fronteiras.txt");
            Scanner scan = new Scanner(is);

            while (scan.hasNextLine()) {
                String[] dados = scan.nextLine().trim().split(",");
                dados[0] = dados[0].trim();
                dados[1] = dados[1].trim();
                if (pais.getNome().equals(dados[0]) || pais.getNome().equals(dados[1])) {
                    Pais p1 = getPaisByName(dados[0], mapa);
                    Pais p2 = getPaisByName(dados[1], mapa);
                    
                    double dist = getDistancia(p1, p2);
                    
                    Fronteira fronteira = new Fronteira(p1, p2, dist);
                    mapa.insertEdge(fronteira.getpOrigem(), fronteira.getpDestino(), fronteira, fronteira.getDistancia());
                }
            }
            scan.close();
        }
    }

    /**
     *
     * Obtém a instância de país através do seu nome
     *
     * @param name Nome do país
     * @param mapa Grafo com os paises
     * @return Instância de país com o nome
     */
    private Pais getPaisByName(String name, Graph mapa) {
        for (Object p : mapa.allkeyVerts()) {
            Pais pais = (Pais) p;
            if (pais.getNome().equals(name)) {
                return pais;
            }
        }
        return null;
    }
    
    private double getDistancia(Pais p1, Pais p2){
        double lat1 = p1.getLatitude();
        double lon1 = p1.getLongitude();
        double lat2 = p2.getLatitude();
        double lon2 = p2.getLongitude();
        // shortest distance over the earth's surface
        // https://www.movable-type.co.uk/scripts/latlong.html
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + 
                   Math.cos(theta1) * Math.cos(theta2) * 
                   Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = (R * c)/1000; // distância em kilometros
        return d;
    }
}
