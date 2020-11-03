package PL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @author DEI-ESINF
 * @param <E>
 */
public class TREE<E extends Comparable<E>> extends BST<E> {

    /*
    * @param element A valid element within the tree
    * @return true if the element exists in tree false otherwise
     */
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }

        return (find(element, root) != null);
    }

    public boolean isLeaf(E element) {
        Node n = find(element, root);
        if (n == null) {
            return false;
        }

        if (n.getLeft() == null && n.getRight() == null) {
            return true;
        }
        return false;
    }

    /*
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
     */
    public Iterable<E> ascdes() {
        List<E> result = new ArrayList<>();
        if (root != null) {
            ascSubtree(root.getLeft(), result);
            result.add(root.getElement());
            desSubtree(root.getRight(), result);
        }

        return result;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }

        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);
    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }

        ascSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getLeft(), snapshot);

    }

    /**
     * Returns the tree without leaves.
     *
     * @return tree without leaves
     */
    public BST<E> autumnTree() {
        BST<E> autumnTree = new TREE();
        autumnTree.root = copyRec(root);
        return autumnTree;
    }

    /**
     *
     * Cria cópia dos nós que não são folha.
     *
     * @param node
     * @return
     */
    private Node<E> copyRec(Node<E> node) {
        if (node != null) {
            if (!isLeaf(node.getElement())) {
                return new Node(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));
            }
        }
        return null;
    }

    /**
     * @return the the number of nodes by level.
     */
    public int[] numNodesByLevel() {
        int[] nodesByLevel = new int[height() + 1];

        int i = 0;
        Map<Integer, List<E>> levels = nodesByLevel();
        for (List<E> l : levels.values()) {
            nodesByLevel[i++] = l.size();
        }

        return nodesByLevel;
    }
}
