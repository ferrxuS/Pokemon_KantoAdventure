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
public class MrMime extends Pokemon{
    // Saffron City
    public MrMime() {
        super(
            "MrMime", 
            new ArrayList<String>() {{
                add("Psychic");
                add("Fairy");
            }},
            21,
            185,
            new HashMap<String, Integer>() {{
                put("Copy Cat", 32);
                put("Pound", 29);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Saffron City" // Location
        );
    }
}
