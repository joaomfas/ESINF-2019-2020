/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author joaoferreira
 */
public class CarregarDadosTest {

	public CarregarDadosTest() {
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

	/**
	 * Test of carregarPaises method, of class CarregarDados.
	 */
	@Test
	public void testCarregarPaises() {
		System.out.println("carregarPaises");
		List<Pais> listaPaises = new ArrayList<>();
		CarregarDados instance = new CarregarDados();
		instance.carregarPaises(listaPaises);
	}

	/**
	 * Test of carregarFronteiras method, of class CarregarDados.
	 */
	@Test
	public void testCarregarFronteiras() {
		System.out.println("carregarFronteiras");
		List<Pais> listaPaises = new ArrayList<>();
		Map<Pais, Set<Pais>> paisesFronteiras = new HashMap<>();
		CarregarDados instance = new CarregarDados();
		instance.carregarFronteiras(listaPaises, paisesFronteiras);
	}

	/**
	 * Test of getPaisByName method, of class CarregarDados.
	 */
	@Disabled
	public void testGetPaisByName() {
		System.out.println("getPaisByName");
		String name = "russia";
		List<Pais> listaPaises = new ArrayList<>();
		CarregarDados instance = new CarregarDados();
		instance.carregarPaises(listaPaises);
		Pais expResult = new Pais("russia", Continente.europa, 146.5, "moscovo", 55.755786, 37.617633);
		Pais result = instance.getPaisByName(name, listaPaises);
		assertEquals(expResult, result);
	}

}
