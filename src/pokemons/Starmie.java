/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemons;

import java.util.*;

public class Starmie extends Pokemon {
    // Cerulean City
    public Starmie() {
        super(
            "Starmie",
            new ArrayList<String>() {{
                add("Water");
                add("Psychic");
            }},
            10,
            70,
            new HashMap<String, Integer>() {{
                put("Brine", 27); // Update damage as needed
                put("Cosmic Power", 26); // Update damage as needed
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Cerulean City" // Location
        );
    }

}