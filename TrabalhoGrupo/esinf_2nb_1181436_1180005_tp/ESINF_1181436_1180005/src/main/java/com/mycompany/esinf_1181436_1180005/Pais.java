package com.mycompany.esinf_1181436_1180005;

import java.util.Objects;


public class Pais{
    private String nome;
    private Continente continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;

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
    public Pais(String nome, Continente continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public Continente getContinente() {
        return continente;
    }

    public double getPopulacao() {
        return populacao;
    }

    public String getCapital() {
        return capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }  

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

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
}
