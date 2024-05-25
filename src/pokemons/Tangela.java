/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemons;

import java.util.*;

/**
 *
 * @author ilsat
 */
public class Tangela extends pokemon {
    // Celadon City
    public Tangela() {
        super(
            "Tangela", 
            new ArrayList<String>() {{
                add("Grass");
            }},
            16,
            130,
            new HashMap<String, Integer>() {{
                put("Vine Whip", 29);
                put("Bind", 28);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Celadon City" // Location
        );
    }
}