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
// Vermillion City
public class Sycther extends Pokemon {
    public Sycther() {
        super(
            "Sycther",
            new ArrayList<String>() {{
                add("Bug");
            }},
            13,
            100,
            new HashMap<String, Integer>() {{
                put("Fury Cutter", 33);
                put("Slash", 23);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Vermillion City" // Location
        );
    }
}