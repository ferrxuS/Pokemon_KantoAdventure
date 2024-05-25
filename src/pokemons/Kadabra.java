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
// Saffron City
public class Kadabra extends Pokemon{
    public Kadabra() {
        super(
            "Kadabra", 
            new ArrayList<String>() {{
                add("Psychic");
            }},
            22,
            200,
            new HashMap<String, Integer>() {{
                put("Kinesis", 34);
                put("Psybeam", 30);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Saffron City" // Location
        );
    }
}
