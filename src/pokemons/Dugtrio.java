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
public class Dugtrio extends Pokemon {
    
    //Viridian City
    public Dugtrio() {
        super(
            "Dugtrio", 
            new ArrayList<String>() {{
                add("Ground");
            }},
            30,
            320,
            new HashMap<String, Integer>() {{
                put("Night Slash", 36);
                put("Sand Attack", 34);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Viridian City" // Location
        );
    }
}
