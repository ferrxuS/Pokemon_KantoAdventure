/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PokemonBattle_LevelUp;

import static GUI.NewAdventurePanel.trainer;
import java.util.*;
import pokemons.*;

public class Location {

    private String name;
    private List<pokemon> wildPokemons;
    private List<pokemon> gymLeaderPokemons;

    public static final String VIRIDIAN_CITY = "Viridian City";
    public static final String PEWTER_CITY = "Pewter City";
    public static final String CERULEAN_CITY = "Cerulean City";
    public static final String VERMILLION_CITY = "Vermillion City";
    public static final String CELADON_CITY = "Celdaon City";
    public static final String FUCHSIA_CITY = "Fuchsia City";
    public static final String SAFFRON_CITY = "Saffron City";
    public static final String CINNABAR_ISLAND = "Cinnabar Island";
    public static final String PALLET_TOWN = "Pallet Town";
    public static final String LAVENDER_TOWN = "Lavender Town";

    public Location(String name) {
        this.name = name;
        this.wildPokemons = new ArrayList<>();
        this.gymLeaderPokemons = new ArrayList<>();
        loadPokemons();
    }

    // Method to dynamically load Pokemon based on their location and type
    private void loadPokemons() {
        if (trainer.getCurrentLocation() == null) {
            System.err.println("Error: Trainer's current location is null.");
            return; // Exit method if current location is null
        }

        String currentLocationName = trainer.getCurrentLocation().getName();

        // Iterate through all Pokemon classes
        for (Class<?> pokemonClass : pokemon.class.getDeclaredClasses()) {
            try {
                // Instantiate the Pokemon class
                pokemon pokemon = (pokemon) pokemonClass.getDeclaredConstructor().newInstance();

                // Check if the Pokemon's location matches the current location
                if (pokemon.getLocation().equalsIgnoreCase(currentLocationName)) {
                    // Add to wildPokemons if it's a wild Pokemon
                    if (pokemon.isWildPokemon()) {
                        wildPokemons.add(pokemon);
                    }
                    // Add to gymLeaderPokemons if it's a gym leader Pokemon
                    if (pokemon.isGymLeaderPokemon()) {
                        gymLeaderPokemons.add(pokemon);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Check if gym leader Pokémon were found in the current location
        if (gymLeaderPokemons.isEmpty()) {
            System.err.println("No gym leader Pokémon found in the current location: " + currentLocationName);
        }
    }

    public String getName() {
        return name;
    }

    public List<pokemon> getWildPokemons() {
        return wildPokemons;
    }

    public List<pokemon> getGymLeaderPokemons() {
        return gymLeaderPokemons;
    }
}
