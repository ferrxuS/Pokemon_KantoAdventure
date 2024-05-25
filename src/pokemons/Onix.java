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
public class Onix extends pokemon{
    // Pewter City
    public Onix() {
        super(
            "Onix", 
            new ArrayList<String>() {{
                add("Rock");
            }},
            7,
            55,
            new HashMap<String, Integer>() {{
                put("Rock Throw", 24);
                put("Smack Down", 23);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Pewter City" // Location
        );
    }
}
