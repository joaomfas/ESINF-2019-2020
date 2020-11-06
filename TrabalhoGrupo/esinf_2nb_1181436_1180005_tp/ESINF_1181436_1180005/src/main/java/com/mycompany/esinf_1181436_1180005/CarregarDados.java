package com.mycompany.esinf_1181436_1180005;

import java.io.InputStream;
import java.util.*;

public class CarregarDados {

	/**
	 * Cria uma instância de CarregarDados
	 */
	public CarregarDados() {
	}

	/**
	 *
	 * Carregamento dos países contidos em ficheiro para um ArrayList
	 *
	 * @param listaPaises
	 *                        Lista de países carregados
	 */
	public void carregarPaises(List<Pais> listaPaises) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("paises.txt");
		Scanner scan = new Scanner(is);
		while (scan.hasNextLine()) {
			String[] dados = scan.nextLine().trim().split(",");
			dados[1] = dados[1].trim();
			Pais pais = new Pais(dados[0].trim(), Continente.valueOf(dados[1]), Double.parseDouble(dados[2].trim()),
					dados[3].trim(), Double.parseDouble(dados[4].trim()), Double.parseDouble(dados[5].trim()));
			listaPaises.add(pais);
		}
		scan.close();
	}

	/**
	 *
	 * Carregamento dos Países e Fronteiras para um HashMap
	 *
	 * @param listaPaises
	 *                             Lista de países
	 * @param paisesFronteiras
	 *                             Map Países/Fronteiras
	 */
	public void carregarFronteiras(List<Pais> listaPaises, Map<Pais, Set<Pais>> paisesFronteiras) {
		for (Pais p : listaPaises) {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("fronteiras.txt");
			Scanner scan = new Scanner(is);

			Set<Pais> fronteiras = new HashSet<>();

			while (scan.hasNextLine()) {
				String[] dados = scan.nextLine().trim().split(",");
				dados[0] = dados[0].trim();
				dados[1] = dados[1].trim();
				if (p.getNome().equals(dados[0]) || p.getNome().equals(dados[1])) {
					Pais p1 = getPaisByName(dados[0], listaPaises);
					Pais p2 = getPaisByName(dados[1], listaPaises);
					if (p1 != p) {
						fronteiras.add(p1);
					} else {
						fronteiras.add(p2);
					}
				}
			}
			scan.close();
			paisesFronteiras.put(p, fronteiras);
		}
	}

	/**
	 *
	 * Obtém a instância de país através do seu nome
	 *
	 * @param name
	 *                        Nome do país
	 * @param listaPaises
	 *                        Lista de países
	 * @return Instância de país com o nome
	 */
	public Pais getPaisByName(String name, List<Pais> listaPaises) {
		for (Pais p : listaPaises) {
			if (p.getNome().equals(name)) {
				return p;
			}
		}
		return null;
	}
}
