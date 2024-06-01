/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PokemonBattle_LevelUp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import Trainer.Trainer;
import pokemons.Pokemon;

public class Location {

    private String name;
    private List<Pokemon> wildPokemons;
    private List<Pokemon> gymLeaderPokemons;

    public static final String VIRIDIAN_CITY = "Viridian City";
    public static final String PEWTER_CITY = "Pewter City";
    public static final String CERULEAN_CITY = "Cerulean City";
    public static final String VERMILLION_CITY = "Vermillion City";
    public static final String CELADON_CITY = "Celadon City";
    public static final String FUCHSIA_CITY = "Fuchsia City";
    public static final String SAFFRON_CITY = "Saffron City";
    public static final String CINNABAR_ISLAND = "Cinnabar Island";
    public static final String PALLET_TOWN = "Pallet Town";
    public static final String LAVENDER_TOWN = "Lavender Town";

    public Location(String name) {
        this.name = name;
        this.wildPokemons = new ArrayList<>();
        this.gymLeaderPokemons = new ArrayList<>();
    }

    // Method to dynamically load Pokemon based on their location and type
    public void loadPokemons(Trainer trainer) {
        if (trainer.getCurrentLocation() == null) {
            System.err.println("Error: Trainer's current location is null.");
            return; // Exit method if current location is null
        }

        String currentLocationName = trainer.getCurrentLocation().getName();

        // Iterate through all Pokemon classes
        List<Class<?>> subclasses = new ArrayList<>();
        Class<?> superclass = Pokemon.class;
        String packageName = superclass.getPackage().getName();
        for (Class<?> c : getClasses(packageName)) {
            if (superclass.isAssignableFrom(c) && !superclass.equals(c)) {
                subclasses.add(c);
            }
        }
        for (Class<?> subclass : subclasses) {
            try {
                Pokemon pokemon = (Pokemon) subclass.getDeclaredConstructor().newInstance();

                // Check if the Pokemon's location matches the current location
                if (pokemon.getLocation() != null) { // if not trainer pokemon
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
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Check if gym leader Pokémon were found in the current location
        if (gymLeaderPokemons.isEmpty())

        {
            System.err.println("No gym leader Pokémon found in the current location: " + currentLocationName);
        }
    }

    private List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            java.net.URL resource = classLoader.getResource(path);
            java.nio.file.Path directory = java.nio.file.Paths.get(resource.toURI());
            java.util.stream.Stream<java.nio.file.Path> files = java.nio.file.Files.walk(directory);
            files.forEach(file -> {
                if (file.toString().endsWith(".class")) {
                    String className = file.toString()
                            .replace(directory.toString(), "")
                            .replace(".class", "")
                            .replace('/', '.')
                            .replace('\\', '.')
                            .substring(1); // Remove leading dot
                    try {
                        classes.add(Class.forName(packageName + "." + className));
                    } catch (ClassNotFoundException e) {
                        // Handle exception
                    }
                }
            });
            files.close();
        } catch (Exception e) {
            // Handle exception
        }
        return classes;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getWildPokemons() {
        return wildPokemons;
    }

    public List<Pokemon> getGymLeaderPokemons() {
        return gymLeaderPokemons;
    }
}
