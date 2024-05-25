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
public class Victreebel extends pokemon{
    // Celadon City
    public Victreebel() {
        super(
            "Victreebel", 
            new ArrayList<String>() {{
                add("Grass");
            }},
            17,
            140,
            new HashMap<String, Integer>() {{
                put("Razor Leaf", 30);
                put("Sleep Powder", 28);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Celadon City" // Location
        );
    }
}
