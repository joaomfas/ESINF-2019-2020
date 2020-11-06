package org.isep.ipp.pt.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //1. Com recurso à estrutura árvore binária de pesquisa (BST):
        //a. Contruir uma árvore binária de pesquisa que contenha a informação relativa a cada país: nome,
        //continente, população, capital, latitude, longitude, lista de países fronteira e número total de fronteiras. 
        //Forneça um método que, dado um nome de país, devolva a lista das suas fronteiras com outros países.
        Tree_Pais bstTreeA = new Tree_Pais();
        CarregarDados loadData = new CarregarDados();
        ArrayList<Pais> listaPaises = loadData.carregarPaises();
        loadData.carregarFronteiras(listaPaises);
        bstTreeA.addListaPaises(listaPaises);
        String strPais = "espanha";
        Pais p = bstTreeA.getPaisInstance(strPais);
        System.out.println("1.\na)\n" + p.getNome() + " tem como fronteira os seguintes países: " + p.getFronteiras());

        //b. Fornecer um método capaz de devolver uma lista ordenada dos países pertencentes a um determinado continente. 
        //Efectuar ordenação decrescente por número de fronteiras e crescente por valor de população 
        //(i.e.: maior número de fronteiras e menor população).
        
        String strContinente = "europa";
        
        ArrayList<Pais> paisesDeContinente = loadData.getPaisesByContinente(strContinente, listaPaises);
        
        Comparator<Pais> comp = new Comparator<Pais>() {
            @Override
            public int compare(Pais o1, Pais o2) {
                if (o2.getFronteiras().size() > o1.getFronteiras().size()) {
                    return 1;
                } else if (o2.getNumFronteiras() == o1.getNumFronteiras()) {
                    if (o1.getPopulacao() > o2.getPopulacao()) {
                        return 1;
                    } else if (o1.getPopulacao() == o2.getPopulacao()) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        };
       
        Tree_Pais bstTreeB = new Tree_Pais(comp);
        bstTreeB.addListaPaises(paisesDeContinente);
        
        ArrayList<Pais> listaOrdenda = (ArrayList<Pais>) bstTreeB.inOrder();
        System.out.println("b)\nOs países da " + strContinente + " ordenados são: " + listaOrdenda);
        
        for(Pais p1 : listaOrdenda){
            System.out.println(p1.getNome() + "     #front: " + p1.getNumFronteiras() + "   pop: " + p1.getPopulacao());
        }
	
	System.out.println("ARVORE BINARIA");
	    System.out.println(bstTreeA);
	
	
	// EXERCÍCIO 2.
	// a. Construir uma 2d-tree que contenha a informação relativa a cada país: nome, continente, 
	// população, capital, latitude, longitude, lista de países fronteira e número total de fronteiras. 
	// Considere x como latitude e y longitude. 
	Paises2DTree kdtree = new Paises2DTree();
	listaPaises.forEach(pais -> { kdtree.insert(pais); });
	System.out.println("Ex. 2.a) árvore 2d-tree");
	System.out.println(kdtree.toString());
	
	// b. Pesquisa exata: utilizando a 2d-tree da alínea a), fornecer um método que devolva o país 
	// cuja capital está situada nas coordenadas (latitude, longitude). 
	Pais guiana = kdtree.findPais(4.9333300, -52.3333300);
	System.out.println("Ex. 2.b) encontrando guianafrancesa pelas coordenadas 4.9333300, -52.3333300");
	System.out.println(guiana);

	// c. Pesquisa vizinho mais próximo: utilizando a 2d-tree1 da alínea a), fornecer um método que 
	// encontre o país cuja capital está mais próxima das coordenadas (latitude, longitude). 
	// procurando por badajoz
	Pais portugal = kdtree.findNearestPais(38.8794 , -6.9707);
	System.out.println("Ex. 2.c) procurando pais mais próximo pelas coordenadas de badajoz");
	System.out.println(portugal);
	
	// d. Pesquisa por área geográfica: Utilizando a 2d-tree da alínea a), fornecer um método que 
	// devolva a lista de todos os países cuja capital está contida numa área dada por um rectângulo 
	// de coordenadas (latitude1, longitude1) e (latitude2, longitude2). 
	// quadrado com alemanha, belgica, holanda e luxemburgo
	System.out.println("Ex. 2.d quadrado contendo alemanhaa belgica, holanda e luxemburgo");
	List<Pais> lista = kdtree.squareSearch(49, 3, 53, 14);
	System.out.println(lista);
	
    }
}
