/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.*;

/**
 *
 * @author raishahaque
 */
public class Graph {

    // Adding a vertex for each city
    public static Vertex v1 = new Vertex(1, "Pallet Town");
    public static Vertex v2 = new Vertex(2, "Viridian City");
    public static Vertex v3 = new Vertex(3, "Cinnabar Island");
    public static Vertex v4 = new Vertex(4, "Lavender Town");
    public static Vertex v5 = new Vertex(5, "Pewter City");
    public static Vertex v6 = new Vertex(6, "Cerulean City");
    public static Vertex v7 = new Vertex(7, "Vermillion City");
    public static Vertex v8 = new Vertex(8, "Celadon City");
    public static Vertex v9 = new Vertex(9, "Fuchsia City");
    public static Vertex v10 = new Vertex(10, "Saffron City");

    private Map<Vertex, List<Vertex>> adjVertices;
    private Map<Vertex, List<Edge>> adjEdge;

    public Graph() {
        this.adjVertices = new HashMap<>();
        this.adjEdge = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    // Method to add unweighted edge
    public void addEdge(Vertex start, Vertex destination) {
        adjVertices.get(start).add(destination);
        adjVertices.get(destination).add(start);
    }

    // Method to add weighted edge
    public void addEdge(Vertex start, Vertex destination, int weight) {
        Edge edge = new Edge(start, destination, weight);
        adjEdge.putIfAbsent(start, new ArrayList<>());
        adjEdge.putIfAbsent(destination, new ArrayList<>());
        adjEdge.get(start).add(edge);
        adjEdge.get(destination).add(edge);
    }

    List<Vertex> getAdjVertices(Vertex vertex) {
        return adjVertices.get(vertex);
    }

    public List<String> shortestPath(Vertex start, Vertex destination) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        for (Vertex vertex : adjVertices.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
        }
        distances.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(destination)) {
                break; // Stop when reaching the destination
            }
            List<Edge> edges = adjEdge.get(current);
            if (edges != null) {
                for (Edge edge : edges) {
                    int newDist = distances.get(current) + edge.getWeight();
                    if (newDist < distances.get(edge.getDestination())) {
                        distances.put(edge.getDestination(), newDist);
                        predecessors.put(edge.getDestination(), current);
                        queue.add(edge.getDestination());
                    }
                }
            }
        }

        // Reconstruct the shortest path
        List<String> shortestPath = new ArrayList<>();
        Vertex current = destination;
        while (current != null) {
            shortestPath.add(current.getName());
            current = predecessors.get(current);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}

class Edge {

    private final Vertex start;
    private final Vertex destination;
    private final int weight;

    public Edge(Vertex start, Vertex destination, int weight) {
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}

class Vertex {

    private final int id;
    private final String name;

    Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
