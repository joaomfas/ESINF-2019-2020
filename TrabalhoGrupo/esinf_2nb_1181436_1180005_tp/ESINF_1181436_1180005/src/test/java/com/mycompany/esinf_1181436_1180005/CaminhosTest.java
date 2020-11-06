/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_1181436_1180005;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author joaoferreira
 */
public class CaminhosTest {

    private List<Pais> listaPaises = new ArrayList<>();
    private Map<Pais, Set<Pais>> paisesFronteiras = new HashMap<>();
    Pais origem = null;
    Pais destino = null;

    public CaminhosTest() {
        CarregarDados carrDados = new CarregarDados();

        carrDados.carregarPaises(listaPaises);
        carrDados.carregarFronteiras(listaPaises, paisesFronteiras);

        String origemStr = "espanha";
        String destinoStr = "russia";
        for (Pais p : listaPaises) {
            if (origemStr.equals(p.getNome())) {
                origem = p;
            } else if (destinoStr.equals(p.getNome())) {
                destino = p;
            }
        }
    }

    /**
     * Test of caminhoMaisCurto method, of class Caminhos.
     */
    @Test
    public void testCaminhoMaisCurto() {
        System.out.println("caminhoMaisCurto");
        int expResult = 5;
        int result = Caminhos.caminhoMaisCurto(origem, destino, paisesFronteiras);
        assertEquals(expResult, result);
    }

    /**
     * Test of caminhoMaisCurto_v2 method, of class Caminhos.
     */
    @Test
    public void testCaminhoMaisCurto_v2() {
        System.out.println("caminhoMaisCurto_v2");
        int expResult = 5;
        int result = Caminhos.caminhoMaisCurto_v2(origem, destino, paisesFronteiras);
        assertEquals(expResult, result);
    }

}
