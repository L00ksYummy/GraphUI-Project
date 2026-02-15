/*
 * Michael Allen
 * Project 4
 * 7/6/2025
 * This file holds the methods for the functionality of the buttons. It creates edges, helps find vertex's for the add edge button, and holds the methods for 
 * the has cycle, dfs, bfs, and is connected buttons to work.
 */

package com.example.week8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph{
    private Map<Vertex, List<Vertex>> adjacencyList = new HashMap();
	
    public void addVertex(Vertex vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public class Edge {
        private final Vertex vertex1;
        private final Vertex vertex2;

        public Edge(Vertex v1, Vertex v2){
            this.vertex1 = v1;
            this.vertex2 = v2;
        }

        public Vertex getVertex1(){
            return vertex1;
        }

        public Vertex getVertex2(){
            return vertex2;
        }
    }

    public Vertex findVertexByName(String name) {
    for (Vertex v : adjacencyList.keySet()) {
        if (v.getName().equals(name)) {
            return v;
        }
    }
    return null; // not found
}

    public void addEdge(Vertex v1, Vertex v2){
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public boolean hasCycle(){
        Set<Vertex> visited = new HashSet<>();
        for (Vertex v: adjacencyList.keySet()){
            if (!visited.contains(v)){
                if (dfsCycleCheck(v, visited, null)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfsCycleCheck(Vertex v, Set<Vertex> visited, Vertex parent){
        visited.add(v);

        for (Vertex neighbor: adjacencyList.get(v)){
            if (!visited.contains(neighbor)){
                if (dfsCycleCheck(neighbor, visited, v)) return true;
            } else if (!neighbor.equals(parent)) return true;
        }

        return false;
    }

    public boolean isConnected(){
        if (adjacencyList.isEmpty()) return true;

        List<Vertex> visited = new ArrayList<>();

        Vertex start = adjacencyList.keySet().iterator().next();


        dfsIsConnected(start, visited);

        return (visited.size() == adjacencyList.size());
    }

    public void dfsIsConnected(Vertex v, List<Vertex> visited){
        visited.add(v);
        for (Vertex neighbor: adjacencyList.get(v)){
            if (!visited.contains(neighbor)){
                dfsIsConnected(neighbor, visited);
            }
        }
    }

    public List<Vertex> bfs(Vertex v){
        List<Vertex> traversal = new ArrayList<>();
        Queue<Vertex> toVisit = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>(); 

        toVisit.add(v);
        visited.add(v);

        while (!toVisit.isEmpty()){
            Vertex current = toVisit.poll();
            traversal.add(current); 

            for (Vertex neighbor: adjacencyList.get(current)){
                if (!visited.contains(neighbor)){
                    toVisit.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return traversal;
    }

    public List<Vertex> dfs(){
        List<Vertex> visited = new ArrayList<>();
        Vertex start = adjacencyList.keySet().iterator().next();
        return dfs(start, visited);
    }

    public List<Vertex> dfs(Vertex v, List<Vertex> visited){
        visited.add(v);
        for (Vertex neighbor: adjacencyList.get(v)){
            if (!visited.contains(neighbor)){
                dfs(neighbor, visited);
            }
        }
        return visited;
    }

}
