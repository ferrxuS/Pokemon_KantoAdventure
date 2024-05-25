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
public class Dragonair extends pokemon {

    //Fuchsia City
    public Dragonair() {
        super(
            "Dragonair",
            new ArrayList<String>() {{
                add("Dragon");
            }},
            21,
            185,
            new HashMap<String, Integer>() {{
                put("Dragon Dance", 35);
                put("Twister", 25);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Fuchsia City" // Location
        );
    }
}
