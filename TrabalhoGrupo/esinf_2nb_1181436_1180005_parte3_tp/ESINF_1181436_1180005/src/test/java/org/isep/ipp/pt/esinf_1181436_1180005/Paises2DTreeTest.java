/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isep.ipp.pt.esinf_1181436_1180005;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author breno
 */
public class Paises2DTreeTest {

	List<Pais> listaPaises;
	public Paises2DTreeTest() {
		// (3, 6), (17, 15), (13, 15), (6, 12), (9, 1), (2, 7), (10, 19)
		Pais p1 = new Pais("A", "", 0, "", 3, 6);
		Pais p2 = new Pais("B", "", 0, "", 17, 15);
		Pais p3 = new Pais("C", "", 0, "", 13, 15);
		Pais p4 = new Pais("D", "", 0, "", 6, 12);
		Pais p5 = new Pais("E", "", 0, "", 9, 1);
		Pais p6 = new Pais("F", "", 0, "", 2, 7);
		Pais p7 = new Pais("G", "", 0, "", 10, 19);
		listaPaises = new LinkedList<Pais>(){{
			addLast(p1);
			addLast(p2);
			addLast(p3);
			addLast(p4);
			addLast(p5);
			addLast(p6);
			addLast(p7);
		}};
	}

	/**
	 * Test of insert method, of class Paises2DTree.
	 */
	@Test
	public void testInsertPais() {
		System.out.println("insert");
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		List<Pais> result = new LinkedList<Pais>();
		instance.posOrder().forEach(p -> { result.add(p); });
		List<Pais> expResult = new LinkedList<Pais>() {{
			addLast(listaPaises.get(5)); // F
			addLast(listaPaises.get(4)); // E
			addLast(listaPaises.get(3)); // D
			addLast(listaPaises.get(6)); // G
			addLast(listaPaises.get(2)); // C
			addLast(listaPaises.get(1)); // B
			addLast(listaPaises.get(0)); // A 
		}};
		System.out.println(instance.toString());
		assertEquals(expResult, result);
	}

	/**
	 * Test of findPais method, of class Paises2DTree.
	 */
	@Test
	public void testFindPais() {
		System.out.println("findPais");
		double latitude = 6;
		double longitude = 12;
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		Pais expResult = listaPaises.get(3); // D (6, 12)
		Pais result = instance.findPais(latitude, longitude);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of findPais method, of class Paises2DTree.
	 * tests if method returns null when the coordinates aren't equal
	 * to any of the Pais's.
	 */
	@Test
	public void testFindPais_null() {
		System.out.println("findPais_null");
		double latitude = 9.1;
		double longitude = 1.0;
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		Pais expResult = null;
		Pais result = instance.findPais(latitude, longitude);
		assertEquals(expResult, result);
	}
	
	
	/**
	 * Test of findNearestPais method, of class Paises2DTree.
	 */
	@Test
	public void testFindNearestPais() {
		System.out.println("findNearestPais");
		double latitude = 19;
		double longitude = 13;
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		Pais expResult = listaPaises.get(1); // B (16, 10)
		Pais result = instance.findNearestPais(latitude, longitude);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of findNearestPais method, of class Paises2DTree.
	 * tests if empty tree returns null
	 */
	@Test
	public void testFindNearestPais_null() {
		System.out.println("findNearestPais_null");
		double latitude = 0.0;
		double longitude = 0.0;
		Paises2DTree instance = new Paises2DTree();
		Pais expResult = null;
		Pais result = instance.findNearestPais(latitude, longitude);
		assertEquals(expResult, result);
	}

	/**
	 * Test of insert method, of class Paises2DTree.
	 */
	@Test
	public void testInsert_Pais() {
		System.out.println("insert");
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		assertEquals(instance.size(), 7);
	}

	/**
	 * Test of squareSearch method, of class Paises2DTree.
	 */
	@Test
	public void testSquareSearch() {
		System.out.println("squareSearch");
		double lat1 = 5.0;
		double lat2 = 15.0;
		double lon1 = 10.0;
		double lon2 = 17.0;
		Paises2DTree instance = new Paises2DTree();
		listaPaises.forEach(p -> { instance.insert(p); });
		Set<Pais> expResult = new HashSet<Pais>() {{ 
			add(listaPaises.get(2));
			add(listaPaises.get(3));
		}};
		List<Pais> result = instance.squareSearch(lat1, lon1, lat2, lon2);
		Set<Pais> resultSet = new HashSet<Pais>();
		result.forEach(p -> { resultSet.add(p);} );
		assertEquals(expResult, resultSet);
	}

}
