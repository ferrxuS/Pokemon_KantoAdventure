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
// Celadon City
public class Beedrill extends Pokemon {
    public Beedrill() {
        super(
            "Beedrill", 
            new ArrayList<String>() {{
                add("Bug");
                add("Poison");
            }},
            18,
            150,
            new HashMap<String, Integer>() {{
                put("Poison Sting", 36);
                put("Bug Bite", 25);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Celadon City" // Location
        );
    }
}
