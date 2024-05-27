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
            System.out.println("Invalid Pokemon");
            return; // Exit method if the Pokemon is invalid
    }

    if (!evolution.isEmpty()) {
        System.out.println("Your Pokemon " + trainerPokemon.name + " has evolved!");
        System.out.println(trainerPokemon.name + " has evolved to " + evolution);
        trainerPokemon.name = evolution;

        if (trainerPokemon.level == 30) {
            System.out.println("Your team is complete!");
        } else {
            System.out.println("Choose another Pokemon to add to your team:");
            System.out.println(trainer.showPokemonList());
            trainer.addPokemon();
        }
    }
}

}
