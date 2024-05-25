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
//Cinnabar Island
public class Blastoise extends pokemon {
    public Blastoise() {
            super(
                "Blastoise", 
                new ArrayList<String>() {{
                    add("Water");
                }},
                26, 
                260,
                new HashMap<String, Integer>() {{
                    put("HydroPump",37);
                    put("Rapid Spin", 26);
                }},
                    false,
                    true,
                    "Cinnabar Island"
            );
        }
}
