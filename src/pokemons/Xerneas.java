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
// Viridian City
public class Xerneas extends Pokemon {
    public Xerneas() {
        super(
            "Xerneas",
            new ArrayList<String>() {{
                add("Fairy");
            }},
            32,
            360,
            new HashMap<String, Integer>() {{
                put("Aurora Beam", 45);
                put("Horn Leech", 43);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Viridian City" // Location
        );
    }
}