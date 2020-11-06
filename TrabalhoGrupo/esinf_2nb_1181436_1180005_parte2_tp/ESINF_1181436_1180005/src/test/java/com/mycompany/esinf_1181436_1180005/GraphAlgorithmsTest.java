package com.mycompany.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphAlgorithmsTest {

    Graph<Pais, Fronteira> mapa = new Graph<>(false);

    public GraphAlgorithmsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        CarregarDados cd = new CarregarDados();
        cd.carregarPaises(mapa);
        cd.carregarFronteiras(mapa);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of caminhoMaisCurtoComIntermedios method, of class GraphAlgorithms.
     */
    @Test
    public void testCaminhoMaisCurtoComIntermedios() {
        System.out.println("caminhoMaisCurtoComIntermedios");
        double expResult = 4651;
        
        //Capital Origem
        String capital1 = "paris";
        //Capital Destino
        String capital2 = "moscovo";

        Pais p1 = Utils.getPaisByCapital(mapa, capital1);
        Pais p2 = Utils.getPaisByCapital(mapa, capital2);

        //Capitais a ter no caminho
        ArrayList<String> capitais = new ArrayList<>();
        capitais.add("madrid");

        //Converter capitais em países
        ArrayList<Pais> paises = new ArrayList<>();
        for (String cap : capitais) {
            Pais p = Utils.getPaisByCapital(mapa, cap);
            paises.add(p);
        }
        ArrayList<Pais> path = new ArrayList<>();
        double result = GraphAlgorithms.caminhoMaisCurtoComIntermedios(mapa, p1, p2, paises, path);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("shortestPath");
        double expResult = 4101;
        LinkedList<Pais> caminho = new LinkedList<>();

        //Capital Origem
        String capital1 = "lisboa";
        //Capital Destino
        String capital2 = "moscovo";

        Pais pais1 = Utils.getPaisByCapital(mapa, capital1);
        Pais pais2 = Utils.getPaisByCapital(mapa, capital2);
        double result = GraphAlgorithms.shortestPath(mapa, pais1, pais2, caminho);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of colorMap method, of class GraphAlgorithms.
     */
    @Test
    public <V> void testColorMap() {
        System.out.println("ColorMap");
        Map<Pais, Integer> result = GraphAlgorithms.colorMap(mapa);
        ArrayList<Integer> listaCores = new ArrayList<>();
        for(Pais p : mapa.allkeyVerts()){
            int corP = result.get(p);
            if(!listaCores.contains(corP)){
                listaCores.add(corP);
            }
            for(Pais pAdj : mapa.adjVertices(p)){
                int corAdj = result.get(pAdj);
                if(corP == corAdj){
                    Assert.fail("Países vizinhos com a mesma cor");
                }
            }
        }
    }

    /**
     * Test of nearestNeighborCycle method, of class GraphAlgorithms.
     */
    @Test
    public void testNearestNeighborCycle() {
        System.out.println("nearestNeighborCycle");
	// verify that argentina -> paraguai does not creates a loop
	Pais pais1 = Utils.getPaisByCapital(mapa, "brasilia");
	Pais pais2 = Utils.getPaisByCapital(mapa, "assuncao");
	Pais pais3 = Utils.getPaisByCapital(mapa, "buenosaires");
	Pais pais4 = Utils.getPaisByCapital(mapa, "montevideu");
        LinkedList<Pais> expResult = new LinkedList<Pais>(){{
		addLast(pais1);
		addLast(pais2);
		addLast(pais3);
		addLast(pais4);
		addLast(pais1);
	}};
        LinkedList<Pais> result = GraphAlgorithms.nearestNeighborCycle(pais1, mapa);
        assertEquals(expResult, result);
    }

    /**
     * Test of DSatur method, of class GraphAlgorithms.
     */
    @Test
    public void testDSatur() {
        System.out.println("DSatur");
        int[] result = GraphAlgorithms.DSatur(mapa);
        ArrayList<Integer> listaCores = new ArrayList<>();
        for(Pais p : mapa.allkeyVerts()){
            int corP = result[mapa.getKey(p)];
            if(!listaCores.contains(corP)){
                listaCores.add(corP);
            }
            for(Pais pAdj : mapa.adjVertices(p)){
                int corAdj = result[mapa.getKey(pAdj)];
                if(corP == corAdj){
                    Assert.fail("Países vizinhos com a mesma cor");
                }
            }
        }
        
        if(listaCores.size() > 4){
            Assert.fail("Foram utilizadas mais de 4 cores");
        }
    }
    
     /**
     * Test of WelshPowell method, of class GraphAlgorithms.
     */
    @Test
    public void testWelshPowell() {
        System.out.println("WelshPowell");
        int[] result = GraphAlgorithms.DSatur(mapa);
        ArrayList<Integer> listaCores = new ArrayList<>();
        for(Pais p : mapa.allkeyVerts()){
            int corP = result[mapa.getKey(p)];
            if(!listaCores.contains(corP)){
                listaCores.add(corP);
            }
            for(Pais pAdj : mapa.adjVertices(p)){
                int corAdj = result[mapa.getKey(pAdj)];
                if(corP == corAdj){
                    Assert.fail("Países vizinhos com a mesma cor");
                }
            }
        }
    }

}
