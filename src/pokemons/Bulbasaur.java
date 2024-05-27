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
// Pallet Town
public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super(
                "Bulbasaur",
                new ArrayList<String>() {
            {
                add("Grass");
                add("Poison");
            }
        },
                5,
                new HashMap<String, Integer>() {
            {
                put("Vine Whip", 30);
                put("Tackle", 20);
            }
        },
                new ArrayList<String>() {
            {
                add("Water");
                add("Ground");
                add("Rock");
            }
        },
                new ArrayList<String>() {
            {
                add("Fire");
                add("Poison");
                add("Bug");
            }
        }
        );
    }
}
