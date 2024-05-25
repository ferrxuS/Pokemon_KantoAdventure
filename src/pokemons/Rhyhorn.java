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
public class Rhyhorn extends pokemon{
    // Viridian City
    public Rhyhorn() {
        super(
            "Rhyhorn", 
            new ArrayList<String>() {{
                add("Ground");
                add("Rock");
            }},
            31,
            340,
            new HashMap<String, Integer>() {{
                put("Horn Attack", 40);
                put("Drill Run", 35);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Viridian City" // Location
        );
    }
}

