/*
* A collection of graph algorithms.
 */
package com.mycompany.esinf_1181436_1180005;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import javafx.util.Pair;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param <V> V
     * @param <E> E
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vertices list of all vertices
     * @param visited set of discovered vertices
     * @param dist minimum distances
     * @param pathKeys List with each vertex successors
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        dist[g.getKey(vOrig)] = 0;
        while (vOrig != null) {
            visited[g.getKey(vOrig)] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                if (!visited[g.getKey(vAdj)] && dist[g.getKey(vAdj)] > (dist[g.getKey(vOrig)] + g.getEdge(vOrig, vAdj).getWeight())) {
                    dist[g.getKey(vAdj)] = dist[g.getKey(vOrig)] + g.getEdge(vOrig, vAdj).getWeight();
                    pathKeys[g.getKey(vAdj)] = g.getKey(vOrig);
                }
            }
            double minDist = Double.MAX_VALUE;
            int vOrigKey = -1;
            for (int i = 0; i < dist.length; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    vOrigKey = i;
                    minDist = dist[i];
                }
            }
            vOrig = null;
            for (V v : g.allkeyVerts()) {
                if (vOrigKey == g.getKey(v)) {
                    vOrig = v;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param <V> V
     * @param <E> E
     * @param g Graph instance
     * @param vOrig Origin vertex
     * @param vDest Destin vertex
     * @param path stack with the minimum path (correct order)
     * @param pathKeys List with each vertex successors
     * @param verts List of all vertexes
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        V vert = vDest;
        path.push(vDest);
        while (!vert.equals(vOrig)) {
            int vertKey = pathKeys[g.getKey(vert)];
            for (V v : g.allkeyVerts()) {
                if (g.getKey(v) == vertKey) {
                    vert = v;
                }
            }
            path.push(vert);
        }
    }

    /**
     *
     * Algorithm that finds the minimum distance between origin - destiny
     * passing thorugh the intermediate nodes. It starts by getting paths
     * between the origin and all the others, then the intermediate nodes and
     * all the others and between destiny and all the others. After that is runs
     * through all the combinations that have origin - intermediate nodes -
     * destiny and finds the shortest path.
     *
     * @param g Graph
     * @param vOrig Origin vertex
     * @param vDest Destiny vertex
     * @param nosIntermedios Intermediate nodes
     * @return Minimum distance between origin - destiny passing thorugh the
     * intermediate nodes
     */
    public static <V, E> double caminhoMaisCurtoComIntermedios(Graph<V, E> g, V vOrig, V vDest, ArrayList<V> nosIntermedios, ArrayList<V> caminho) {

        //Caminhos de vOrig para todos os outros
        ArrayList<LinkedList<V>> pathsOrigem = new ArrayList<>();
        ArrayList<Double> distsOrigem = new ArrayList<>();
        shortestPaths(g, vOrig, pathsOrigem, distsOrigem);

        //Caminhos de vDest para todos os outros
        ArrayList<LinkedList<V>> pathsDest = new ArrayList<>();
        ArrayList<Double> distsDest = new ArrayList<>();
        shortestPaths(g, vDest, pathsDest, distsDest);

        //Caminhos dos intermédios para todos os outros
        Map<V, ArrayList<Double>> pathIntermedios = new HashMap<>();
        for (V v : nosIntermedios) {
            ArrayList<LinkedList<V>> pathInterm = new ArrayList<>();
            ArrayList<Double> distInterm = new ArrayList<>();
            shortestPaths(g, v, pathInterm, distInterm);
            pathIntermedios.put(v, distInterm);
        }

        double dist = Double.MAX_VALUE;
        //Percorrer todas as permutações possíveis de caminhos e encontrar o caminho mais curto
        if (nosIntermedios.size() > 0) {
            ArrayList<V> vIntermedios = new ArrayList<>();
            for (V vertex : g.allkeyVerts()) {
                double distInterms = 0;
                if (nosIntermedios.contains(vertex)) {
                    for (V vInterm : nosIntermedios) {
                        distInterms += pathIntermedios.get(vInterm).get(g.getKey(vertex));
                        vIntermedios.add(vInterm);
                    }
                    if (dist > distsOrigem.get(g.getKey(vertex))
                            + distsDest.get(g.getKey(vertex))
                            + distInterms) {

                        caminho.clear();
                        caminho.addAll(pathsOrigem.get(g.getKey(vIntermedios.get(0))));
                        if (vIntermedios.size() > 1) {
                            for (V vInt : vIntermedios) {
                                if (vIntermedios.indexOf(vInt) != 0) {
                                    LinkedList<V> camInt = new LinkedList<>();
                                    shortestPath(g, vIntermedios.get(vIntermedios.indexOf(vInt) - 1), vInt, camInt);
                                    camInt.remove(0);
                                    caminho.addAll(camInt);
                                }
                            }
                        }
                        LinkedList<V> pathDestInt = new LinkedList<>(pathsDest.get(g.getKey(vIntermedios.get(vIntermedios.size() - 1))));
                        Collections.reverse(pathDestInt);
                        pathDestInt.remove(0);
                        caminho.addAll(pathDestInt);
                        dist = distsOrigem.get(g.getKey(vertex))
                                + distsDest.get(g.getKey(vertex))
                                + distInterms;

                    }
                }
            }
        } else {
            dist = distsOrigem.get(g.getKey(vDest));
            caminho.addAll(pathsOrigem.get(g.getKey(vDest)));
        }

        return dist;
    }

    /**
     *
     * Shortest-path between vOrig and vDest
     *
     * @param <V> V
     * @param <E> E
     * @param g Grafo
     * @param vOrig Nó de origem
     * @param vDest Nó de destino
     * @param shortPath Caminho mais curto
     * @return Distância do caminho mais curto entre o nó de origem e o destino
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        if (!g.validVertex(vDest) || !g.validVertex(vOrig)) {
            return 0;
        }
        shortPath.clear();
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        shortestPaths(g, vOrig, paths, dists);

        shortPath.addAll(paths.get(g.getKey(vDest)));
        if (dists.get(g.getKey(vDest)) != Double.MAX_VALUE) {
            return dists.get(g.getKey(vDest));
        } else {
            return 0;
        }
    }

    /**
     *
     * shortest-path between voInf and all other
     *
     * @param <V> V
     * @param <E> E
     * @param g Grafo
     * @param vOrig Nó de origem
     * @param paths Caminhos mais curtos para os vários nós
     * @param dists Distância de cada caminho
     * @return Retorna false se algum parâmetro é inválido e true se o cálculo
     * for realizado
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE) {
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * colorMap generates the coloring of a graph so that two connected vertices
     * never share the same color.The method returns a map, given a graph g
     * given as input, where the graph's vertexes are keys to an integers from 1
     * to N representing the applied color.
     *
     * Discussion: Because the problem of finding the chromatic number (minimum
     * number of colors possible) is an NP-complete problem, only approximate
     * methods are available. The algorithm here applied is the Greedy
     * algorithm, taken from the book "A guide to graph coloring" from R.M.R
     * Lewis, and is not exact (does not wield the minimum number of colors
     * possible). The vertices may be are ordered by their outgoing degree as a
     * means of optimization.
     *
     * How it works:
     *
     * 1. gets a list of vertices from the graph (v1, v2... vn) 2. iterates
     * through the list trying to assign a color not yet assigned to the
     * vertice's neighbors. to do so: 2.1 creates a set of already assigned
     * colors to neighbors 2.2 finds first color not yet assigned (first
     * available color) 2.3 assign color to vertex
     *
     * @param <V> V
     * @param <E> E
     * @param g Graph instance
     * @return Returns a map with the colors
     */
    public static <V, E> Map<V, Integer> colorMap(Graph<V, E> g) {
        V[] vertices = g.allkeyVerts();

        // ordering of the vertices according to their degree
        Arrays.sort(vertices, new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                if (g.outDegree(o2) > g.outDegree(o1)) {
                    return 1;
                }
                return -1;
            }
        });

        if (vertices.length == 0) {
            return null;
        }
        Map<V, Integer> colorMap = new HashMap();

        // greedy algortim
        for (V vertex : vertices) {
            // create a set of colors already assigned to neighbors
            List<V> neighbors = Arrays.asList(g.allNeighbors(vertex));
            Set<Integer> colors = new HashSet<>();
            for (V neighbor : neighbors) {
                if (colorMap.containsKey(neighbor)) {
                    int color = colorMap.get(neighbor);
                    colors.add(color);
                }
            }
            // find the first available color for vertex
            int firstAvailableColor = 1;
            while (true) {
                if (colors.contains(firstAvailableColor)) {
                    firstAvailableColor++;
                } else {
                    break;
                }
            }
            // apply color
            colorMap.put(vertex, firstAvailableColor);
        }

        return colorMap;
    }

    /**
     * The method traverses g, generating a cycle for an origin vertex visiting
     * neighbors zero or one times.It uses the nearest neighbor heuristics:
     * meaning we always visit the next nearest neighbor.If generating a path
     * from it is not possible, the method uses the second nearest neighbor, and
     * so on.In this method the data structure necessary are defined and the
     * recursive auxiliary function performing the computation is called.Refer
     * to the cycleRecursive function for description. The Path list contains
     * the vertices traversed so far. An auxiliary list of "impossible" vertices
     * is used to mark those vertices which cannot be part of the path as they
     * do not find a way back to the origin vertex.
     *
     * The lists and graph are passed to the function cycleRecursive which
     * generates the path through recursion.
     *
     * @param <V> V
     * @param <E> E
     * @param origin Origin vertex
     * @param g Graph instance
     * @return Cycle
     */
    public static <V, E> LinkedList<V> nearestNeighborCycle(V origin, Graph<V, E> g) {
        LinkedList<V> path = new LinkedList<>();
        ArrayList<V> impossible = new ArrayList<>();
        cycleRecursive(origin, origin, g, path, impossible);
        path.add(origin);
        return path;
    }

    /**
     * This auxiliary function generates the a list of V representing the path
     * from a given vertex to a destination vertex, passing once only through
     * other vertices in the graph g.A queue of vertices linked to curVertex is
     * constructed using the edge's weight.Lower weights means higher priority
     * on the queue.
     *
     * The function is called recursively using the adjacent vertex with lowest
     * weight as argument. If a path can be successfuly constructed, a "true"
     * message is propagated. Otherwise, a "false" message is propagated and the
     * vertex is marked as "impossible", meaning it wont be visited further.
     *
     * When no path can be constructed using the adjacent vertex with lowest
     * weight, it is removed from the queue and the next vertex with lowest
     * weight is used instead.
     *
     * @param <V> V
     * @param <E> E
     * @param curVertex Current vertex
     * @param dest Destiny vertex
     * @param g Graph instance
     * @param path Path
     * @param impossible Visited vertexes
     * @return False if the current vertex has path to the destiny. True if it
     * has.
     */
    public static <V, E> boolean cycleRecursive(V curVertex, V dest, Graph<V, E> g, LinkedList<V> path, ArrayList<V> impossible) {
        // add current vertex to path
        path.addLast(curVertex);

        // creates a queue of edges to visit based on least distance
        Queue<Pair<V, Double>> queue;
        queue = new PriorityQueue<>(new Comparator<Pair<V, Double>>() {
            @Override
            public int compare(Pair<V, Double> o1, Pair<V, Double> o2) {
                return Double.compare(o1.getValue(), o2.getValue());
            }
        });

        for (Edge<V, E> edge : g.outgoingEdges(curVertex)) {
            double weigth = edge.getWeight();
            V vdest = (V) edge.getVDest();
            queue.add(new Pair<V, Double>(vdest, weigth));
        }
        // visits vertices. if a solution is found, propagates success
        while (!queue.isEmpty()) {
            V v = queue.poll().getKey();
            if (v.equals(dest) && path.size() > 1) {
                return true;
            }
            if (path.contains(v)) {
                continue;
            }
            if (impossible.contains(v)) {
                continue;
            }
            if (cycleRecursive(v, dest, g, path, impossible)) {
                return true;
            }
        }

        // if solution was not found in this branch, remove vertex
        // adds to the list of impossible vertices
        path.remove(curVertex);
        impossible.add(curVertex);
        return false;
    }

    /**
     *
     * Implementation of the DSatur algorithm to color the graph.This algorithm
     * always colours the vertices of a graph one after another, expending a
     * previously unused colour when needed.Once a new vertex has been coloured,
     * the algorithm determines which of the remaining uncoloured vertices has
     * the highest number of colours in its neighbourhood and colours this
     * vertex next.
     *
     * @param g Graph
     * @return Returns an array with the vertexes colours
     */
    public static <V, E> int[] DSatur(Graph<V, E> g) {
        V[] vertices = g.allkeyVerts();
        int[] vCores = new int[g.numVertices()];

        List<V> comCor = new ArrayList<>();

        Map<V, Integer> coresResultantes = new LinkedHashMap<>();

        Arrays.fill(vCores, -1);
        List<V> semCor = new ArrayList<>(Arrays.asList(vertices));

        // The algorithm starts on the vertex which has the biggest ougoing degree since no vertex is coloured
        int grauMax = -1;
        V vGrauMax = null;
        for (V vGrau : g.allkeyVerts()) {
            if (g.outDegree(vGrau) > grauMax) {
                grauMax = g.outDegree(vGrau);
                vGrauMax = vGrau;
            }
        }
        vCores[g.getKey(vGrauMax)] = 0;
        comCor.add(vGrauMax);
        semCor.remove(vGrauMax);
        coresResultantes.put(vGrauMax, 0);

        while (semCor.size() > 0) {
            // Selects the vertex with the biggest variety of neighbor colours
            int vertice = obterVertMaxSat(g, vCores, vertices);
            while (comCor.contains(vertices[vertice])) {
                vertice = obterVertMaxSat(g, vCores, vertices);
            }

            // Starts list with available colours. For n nodes the max colours is n
            boolean[] coresDisponiveis = new boolean[g.numVertices()];
            for (int j = 0; j < g.numVertices(); j++) {
                coresDisponiveis[j] = true;
            }

            // ArrayList with the neighbors of that vertex
            ArrayList<V> vAdj = new ArrayList<>();
            for (V v : g.adjVertices(vertices[vertice])) {
                vAdj.add(v);
            }

            int ultimaCor = 0;
            // Looks for the neighbors colours and marks them as unavailable
            for (int k = 0; k < comCor.size(); k++) {
                V verAtual = comCor.get(k);
                if (vAdj.contains(verAtual)) {
                    int cor = coresResultantes.get(verAtual);
                    coresDisponiveis[cor] = false;
                }
            }

            // Selects the first available colour
            for (int j = 0; j < coresDisponiveis.length; j++) {
                if (coresDisponiveis[j]) {
                    ultimaCor = j;
                    break;
                }
            }

            semCor.remove(vertices[vertice]);
            comCor.add(vertices[vertice]);
            coresResultantes.put(vertices[vertice], ultimaCor);
            vCores[vertice] = ultimaCor;
        }

        return vCores;
    }

    /**
     *
     * Determines which uncolored vertex has the neighbors with the biggest
     * variety of colors
     *
     * @param g Grafo
     * @param vCores Cores de cada vértice
     * @param vertices Lista de vértices
     * @returnv értice cujos vértices adjacentes possuem o maior número de cores
     * diferentes
     */
    public static <V, E> int obterVertMaxSat(Graph<V, E> g, int[] vCores, V[] vertices) {
        int maxSat = 0;
        int vComMaxSat = 0;

        for (int i = 0; i < g.numVertices(); i++) {
            if (vCores[i] == -1) {
                Set<Integer> cores = new TreeSet<>();
                ArrayList<V> vAdj = new ArrayList<>();
                for (V v : g.adjVertices(vertices[i])) {
                    vAdj.add(v);
                }
                for (int j = 0; j < g.numVertices(); j++) {
                    if (vAdj.contains(vertices[j]) && vCores[j] != -1) {
                        cores.add(vCores[j]);
                    }
                }
                int tempSat = cores.size();
                if (tempSat > maxSat) {
                    maxSat = tempSat;
                    vComMaxSat = i;
                } else if (tempSat == maxSat
                        && g.outDegree(vertices[i]) >= g.outDegree(vertices[vComMaxSat])) {
                    vComMaxSat = i;
                } else if (tempSat == 0) {
                    vComMaxSat = i;
                }
            }
        }
        return vComMaxSat;
    }

    /**
     *
     * Implementation of the Welsh-Powell algorithm for coloring a graph. This
     * algorithm tries to colour every node with a color. It it fails, it goes
     * to the next colour and tries it again. And so on. Until it succeeds to
     * colour all the nodes. It follows the nodes by their outgoing degree.
     *
     * @param g Graph
     * @param vertices Vertex list
     * @param cores Vertex colours
     * @param vert Current vertex
     * @param vertIndex Current vertex index
     * @param cor Current colour
     * @return Returns true if it succeeds to colours all the nodes
     */
    public static <V, E> boolean welshPowell(Graph<V, E> g, V[] vertices, int[] cores, V vert, int vertIndex, int cor) {
        ArrayList<Integer> coresList = new ArrayList<>();
        for (int i = 0; i < cores.length; i++) {
            coresList.add(cores[i]);
        }

        if (!coresList.contains(0)) {
            return true;
        }

        cores[vertIndex] = cor;
        Set<V> adjVerts = new HashSet<>((Collection<? extends V>) g.adjVertices(vert));

        for (V v : vertices) {
            if (!adjVerts.contains(v) && !v.equals(vert) && cores[findIndex(vertices, v)] == 0) {
                int index = findIndex(vertices, v);
                cores[index] = cor;
                adjVerts.addAll((Collection<? extends V>) g.adjVertices(v));
            }
        }
        vertIndex = nextUncolored(cores);
        cor++;
        welshPowell(g, vertices, cores, vertices[vertIndex], vertIndex, cor);

        return false;
    }

    /**
     *
     * Prepares the data structures for the Welsh-Powll algorithm. It also
     * orders the vertexes by their outgoing degree,
     *
     * @param g Graph
     * @return Returns a map with the Nodes and their colours
     */
    public static <V, E> Map<V, Integer> welshPowell(Graph<V, E> g) {
        int[] cores = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++) {
            cores[i] = 0;
        }

        V[] vertices = g.allkeyVerts();

        Arrays.sort(vertices, new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                return g.outDegree(o2) - g.outDegree(o1);
            }
        });

        welshPowell(g, vertices, cores, vertices[0], 0, 1);

        Map<V, Integer> verCores = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            verCores.put(vertices[i], cores[i]);
        }

        return verCores;
    }

    /**
     *
     * Returns the next uncoloured vertex
     *
     * @param cores List of the vertexes colours
     * @return The next uncoloured vertex
     */
    private static <V, E> int nextUncolored(int[] cores) {
        for (int i = 0; i < cores.length; i++) {
            if (cores[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * Returns a vertexes index according to the given list
     *
     * @param vertices Vertexes list
     * @param vertice Vertex
     * @return Vertex index
     */
    private static <V, E> int findIndex(V[] vertices, V vertice) {
        int index = 0;
        for (V v : vertices) {
            if (v.equals(vertice)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
