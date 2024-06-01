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
public class Raichu extends Pokemon{
    // Vermillion City
    public Raichu() {
        super(
            "Raichu", 
            new ArrayList<String>() {{
                add("Electric");
            }},
            12,
            90,
            new HashMap<String, Integer>() {{
                put("Electro Ball", 28);
                put("Iron Tail", 27);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Vermillion City" // Location
        );
    }

}
