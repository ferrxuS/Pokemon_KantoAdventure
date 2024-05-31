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
    
    public void evolve(Pokemon pokemon) {
        StringBuilder ret = new StringBuilder();
    String evolution = "";

    switch (pokemon.getName()) {
        case "Squirtle":
            if (pokemon.getLevel() == 10) {
                evolution = "Wartortle";
            } else if (pokemon.getLevel() == 20) {
                evolution = "Blastoise";
            }
            break;
        case "Charmander":
            if (pokemon.getLevel() == 10) {
                evolution = "Charmeleon";
            } else if (pokemon.getLevel() == 20) {
                evolution = "Charizard";
            }
            break;
        case "Bulbasaur":
            if (pokemon.getLevel() == 10) {
                evolution = "Ivysaur";
            } else if (pokemon.getLevel() == 20) {
                evolution = "Venusaur";
            }
            break;
        default:
            ret.append("Invalid Pokemon");
            return; // Exit method if the Pokemon is invalid
    }

    if (!evolution.isEmpty()) {
        ret.append("\n  +---------------------------------------------------------------------+  \n");
        ret.append("    Your Pokemon ").append(pokemon.getName()).append(" has evolved!\n");
        ret.append("    ").append(pokemon.getName()).append(" has evolved to ").append(evolution);
        pokemon.setName(evolution);
        ret.append("\n  +---------------------------------------------------------------------+  \n");

        if (pokemon.getLevel() == 30) {
            ret.append("    Your team is complete!\n");
        } else if(Trainer.getPokemonList().get(0).getLevel() == 10 || (Trainer.getPokemonList().get(0).getLevel() == 20 && Trainer.getPokemonList().get(1).getLevel() == 10)){
            ret.append("    Choose another Pokemon to add to your team:");
            ret.append(trainer.showPokemonList());
            trainer.addPokemon();
        }
    }
    evolution = "";
}

}
