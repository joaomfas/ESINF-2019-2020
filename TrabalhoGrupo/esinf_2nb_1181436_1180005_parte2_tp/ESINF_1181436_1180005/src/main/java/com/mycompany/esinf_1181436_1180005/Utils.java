package com.mycompany.esinf_1181436_1180005;

public class Utils {

    public static Pais getPaisByCapital(Graph<Pais,Fronteira> mapa, String capital) {
        Pais pais = null;
        for (Pais p : mapa.allkeyVerts()) {
            if (p.getCapital().equalsIgnoreCase(capital)) {
                pais = p;
            }
        }
        return pais;
    }
}
