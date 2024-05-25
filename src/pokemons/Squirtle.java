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
public class Squirtle extends Pokemon {
    //Pallet Town
    public Squirtle() {
        super(
                "Squirtle",
                new ArrayList<String>() {
            {
                add("Water");
            }
        },
                5,
                new HashMap<String, Integer>() {
            {
                put("Water Gun", 30);
                put("Tail Whip",20);
            }
        },
                new ArrayList<String>() {
            {
                add("Water");
                add("Fire");
            }
        },
                new ArrayList<String>() {
            {
                add("Grass");
                add("Electricity");
            }
        }
        );
    }
}
