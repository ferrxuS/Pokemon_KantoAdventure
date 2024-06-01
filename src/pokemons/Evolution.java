/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pokemons;

/**
 *
 * @author ilsat
 */
import Trainer.Trainer;

public class Evolution {

    
    public Trainer trainer;
    public Pokemon trainerPokemon;

    public boolean evolve(Pokemon pokemon) {
        StringBuilder ret = new StringBuilder();
       
            switch (pokemon.getName()) {
                case "Squirtle":
                    if (pokemon.getLevel() == 10) {
                        pokemon.setName("Wartortle");
                        return true;
                    } else if (pokemon.getLevel() == 20) {
                        pokemon.setName("Blastoise");
                        return true;
                    }
                    break;
                case "Charmander":
                    if (pokemon.getLevel() == 10) {
                        pokemon.setName("Charmeleon");
                        return true;
                    } else if (pokemon.getLevel() == 20) {
                        pokemon.setName("Charizard");
                        return true;
                    }
                    break;
                case "Bulbasaur":
                    if (pokemon.getLevel() == 10) {
                        pokemon.setName("Ivysaur");
                        return true;
                    } else if (pokemon.getLevel() == 20) {
                        pokemon.setName("Venusaur");
                        return true;
                    }
                    break;
                default:
                    ret.append("Invalid Pokemon");
                    return false; // Exit method if the Pokemon is invalid
            }

            
        return false;
    }
}
