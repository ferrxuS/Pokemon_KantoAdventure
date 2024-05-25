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
// Cerulean City
public class Meowth extends pokemon {
    public Meowth() {
        super(
            "Meowth",
            new ArrayList<String>() {{
                add("Normal");
            }},
            11,
            80,
            new HashMap<String, Integer>() {{
                put("Bite", 32);
                put("Taunt", 22);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Cerulean City" // Location
        );
    }
}
