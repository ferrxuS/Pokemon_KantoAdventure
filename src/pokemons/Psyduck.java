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
public class Psyduck extends Pokemon{
    // Cerulean City
    public Psyduck() {
        super(
            "Psyduck", 
            new ArrayList<String>() {{
                add("Water");
                add("Psychic");
            }},
            9,
            65,
            new HashMap<String, Integer>() {{
                put("Confusion", 27);
                put("Scratch", 26);
            }},
            true,  // Is a Gym Leader Pokémon
            false, // Not a Wild Pokémon
            "Cerulean City" // Location
        );
    }
}
