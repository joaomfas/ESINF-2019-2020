package com.mycompany.esinf_1181436_1180005;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FiltrosTest {

    public FiltrosTest() {
        
    }
    
        @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    void filtroContinentePopulacao_teste() {
        System.out.println("filtroContinentePopulacao_test");
        Pais p1 = new Pais("", Continente.americanorte, 4, "", 0, 0);
        Pais p2 = new Pais("", Continente.americanorte, 2, "", 0, 0);
        Pais p3 = new Pais("", Continente.americanorte, 3, "", 0, 0);
        Pais p4 = new Pais("", Continente.americanorte, 5, "", 0, 0);
        Pais p5 = new Pais("", Continente.europa, 4, "", 0, 0);
        List<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        paises.add(p3);
        paises.add(p4);
        paises.add(p5);
        List<Pais> result = Filtros.filtroContinentePopulacao(paises, Continente.americanorte, 3);
        List<Pais> expResult = new ArrayList<>();
        expResult.add(p3);
        expResult.add(p1);
        expResult.add(p4);
        assertEquals(expResult, result);
    }

    @Test
    void filtroNumeroPaisesFronteira_teste() {
        System.out.println("filtroNumeroPaisesFronteira_teste");
        Pais p1 = new Pais("p1", Continente.americanorte, 4, "", 0, 0);
        Pais p2 = new Pais("p2", Continente.americanorte, 2, "", 0, 0);
        Pais p3 = new Pais("p3", Continente.americanorte, 3, "", 0, 0);
        Pais p4 = new Pais("p4", Continente.americanorte, 5, "", 0, 0);
        Pais p5 = new Pais("p5", Continente.europa, 4, "", 0, 0);
        Map<Pais, Set<Pais>> paisesFronteiras = new HashMap<>();
        paisesFronteiras.put(p1, new HashSet<>());
        paisesFronteiras.put(p2, new HashSet<>());
        paisesFronteiras.put(p3, new HashSet<>());
        paisesFronteiras.put(p4, new HashSet<>());
        paisesFronteiras.put(p5, new HashSet<>());
        paisesFronteiras.get(p1).add(p5);
        paisesFronteiras.get(p1).add(p2);
        paisesFronteiras.get(p1).add(p3);
        paisesFronteiras.get(p1).add(p4);
        paisesFronteiras.get(p2).add(p1);
        paisesFronteiras.get(p2).add(p2);
        paisesFronteiras.get(p4).add(p5);
        paisesFronteiras.get(p4).add(p1);
        paisesFronteiras.get(p4).add(p3);
        paisesFronteiras.get(p4).add(p2);
        
        // 4 - p4, p1
        // 2 - p2
        // 0 - p3
        SortedMap<Integer, Set<Pais>> expResult = new TreeMap<>();
        Set<Pais> set0 = new HashSet<>();
        Set<Pais> set2 = new HashSet<>();
        Set<Pais> set4 = new HashSet<>();
        set4.add(p4);
        set4.add(p1);
        set2.add(p2);
        set0.add(p3);
        expResult.put(0, set0);
        expResult.put(2, set2);
        expResult.put(4, set4);

        SortedMap<Integer, Set<Pais>> result = Filtros.filtroNumeroPaisesFronteira(paisesFronteiras, Continente.americanorte);
        assertEquals(expResult, result);
    }
}
