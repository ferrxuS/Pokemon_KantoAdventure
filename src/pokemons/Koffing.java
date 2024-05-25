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
//Fuchsia City
public class Koffing extends pokemon {

    public Koffing() {
        super(
                "Koffing",
                new ArrayList<String>() {
            {
                add("Poison");
            }
        },
                20,
                170,
                new HashMap<String, Integer>() {
            {
                put("Poison Gas", 32);
                put("Smog", 30);
            }
        },
                true, // Is a Gym Leader Pokémon
                false, // Not a Wild Pokémon
                "Fuchsia City" // Location
        );
    }
}
