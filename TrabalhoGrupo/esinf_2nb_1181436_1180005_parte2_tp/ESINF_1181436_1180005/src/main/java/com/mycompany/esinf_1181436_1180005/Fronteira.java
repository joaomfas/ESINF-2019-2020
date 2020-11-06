package com.mycompany.esinf_1181436_1180005;


public class Fronteira {
    Pais pOrigem;
    Pais pDestino;
    double distancia;

    public Fronteira(Pais pOrigem, Pais pDestino, double distancia) {
        this.pOrigem = pOrigem;
        this.pDestino = pDestino;
        this.distancia = distancia;
    }

    public Pais getpOrigem() {
        return pOrigem;
    }

    public Pais getpDestino() {
        return pDestino;
    }

    public double getDistancia() {
        return distancia;
    }
    
    
}
