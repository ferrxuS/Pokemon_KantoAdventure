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
    
    public void evolve() {
        StringBuilder ret = new StringBuilder();
    String evolution = "";

    switch (trainerPokemon.name) {
        case "Squirtle":
            if (trainerPokemon.level == 10) {
                evolution = "Wartortle";
            } else if (trainerPokemon.level == 20) {
                evolution = "Blastoise";
            }
            break;
        case "Charmander":
            if (trainerPokemon.level == 10) {
                evolution = "Charmeleon";
            } else if (trainerPokemon.level == 20) {
                evolution = "Charizard";
            }
            break;
        case "Bulbasaur":
            if (trainerPokemon.level == 10) {
                evolution = "Ivysaur";
            } else if (trainerPokemon.level == 20) {
                evolution = "Venusaur";
            }
            break;
        default:
            ret.append("Invalid Pokemon");
            return; // Exit method if the Pokemon is invalid
    }

    if (!evolution.isEmpty()) {
        ret.append("Your Pokemon ").append(trainerPokemon.name).append(" has evolved!");
        ret.append(trainerPokemon.name).append(" has evolved to ").append(evolution);
        trainerPokemon.name = evolution;

        if (trainerPokemon.level == 30) {
            ret.append("Your team is complete!");
        } else if(Trainer.getPokemonList().get(0).getLevel() == 10 || (Trainer.getPokemonList().get(0).level == 20 && Trainer.getPokemonList().get(1).level == 10)){
            ret.append("Choose another Pokemon to add to your team:");
            ret.append(trainer.showPokemonList());
            trainer.addPokemon();
        }
    }
    evolution = "";
}

}
