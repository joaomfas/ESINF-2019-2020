/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isep.ipp.pt.esinf_1181436_1180005;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author breno
 */
public class Paises2DTree extends BST<Pais> {

	/**
	 * Insert method overriden to apply for Pais instead of generics
	 *
	 * @param p
	 */
	@Override
	public void insert(Pais p) {
		root = insert(p, root, 0);
	}

	/**
	 * 2D-tree insert method. depending of level (0 -> x) and (1 -> y) we
	 * compare Pais's latitude or longitude for inserting left or right.
	 *
	 * @param p
	 * @param node
	 * @return
	 */
	public Node<Pais> insert(Pais p, Node<Pais> node, int level) {
		if (node == null) {
			return new Node(p, null, null);
		}

		double diffLat = p.getLatitude() - node.getElement().getLatitude();
		double diffLong = p.getLongitude() - node.getElement().getLongitude();
		double diff;

		// select which value to compare and flip level
		if (level == 0) {
			diff = diffLat;
			level = 1;
		} else {
			diff = diffLong;
			level = 0;
		}

		if (diff < 0) {
			node.setLeft(insert(p, node.getLeft(), level));
		} else {
			node.setRight(insert(p, node.getRight(), level));
		}

		return node;
	}

	
	 /**
	 * finds the Pais with given coordinates. If no Pais is found, returns
	 * null. Finds the nearest Pais to the given coordinates and compare
	 * it's coordinates to the given arguments.
	 *
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public Pais findPais(double latitude, double longitude) {
		Node<Pais> node = findPais(latitude, longitude, root, 0);
		if(node != null)
			return node.getElement();
		return null;
	}
	
	private Node<Pais> findPais(double lat, double lon, Node<Pais> node, int level) {
		if (node == null) {
			return null;
		}

		double diffLat = lat - node.getElement().getLatitude();
		double diffLong = lon - node.getElement().getLongitude();
		double diff;

		if(Double.compare(diffLat, 0) == 0 && Double.compare(diffLong, 0) == 0)
			return node;
		
		if (level == 0) {
			diff = diffLat;
			level = 1;
		} else {
			diff = diffLong;
			level = 0;
		}

		if (diff < 0) {
			return findPais(lat, lon, node.getLeft(), level);
		} else {
			return findPais(lat, lon, node.getRight(), level);
		}
	}
	
	



	/**
	 * Finds nearest Pais to the given coordinates. Uses protected method to
	 * start search at level 0 (x). If the tree has no nodes, the search
	 * wields nothing and the method returns null.
	 *
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public Pais findNearestPais(double latitude, double longitude) {
		Node<Pais> node = findNearestPais(latitude, longitude, root, 0);
		if (node != null) {
			return node.getElement();
		}
		return null;
	}

	/**
	 *
	 * @param lat
	 * @param lon
	 * @param node
	 * @param level
	 * @return
	 */
	protected Node<Pais> findNearestPais(double lat, double lon, Node<Pais> node, int level) {

		// if we hit an empty node, go back
		if (node == null) {
			return null;
		}

		// calculate dx and dy
		double diffLat = lat - node.getElement().getLatitude();
		double diffLong = lon - node.getElement().getLongitude();

		// select whether to compare dx or dy and flip level
		double diff;
		if (level == 0) {
			diff = diffLat;
			level = 1;
		} else {
			diff = diffLong;
			level = 0;
		}

		// define which node to visit and which node is the other branch
		Node<Pais> nodeToVisit;
		
		// the node not visited by default  (other branch) is also saved as a variable
		Node<Pais> otherBranchNode;
		if (diff < 0) {
			nodeToVisit = node.getLeft();
			otherBranchNode = node.getRight();
		} else {
			nodeToVisit = node.getRight();
			otherBranchNode = node.getLeft();
		}

		// visit one level down the three and hold teh current nearest neihbor
		Node<Pais> currentBest = findNearestPais(lat, lon, nodeToVisit, level);

		// if the currentBest is null, we have hit the point where the
		// target would be inserted. so we return this node as the best
		if (currentBest == null) {
			return node;
		}

		// calculate squared distance from this node and current best node to the target
		// squaredDist is used to avoid having to compute squared roots
		double distNode = node.getElement().squaredDist(lat, lon);
		double distCurrentBestNode = currentBest.getElement().squaredDist(lat, lon);

		// check whether this node is better than the current best
		// in this case, set the this node as the current best
		if (distNode < distCurrentBestNode) {
			currentBest = node;
			distCurrentBestNode = distNode;
		}

		//  check if there could be nearer points on the other side of
		// the splitting plane. if this is true, visit down the the other branch of the tree.
		// and check if it returns a better node
		if (diff < distCurrentBestNode && otherBranchNode != null) {
			Node<Pais> otherBranchBest = 
				findNearestPais(lat, lon, otherBranchNode, level);
			
			double distOtherBranchBest = 
				otherBranchBest.getElement().squaredDist(lat, lon);
			
			if (distCurrentBestNode < distCurrentBestNode) {
				currentBest = otherBranchBest;
			}
		}

		return currentBest;
	}

	public List<Pais> squareSearch(double lat1, double lon1, double lat2, double lon2) {
		List<Pais> listaPaises = new LinkedList<>();
		squareSearch(lat1, lon1, lat2, lon2, root, 0, listaPaises);
		return listaPaises;
	}

	private void squareSearch(double lat1, double lon1, double lat2, double lon2,
		Node<Pais> node, int level, List<Pais> listaPaises) {
		if (node == null) {
			return;
		}
		// by default, we make x -> latitude and y -> longitude
		double x1 = lat1;
		double x2 = lat2;
		double xk = node.getElement().getLatitude();
		double y1 = lon1;
		double y2 = lon2;
		double yk = node.getElement().getLongitude();
		
		// select whether to compare dx or dy and flip level
		if (level == 0) {
			level = 1;
		} else {
			level = 0;
			// if we are comparing longitude, flip values for xn and yn
			double tmp;
			tmp = x1;
			x1 = y1;
			y1 = tmp;
			tmp = x2;
			x2 = y2;
			y2 = tmp;
			tmp = xk;
			xk = yk;
			yk = tmp;
		}

		// if node is to the left of square, visit left children.
		// and if it is to the right, visit right children
		if(xk < x2) 
			squareSearch(lat1, lon1, lat2, lon2, node.getRight(), level, listaPaises);
		if(xk > x1) 
			squareSearch(lat1, lon1, lat2, lon2, node.getLeft(), level, listaPaises);
		
		// if node is inside square in x, verify that it is inside 
		// square in y too before inserting into the list
		if (xk >= x1 && xk <= x2) 
			if (yk > y1 && yk < y2) 
				listaPaises.add(node.getElement());
	}
	
}
