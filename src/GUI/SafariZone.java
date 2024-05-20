/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIM XIN MEI
 */
public class SafariZone {

    public static String result(String queue) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n    Sorting your Pokemon according to their unique preferences...\n");
        sb.append(SortPokemon(queue)).append("\n");
        sb.append("    +---------------------------------------------------------------------+  \n").append("    Your pokemon are now sorted! Enjoy yor adventure in the Safari Zone!");
        sb.append("\n    +---------------------------------------------------------------------+  \n");
        return sb.toString();
    }

    public static String SortPokemon(String q) {
        StringBuilder sb = new StringBuilder();
        String[] pokemon = q.split(", ");
        List<String> pokemonList = new ArrayList<>();
        for (int i = 0; i < pokemon.length; i++) {
            pokemonList.add(pokemon[i]);
        }
        boolean hasBulbasaur = pokemonList.contains("Bulbasaur");
        boolean hasCharmander = pokemonList.contains("Charmander");
        boolean hasPikachu = pokemonList.contains("Pikachu");
        boolean hasSnorlax = pokemonList.contains("Snorlax");
        boolean hasJigglypuff = pokemonList.contains("Jigglypuff");
        boolean hasEevee = pokemonList.contains("Eevee");
        boolean hasMachop = pokemonList.contains("Machop");
        int step = 1;
        if (hasEevee) {
            sb.append("\n    Step ").append(step).append(" Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability.\n");
            pokemonList.remove("Eevee");
            pokemonList.add(0, "Eevee");
            sb.append("    Sorted List: ");
            for (int i = 0; i < pokemonList.size(); i++) {
                if (i == pokemonList.size() - 1) {
                    sb.append(pokemonList.get(i));
                } else {
                    sb.append(pokemonList.get(i)).append(",");
                }
            }
            step++;
        }
        if (hasPikachu && hasJigglypuff) {
            sb.append("\n\n    Step ").append(step).append(" Pikachu demands to be placed at the center of the arrangement.\n");
            int mid = (pokemonList.size() - 1) / 2;
            pokemonList.remove("Pikachu");
            pokemonList.remove("Jigglypuff");
            pokemonList.add(mid - 1, "Jigglypuff");
            pokemonList.add(mid, "Pikachu");
            sb.append("    Partial Sort: ");
            for (int i = 0; i < pokemonList.size(); i++) {
                if (i == pokemonList.size() - 1) {
                    sb.append(pokemonList.get(i));
                } else {
                    sb.append(pokemonList.get(i)).append(",");
                }
            }
            step++;
        }
        if (hasSnorlax) {
            sb.append("\n\n    Step ").append(step).append(" Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation.\n");
            pokemonList.remove("Snorlax");
            pokemonList.add("Snorlax");
            sb.append("    Partial Sort: ");
            for (int i = 0; i < pokemonList.size(); i++) {
                if (i == pokemonList.size() - 1) {
                    sb.append(pokemonList.get(i));
                } else {
                    sb.append(pokemonList.get(i)).append(",");
                }
            }
            step++;
        }
        if (hasJigglypuff) {
            sb.append("\n\n    Step ").append(step).append(" Jigglypuff prefers to be surrounded by other \"cute\" Pokémon for morale purposes.\n");
            if (hasPikachu) {
                int mid = (pokemonList.size() - 1) / 2;
                pokemonList.remove("Pikachu");
                pokemonList.remove("Jigglypuff");
                pokemonList.add(mid - 1, "Jigglypuff");
                pokemonList.add(mid, "Pikachu");
                sb.append("    Partial Sort: ");
                for (int i = 0; i < pokemonList.size(); i++) {
                    if (i == pokemonList.size() - 1) {
                        sb.append(pokemonList.get(i));
                    } else {
                        sb.append(pokemonList.get(i)).append(",");
                    }
                }
                step++;
            }
        }
        if (hasBulbasaur && hasCharmander) {
            sb.append("\n\n    Step ").append(step).append(": Bulbasaur refuses to be placed near Charmander.\n");

            int bulbasaurIndex = pokemonList.indexOf("Bulbasaur");
            int charmanderIndex = pokemonList.indexOf("Charmander");

            // Remove Charmander from its current position
            pokemonList.remove(charmanderIndex);

            // Determine the new position for Charmander
            if (bulbasaurIndex <= pokemonList.size() / 2) {
                // If Bulbasaur is in the first half, place Charmander at the second-to-last position
                pokemonList.add(pokemonList.size()-1, "Charmander");
            } else {
                // If Bulbasaur is in the second half, place Charmander at the second position
                pokemonList.add(1, "Charmander");
            }

            // Append the modified list to the StringBuilder
            sb.append("    Partial Sort: ");
            for (int i = 0; i < pokemonList.size(); i++) {
                if (i == pokemonList.size() - 1) {
                    sb.append(pokemonList.get(i));
                } else {
                    sb.append(pokemonList.get(i)).append(",");
                }
            }
            step++;
        }
        if (hasMachop) {
            sb.append("\n\n    Step ").append(step).append(" Machop demands to be placed next to the heaviest Pokemon in the lineup to show off its strength.\n");
            pokemonList.remove("Machop");
            pokemonList.add(pokemonList.size() - 1, "Machop");
            step++;
        }
        // Final sorted list
        sb.append("    Final Sorted List: ").append(String.join(",", pokemonList)).append("\n");
        return sb.toString();
    }

}
