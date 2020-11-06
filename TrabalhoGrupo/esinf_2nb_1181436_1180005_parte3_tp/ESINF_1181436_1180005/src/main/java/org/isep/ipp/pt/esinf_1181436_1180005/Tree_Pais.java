package org.isep.ipp.pt.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.Comparator;

public class Tree_Pais extends BST<Pais> {

    /**
     * O comparador inicial utiliza o método compareTo da classe País
     */
    Comparator<Pais> comp = new Comparator<Pais>() {
        @Override
        public int compare(Pais o1, Pais o2) {
            int comp = o1.compareTo(o2);
            return comp;
        }
    };

    /**
     * Construtor sem argumentos
     */
    public Tree_Pais() {
    }

    /**
     *
     * Construtor da instância que aceita um comparador alternativo
     *
     * @param comp Novo comparador para ser usado pelos métodos da classe
     */
    public Tree_Pais(Comparator comp) {
        this.comp = comp;
    }

    /**
     *
     * O método recebe um ArrayList de países e adiciona-os à árvore binária
     *
     * @param listaPaises Lista de países
     */
    public void addListaPaises(ArrayList<Pais> listaPaises) {
        for (Pais p : listaPaises) {
            insert(p);
        }
    }

    /**
     *
     * Recebe uma instância de país e insere-a na árvore
     *
     * @param element Instância de país a ser adicionada
     */
    @Override
    public void insert(Pais element) {
        root = insert(element, root());
    }

    /**
     *
     * Percorre a árvore até encontrar um nó vazio para armazenar a instância de
     * país
     *
     * @param element Instância a adicionar
     * @param node Nó onde fica ligado
     * @return Retorna o nó onde ficou armazenada a instância de país
     */
    private Node<Pais> insert(Pais element, Node<Pais> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (comp.compare(node.getElement(), element) < 0) {
            node.setRight(insert(element, node.getRight()));
            return node;
        }

        if (comp.compare(node.getElement(), element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
            return node;
        }
        return node;
    }

    /**
     *
     * Retorna a instância de país armazenada na árvore que tenha um determinado
     * nome
     *
     * @param nomePais Nome do país a procurar
     * @return Instância de país com o nome. Null se não for encontrado.
     */
    public Pais getPaisInstance(String nomePais) {
        for (Pais p : inOrder()) {
            if (p.getNome().equalsIgnoreCase(nomePais)) {
                return p;
            }
        }
        return null;
    }
}
