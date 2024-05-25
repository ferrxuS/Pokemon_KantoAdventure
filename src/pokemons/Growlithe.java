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
public class Growlithe extends pokemon {

    //Cinnabar Island
    public Growlithe() {
        super(
                "Growlithe",
                new ArrayList<String>() {
            {
                add("Fire");
            }
        },
                25,
                245,
                new HashMap<String, Integer>() {
            {
                put("Flame Wheel", 35);
                put("Bite", 32);
            }
        },
                true, // Is a Gym Leader Pokémon
                false, // Not a Wild Pokémon
                "Cinnabar Island" // Location
        );
    }
}
