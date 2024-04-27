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

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex start, Vertex destination) {
        adjVertices.get(start).add(destination);
        adjVertices.get(destination).add(start);
    }

    List<Vertex> getAdjVertices(Vertex vertex) {
        return adjVertices.get(vertex);
    }
    
    public String getVertexName(int vertexId) {
    for (Vertex vertex : adjVertices.keySet()) {
        if (vertex.getId() == vertexId) {
            return vertex.getName();
        }
    }
    return null; // Return null if vertex with given ID is not found
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
