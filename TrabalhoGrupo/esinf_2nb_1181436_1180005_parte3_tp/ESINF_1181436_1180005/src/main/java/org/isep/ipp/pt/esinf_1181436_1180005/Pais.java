package org.isep.ipp.pt.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.Objects;

public class Pais implements Comparable<Pais> {

    private String nome;
    private String continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;
    private ArrayList<Pais> fronteiras;
    private int numFronteiras;

    /**
     *
     * Constrói uma instância de País com todos os seus atributos
     *
     * @param nome Nome do país
     * @param continente Continente onde o país se encontra
     * @param populacao População do país
     * @param capital Capital do país
     * @param latitude Latitude do país
     * @param longitude Longitude do país
     */
    public Pais(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fronteiras = new ArrayList<>();
    }

    /**
     *
     * Retorna o nome do país
     *
     * @return Nome do país
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * Retorna o continente a que o país pertence
     *
     * @return Continente a que o país pertence
     */
    public String getContinente() {
        return continente;
    }

    /**
     *
     * Retorna a população do país
     *
     * @return População do país
     */
    public double getPopulacao() {
        return populacao;
    }

    /**
     *
     * Retorna a capital do país
     *
     * @return Caputal do país
     */
    public String getCapital() {
        return capital;
    }

    /**
     *
     * Retorna a coordenada latitude do país
     *
     * @return Latitude do país
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * Retorna a longitude do país
     *
     * @return Longitude do país
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * Permite alterar o nome do país
     *
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * Permite alterar o continente do país
     *
     * @param continente Novo continete
     */
    public void setContinente(String continente) {
        this.continente = continente;
    }

    /**
     *
     * Permite modificar a população do país
     *
     * @param populacao Nova população
     */
    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    /**
     *
     * Permite modificar a capital do país
     *
     * @param capital Nova capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     *
     * Permite modificar a latitude do país
     *
     * @param latitude Nova latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * Permite modificar a longitude do país
     *
     * @param longitude Nova longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * Permite adicionar uma fronteira
     *
     * @param fronteira Nova fronteira
     */
    public boolean addFronteira(Pais fronteira) {
        numFronteiras++;
        return fronteiras.add(fronteira);
    }

    /**
     *
     * Retorna o número de fronteiras do país
     *
     * @return Número de fronteiras
     */
    public int getNumFronteiras() {
        return numFronteiras;
    }

    /**
     *
     * Verifica se um objeto recebido por parâmetro é igual à instância de país
     *
     * @param obj Objeto a comparar
     * @return True se os objetos forem iguais. False se não forem.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pais other = (Pais) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    public ArrayList<Pais> getFronteiras() {
        return fronteiras;
    }

    /**
     *
     * Retorna uma string com os atributos do país
     *
     * @return string com os atributos do país
     */
    @Override
    public String toString() {
        return nome;
        // return "Pais: "  + nome +
        //         "\nContinente: " + continente +
        //         "\nPopulacao: " + populacao +
        //         "\nCapital: " + capital +
        //         "\nLatitude: " + latitude +
        //         "\nLongitude: " + longitude;
    }

    /**
     *
     * Implementação do método compareTo. Compara duas instâncias de país por
     * ordem alfabética.
     *
     * @param o Instância de país a comparar
     * @return Retorna
     */
    @Override
    public int compareTo(Pais o) {
        return this.nome.compareTo(o.getNome());
    }
    
    
    public double getDistancia(double lat, double lon) {
        double lat1 = this.latitude;
        double lon1 = this.longitude;
        double lat2 = lat;
        double lon2 = lon;
        // shortest distance over the earth's surface
        // https://www.movable-type.co.uk/scripts/latlong.html
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2)
                + Math.cos(theta1) * Math.cos(theta2)
                * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = (R * c) / 1000; // distância em kilometros
        return d;
    }
    
    public double squaredDist(double lat, double lon) {
	    return Math.pow(latitude - lat, 2) + Math.pow(longitude - lon, 2);
    }
}
