/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trainer;

import java.io.*;
import java.util.*;
import javax.swing.JPanel;
import pokemons.*;
import PokemonBattle_LevelUp.*;

/**
 *
 * @author LIM XIN MEI
 */
public class Trainer {

    private String trainerName;
    private Location currentLocation; // Updated to use Location instead of JPanel
    private static ArrayList<String> badges = new ArrayList<>();
    private static ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> BadgesList = new ArrayList<>();
    private Pokemon selectedPokemon; // Field to store the selected Pokémon
    
    public Trainer() {
        this.trainerName = trainerName;
        this.selectedPokemon = selectedPokemon;
        this.currentLocation = currentLocation;
    }

    public static ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
    
    public void setCurrentLocation(Location currentLocation) { // Updated setter for Location
        this.currentLocation = currentLocation;
    }

    public void setSelectedPokemon(Pokemon selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
        addPokemon(); // Automatically add the selected Pokémon to the list
    }

    public String getTrainerName() {
        return trainerName;
    }

    public Location getCurrentLocation() { // Updated getter for Location
        return currentLocation;
    }

    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }
    
    public void addToList(String pokemon){
        if(pokemon == "Squirtle"){
            pokemonList.add(new Squirtle());
        }else if(pokemon == "Charmander"){
            pokemonList.add(new Charmander());
        }else if(pokemon == "Bulbasaur"){
            pokemonList.add(new Bulbasaur());
        }
    }

    public void addPokemon(){
        if (!pokemonList.contains(selectedPokemon)) {
            pokemonList.add(selectedPokemon);
        }
    }

    public String showPokemonList() {
        if (!pokemonList.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Pokemon p : pokemonList) {
                result.append(p.toString()).append("\n");
            }
            return result.toString();
        } else {
            return "No Pokémon selected.";
        }
    }

    public static void earnBadges(String location) {
        String filepath = "Badges.txt";
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
            result.append("\n  +---------------------------------------------------------------------+");
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