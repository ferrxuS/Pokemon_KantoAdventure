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
public class Muk extends Pokemon{
    // Fuchsia City
    public Muk() {
        super(
            "Muk", 
            new ArrayList<String>() {{
                add("Poison");
            }},
            19,
            160,
            new HashMap<String, Integer>() {{
                put("Mud Slap", 31);
                put("Sludge Bomb", 27);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Fuchsia City" // Location
        );
    }
}
