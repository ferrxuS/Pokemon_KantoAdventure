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

    public static String ShortestDistance() {
        Graph graph = new Graph();
        char list = 'a';

        graph.addVertex(v10);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v4);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v5);
        graph.addVertex(v9);

        graph.addEdge(v10, v6, 6);
        graph.addEdge(v10, v7, 3);
        graph.addEdge(v10, v8, 4);
        graph.addEdge(v10, v4, 3);
        graph.addEdge(v8, v10, 4);
        graph.addEdge(v8, v9, 10);
        graph.addEdge(v6, v5, 12);
        graph.addEdge(v6, v10, 6);
        graph.addEdge(v6, v4, 9);
        graph.addEdge(v3, v1, 7);
        graph.addEdge(v3, v9, 5);
        graph.addEdge(v9, v3, 5);
        graph.addEdge(v9, v7, 7);
        graph.addEdge(v9, v8, 10);
        graph.addEdge(v9, v4, 11);
        graph.addEdge(v4, v6, 9);
        graph.addEdge(v4, v10, 3);
        graph.addEdge(v4, v7, 5);
        graph.addEdge(v4, v9, 11);
        graph.addEdge(v1, v2, 5);
        graph.addEdge(v1, v3, 7);
        graph.addEdge(v5, v2, 8);
        graph.addEdge(v5, v6, 12);
        graph.addEdge(v7, v10, 3);
        graph.addEdge(v7, v9, 7);
        graph.addEdge(v7, v4, 5);
        graph.addEdge(v2, v1, 5);
        graph.addEdge(v2, v5, 8);
        StringBuilder sb = new StringBuilder();
        sb.append("    The battle has begun! Your rival Gary has challenged you to a race to ");
        String[] options = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuschia City"};
        Random r = new Random();
        int randomIndex = r.nextInt(options.length);
        String chosenCity = options[randomIndex];
        sb.append(chosenCity).append(".\n    Shortest Path:\n    ");
        List<String> shortestPath;
        switch (chosenCity) {
            case "Cinnabar Island":
                shortestPath = graph.shortestPath(v10, v3);
                sb.append(result(shortestPath));
                break;
            case "Pewter City":
                shortestPath = graph.shortestPath(v10, v5);
                sb.append(result(shortestPath));
                break;
            case "Viridian City":
                shortestPath = graph.shortestPath(v10, v2);
                sb.append(result(shortestPath));
                break;
            case "Pallet Town":
                shortestPath = graph.shortestPath(v10, v1);
                sb.append(result(shortestPath));
                break;
            case "Fuschia City":
                shortestPath = graph.shortestPath(v10, v9);
                sb.append(result(shortestPath));
                break;
        }
        sb.append("\n    Goodluck on your race!\n  +---------------------------------------------------------------------+  \n");

        return sb.toString();
    }

    public static String result(List<String> shortestPath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <shortestPath.size(); i++) {
            if (i == shortestPath.size()-1) {
                sb.append(shortestPath.get(i));
            } else {
                sb.append(shortestPath.get(i)).append(" -> ");
            }
        }
        return sb.toString();
    }

    public static String race() {
        StringBuilder sb = new StringBuilder();
        sb.append("    The battle has begun! Your rival Gary has challenged you to a race to ");
        String[] options = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuschia City"};
        Random r = new Random();
        int randomIndex = r.nextInt(options.length);
        String chosenCity = options[randomIndex];
        sb.append(chosenCity).append(".\n    Shortest Path:\n");
        switch (chosenCity) {
            case "Cinnabar Island":
                sb.append("    Saffron City -> Vermillion City -> Fuchsia City -> Cinnabar Island");
                break;
            case "Pewter City":
                sb.append("    Saffron City -> Cerulean City -> Pewter City");
                break;
            case "Viridian City":
                sb.append("    Saffron City -> Cerulean City -> Pewter City -> Viridian City");
                break;
            case "Pallet Town":
                sb.append("    Saffron City -> Vermillion City -> Fuchsia City -> Cinnabar Island -> Pallet Town");
                break;
            case "Fuschia City":
                sb.append("    Saffron City -> Vermillion City -> Fuchsia City");
                break;
        }
        sb.append("\n    Goodluck on your race!\n  +---------------------------------------------------------------------+  \n");

        return sb.toString();
    }

  
}
