/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.Random;

/**
 *
 * @author LIM XIN MEI
 */

import static GUI.Graph.*;
import java.util.List;

public class Race {

    // Method to calculate the shortest distance using the graph
    public static String ShortestDistance() {
        Graph graph = initializeGraph();
        
        // Description of the race
        StringBuilder sb = new StringBuilder();
        sb.append("    The battle has begun! Your rival Gary has challenged you to a race to\n    ");
        String[] options = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuchsia City"};
        Random r = new Random();
        int randomIndex = r.nextInt(options.length);
        String chosenCity = options[randomIndex];
        sb.append(chosenCity).append("!\n    Shortest Path:\n    ");
        
        // Calculating shortest path based on the chosen city
        List<String> shortestPath = calculateShortestPath(graph, chosenCity);
        sb.append(result(shortestPath));
        sb.append("\n    Good luck on your race!\n  +---------------------------------------------------------------------+  \n");

        return sb.toString();
    }

    // Helper method to initialize the graph with vertices and edges
    private static Graph initializeGraph() {
        Graph graph = new Graph();
        
        // Adding vertices
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);
        graph.addVertex(v10);

        // Adding edges with weights
        graph.addEdge(v10, v6, 6);
        graph.addEdge(v10, v7, 3);
        graph.addEdge(v10, v8, 4);
        graph.addEdge(v10, v4, 3);
        graph.addEdge(v8, v9, 10);
        graph.addEdge(v6, v5, 12);
        graph.addEdge(v6, v4, 9);
        graph.addEdge(v3, v1, 7);
        graph.addEdge(v3, v9, 5);
        graph.addEdge(v9, v7, 7);
        graph.addEdge(v9, v4, 11);
        graph.addEdge(v1, v2, 5);
        graph.addEdge(v5, v2, 8);
        graph.addEdge(v7, v4, 5);

        return graph;
    }

    // Helper method to calculate the shortest path based on the chosen city
    private static List<String> calculateShortestPath(Graph graph, String chosenCity) {
        switch (chosenCity) {
            case "Cinnabar Island":
                return graph.shortestPath(v10, v3);
            case "Pewter City":
                return graph.shortestPath(v10, v5);
            case "Viridian City":
                return graph.shortestPath(v10, v2);
            case "Pallet Town":
                return graph.shortestPath(v10, v1);
            case "Fuchsia City":
                return graph.shortestPath(v10, v9);
            default:
                throw new IllegalArgumentException("Invalid city: " + chosenCity);
        }
    }

    // Helper method to format the shortest path
    public static String result(List<String> shortestPath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortestPath.size(); i++) {
            if (i == shortestPath.size() - 1) {
                sb.append(shortestPath.get(i));
            } else {
                sb.append(shortestPath.get(i)).append(" -> ");
            }
        }
        return sb.toString();
    }

    // Method to simulate a race, now using the graph to decide the shortest path
    public static String race() {
        Graph graph = initializeGraph();
        
        StringBuilder sb = new StringBuilder();
        sb.append("    The battle has begun! Your rival Gary has challenged you to a race to\n");
        String[] options = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuchsia City"};
        Random r = new Random();
        int randomIndex = r.nextInt(options.length);
        String chosenCity = options[randomIndex];
        sb.append(chosenCity).append(".\n    Shortest Path:\n");
        
        // Calculate and append the shortest path for the chosen city
        List<String> shortestPath = calculateShortestPath(graph, chosenCity);
        sb.append(result(shortestPath));
        
        sb.append("\n    Good luck on your race!\n  +---------------------------------------------------------------------+  \n");

        return sb.toString();
    }
}