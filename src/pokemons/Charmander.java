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
public class Charmander extends Pokemon {

    public Charmander() {
        //pallet town
        super(
                "Charmander",
                new ArrayList<String>() {
            {
                add("Fire");
            }
        },
                5,
                new HashMap<String, Integer>() {
            {
                put("Flamethrower", 30);
                put("Dragon Breath", 20);
            }
        },
                new ArrayList<String>() {
            {
                add("Grass");
                add("Bug");
                add("Ice");
            }
        },
                new ArrayList<String>() {
            {
                add("Water");
                add("Rock");
            }
        }
        );
    }
}
