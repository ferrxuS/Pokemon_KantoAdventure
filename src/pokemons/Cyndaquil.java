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
public class Cyndaquil extends pokemon {

    //Saffron City
    public Cyndaquil() {
        super(
            "Cyndaquil",
            new ArrayList<String>() {{
                add("Fire");
            }},
            23,
            215,
            new HashMap<String, Integer>() {{
                put("Flame Wheel", 36);
                put("Twister", 30);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Saffron City" // Location
        );
    }
}
