/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemons;

import java.util.*;

/**
 *
 * @author raishahaque
 */
public class Geodude extends pokemon {
    
    // Pewter City
    public Geodude() {
        super(
            "Geodude", 
            new ArrayList<String>() {{
                add("Rock");
            }},
            8,
            60,
            new HashMap<String, Integer>() {{
                put("Tackle", 26);
                put("Bulldoze", 24);
            }},
            true, // Is a Gym Leader Pokémon
            false,  // Not a Wild Pokémon
            "Pewter City" // Location
        );
    }
}
