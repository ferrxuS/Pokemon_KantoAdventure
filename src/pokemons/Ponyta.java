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
public class Ponyta extends Pokemon {
// Cinnabar Island
    public Ponyta() {
        super(
            "Ponyta", 
            new ArrayList<String>() {{
                add("Fire");
            }},
            24,
            230,
            new HashMap<String, Integer>() {{
                put("Flame Charge", 33);
                put("Fire Spin", 30);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Cinnabar Island" // Location
        );
    }
}
