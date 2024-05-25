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
// Pewter City
public class Pidgey extends pokemon {
    public Pidgey() {
        super(
            "Pidgey",
            new ArrayList<String>() {{
                add("Flying");
            }},
            9,
            65,
            new HashMap<String, Integer>() {{
                put("Tackle", 30);
                put("Whirlwind", 20);
            }},
            false, // Not a Gym Leader Pokémon
            true,  // Is a Wild Pokémon
            "Pewter City" // Location
        );
    }
}