/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 *
 * @author LIM XIN MEI
 */
public class Trainer {

    private String TrainerName;
    private JPanel CurrentLocation;
    private static ArrayList<String> badges = new ArrayList<>();
    private static ArrayList<ArrayList<Object>> pokemonList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> BadgesList = new ArrayList<>();

    public void setTrainerName(String TrainerName) {
        this.TrainerName = TrainerName;
    }

    public void setCurrentLocation(JPanel CurrentLocation) {
        this.CurrentLocation = CurrentLocation;
    }

    private static String[][] pokedexList = new String[152][4];

    public void readPokedex() {
        try {
            Scanner s = new Scanner(new FileInputStream("src\\GUI\\Pokedex.txt"));

            int index = 0;
            while (s.hasNextLine() && index < pokedexList.length) {
                String line = s.nextLine();
                String[] temp = line.split(",");
                if (temp.length < 4) {
                    pokedexList[index][0] = temp[0];
                    pokedexList[index][1] = temp[1];
                    pokedexList[index][2] = temp[2];
                    pokedexList[index][3] = null; // Set Type 2 to null
                } else {
                    pokedexList[index] = temp;
                }
                index++;
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void addPokemon(String id, int level) {
        readPokedex();
        for (String[] pokedexList1 : pokedexList) {
            if (pokedexList1[0].equals(id)) {
                ArrayList<Object> pokemon = new ArrayList<>();
                pokemon.add(id);
                pokemon.add(pokedexList1[1]);
                pokemon.add(level);
                pokemonList.add(pokemon);
                break;
            }
        }

    }

    public static void removePokemon(int num) {
        pokemonList.remove(num - 1);
    }

    public static String showPokemon() {
        String temp = "   Your Pokemon:";
        int num = 1;
        pokemonList.sort(Comparator.comparing((ArrayList<Object> pokemon) -> (String) pokemon.get(0)).thenComparing((ArrayList<Object> pokemon) -> (Integer) pokemon.get(2)));
        for (ArrayList<Object> pokemon : pokemonList) {
            temp += "\n      " + num + "- " + pokemon.get(1) + " - Level: " + pokemon.get(2);
            num++;
            //method for element creature
        }
        temp += "\n  +---------------------------------------------------------------------+\n";

        return temp;
    }

    public static void earnBadges(String location) {
        String filepath = "src\\GUI\\Badges.txt";
        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                ArrayList<String> Badge = new ArrayList<>();
                String line = sc.nextLine();
                String[] badge = line.split(",");
                Badge.add(badge[0]);
                Badge.add(badge[1]);
                BadgesList.add(Badge);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (ArrayList<String> b : BadgesList) {
            if (location.equalsIgnoreCase(b.get(0))) {
                if (!badges.contains(b.get(1))) {
                    badges.add(b.get(1));
                }
            }
        }
    }

    public static String showBadges() {
        StringBuilder result = new StringBuilder();
        result.append("    Your Badges: \n");
        if (badges.isEmpty()) {
            result.append("    - None");
        } else {
            // Append each badge to the result with a newline character
            for (String badge : badges) {
                result.append("    -").append(badge).append("\n");
            }
            if (badges.size() == 8) {
            String temp = "\n";
            temp += "     _________                                     __         ._.\n";
            temp += "     \\_   ___ \\  ____   ____    ________________ _/  |_  _____| |\n";
            temp += "    /    \\  \\/ /  _ \\ /    \\  / ___\\_  __ \\__  \\\\   __\\/  ___/ |\n";
            temp += "    \\     \\___(  <_> )   |  \\/ /_/  >  | \\// __ \\|  |  \\___ \\ \\|\n";
            temp += "     \\______  /\\____/|___|  /\\___  /|__|  (____  /__| /____  >__\n";
            temp += "             \\/            \\//_____/            \\/          \\/ \\/\n";
                result.append(temp);
                result.append("\n    You have collected all the badges!");
                result.append("\n  +---------------------------------------------------------------------+ \n");
            }
        }
        return result.toString();
    }

}


