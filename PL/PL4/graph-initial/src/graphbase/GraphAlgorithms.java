/*
* A collection of graph algorithms.
 */
package graphbase;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g Graph instance
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> visitados = new LinkedList<>();
        LinkedList<V> lista = new LinkedList<>();
        LinkedList<V> bfs = new LinkedList<>();

        lista.add(vert);
        visitados.add(vert);
        bfs.add(vert);

        while (!lista.isEmpty()) {
            V vertice = lista.poll();
            for (V adjVert : g.adjVertices(vertice)) {
                if (!visitados.contains(adjVert)) {
                    bfs.add(adjVert);
                    lista.add(adjVert);
                    visitados.add(adjVert);
                }
            }
        }

        return bfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.getKey(vOrig)] = true;

        for (V vAdj : g.adjVertices(vOrig)) {
            if (!visited[g.getKey(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * @param vert
     * @param g Graph instance
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert)) {
            return null;
        }

        int n = g.numVertices();
        boolean[] visited = new boolean[n];

        LinkedList<V> qdfs = new LinkedList<>();
        DepthFirstSearch(g, vert, visited, qdfs);

        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        path.add(vOrig);
        visited[g.getKey(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.push(vDest);
                paths.add(new LinkedList<>(path));
                path.pop();
            } else {
                if (!visited[g.getKey(vAdj)]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }
        }
        path.remove(vOrig);
        visited[g.getKey(vOrig)] = false;
    }

    /**
     * @param g Graph instance
     * @param vOrig
     * @param vDest
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        LinkedList<V> path = new LinkedList<>();
        ArrayList<LinkedList<V>> paths = new ArrayList<>();

        if (!g.validVertex(vOrig)) {
            return paths;
        }

        if (!g.validVertex(vDest)) {
            return paths;
        }

        boolean[] visited = new boolean[g.numVertices()];
        allPaths(g, vOrig, vDest, visited, path, paths);

        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vertices
     * @param visited set of discovered vertices
     * @param dist minimum distances
     * @param pathKeys
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
            int vOrigKey = getVertMinDist(dist, visited);
            vOrig = null;
            for(V v : g.allkeyVerts()){
                if(vOrigKey == g.getKey(v)){
                    vOrig = v;
                }
            }
        }
    }

    protected static int getVertMinDist(double[] dist, boolean[] visited) {
        double minDist = Double.MAX_VALUE;
        int vertMinDistKey = -1;
        for (int i = 0; i < dist.length; i++) {
            if(!visited[i] && dist[i] < minDist){
                vertMinDistKey = i;
                minDist = dist[i];
            }
        }
        return vertMinDistKey;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param vOrig
     * @param vDest
     * @param path stack with the minimum path (correct order)
     * @param pathKeys
     * @param verts
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        V vert = vDest;
        path.push(vDest);
        while(!vert.equals(vOrig)){
            int vertKey = pathKeys[g.getKey(vert)];
            for(V v : g.allkeyVerts()){
                if(g.getKey(v) == vertKey){
                    vert = v;
                }
            }
            path.push(vert);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        if(!g.validVertex(vDest) || !g.validVertex(vOrig)){
            return 0;
        }
        shortPath.clear();
        ArrayList <LinkedList<V>> paths = new ArrayList<>();
        ArrayList <Double> dists = new ArrayList<>();
        
        shortestPaths(g, vOrig, paths, dists);
       
        shortPath.addAll(paths.get(g.getKey(vDest)));
        if(dists.get(g.getKey(vDest)) != Double.MAX_VALUE){
            return dists.get(g.getKey(vDest));
        }else{
            return 0;
        }
    }

    //shortest-path between voInf and all other
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
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }
}
