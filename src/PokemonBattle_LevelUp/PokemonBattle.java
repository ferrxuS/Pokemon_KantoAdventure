/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PokemonBattle_LevelUp;

import javax.swing.*;
import Trainer.Trainer;
import java.util.*;
import pokemons.Pokemon;

/**
 *
 * @author raishahaque
 */
public class PokemonBattle {

    public Trainer trainer;
    public Pokemon trainerPokemon;
    public Pokemon enemyPokemon;
    public int xp;
    public StringBuilder console = new StringBuilder();
    Location location;

    public PokemonBattle(Trainer trainer, boolean isWildPokemon, Location location) {
        this.trainer = trainer;
        this.location = trainer.getCurrentLocation();
        this.trainerPokemon = trainer.getSelectedPokemon();
        Object enemy = determineEnemyPokemon(isWildPokemon);
        if (enemy instanceof Pokemon) {
            this.enemyPokemon = (Pokemon) enemy;
        } else if (enemy instanceof List<?>) {
            List<Pokemon> gymLeaderPokemons = (List<Pokemon>) enemy;
            this.enemyPokemon = gymLeaderPokemons.get(0);
        } else {
            throw new IllegalStateException("Invalid enemy type encountered.");
        }
        this.xp = trainerPokemon.getXP();
    }

    public void battle() {
        Scanner scanner = new Scanner(System.in);
        while (trainerPokemon.getHP() > 0 && enemyPokemon.getHP() > 0) {
            System.out.println("Choose a move:");
            for (String move : trainerPokemon.getMoves()) {
                System.out.println("- " + move);
            }
            String chosenMove = scanner.nextLine();
            try {
                trainerPokemonAttack(chosenMove);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue; // Prompt the player to choose again
            }

            if (enemyPokemon.getHP() <= 0) {
                console.append("It's super effective!\n").append(enemyPokemon.getName()).append(" faints!\n");
                xp += 5 * enemyPokemon.getLevel();
                checkLevelUp();
                break;
            }

            enemyPokemonAttack();
            if (trainerPokemon.getHP() <= 0) {
                console.append(trainerPokemon.getName()).append(" faints!\n");
                break;
            }
        }
        scanner.close();
    }

    // allowed the player to pick the move
    public int trainerPokemonAttack(String chosenMove) {
        if (!trainerPokemon.getMoves().contains(chosenMove)) {
            throw new IllegalArgumentException("Invalid move: " + chosenMove);
        }
        console.append(trainerPokemon.getName()).append(" uses ").append(chosenMove).append("!");
        double effectiveness = calculateEffectiveness(trainerPokemon, enemyPokemon);
        int damage = (int) (trainerPokemon.getMoveDamage(chosenMove) * effectiveness);
        enemyPokemon.setHP(enemyPokemon.getHP() - damage);
        return enemyPokemon.getHP();
    }

    // made the enemy moves random
    public int enemyPokemonAttack() {
        Random random = new Random();
        int moveIndex = random.nextInt(enemyPokemon.getMoves().size());
        String move = enemyPokemon.getMoves().get(moveIndex);
        int damage = enemyPokemon.getMoveDamage(move);
        trainerPokemon.setHP(trainerPokemon.getHP() - damage);
        console.append(enemyPokemon.getName()).append(" uses ").append(move).append("!");
        return trainerPokemon.getHP();
    }

    public double calculateEffectiveness(Pokemon attacker, Pokemon defender) {
        double effectiveness = 1.0;
        for (String type : attacker.getStrengths()) {
            if (defender.getTypes().contains(type)) {
                effectiveness += 0.2;
            }
        }
        for (String type : attacker.getWeaknesses()) {
            if (defender.getTypes().contains(type)) {
                effectiveness -= 0.2;
            }
        }
        effectiveness = Math.max(0.2, Math.min(2.0, effectiveness));
        return effectiveness;
    }

    public void checkLevelUp() {
        int level = trainerPokemon.getLevel();
        if (level <= 10) {
            if (xp >= 100) {
                trainerPokemon.setLevel(level + 1);
                xp = 0;
                trainerPokemon.updateMoveDamages();
            }
        } else if (level > 10 && level < 20) {
            if (xp >= 200) {
                trainerPokemon.setLevel(level + 1);
                xp = 0;
                trainerPokemon.updateMoveDamages();
            }
        } else if (level > 20 && level < 30) {
            if (xp >= 300) {
                trainerPokemon.setLevel(level + 1);
                xp = 0;
                trainerPokemon.updateMoveDamages();
            }
        }
    }

    // Trainer Pokemon
    // Determine the enemy Pokemon based on the current location
    public Object determineEnemyPokemon(boolean isWildPokemon) {
        Location location = trainer.getCurrentLocation();

        if (isWildPokemon) {
            List<Pokemon> wildPokemons = location.getWildPokemons();
            if (wildPokemons.isEmpty()) {
                throw new IllegalStateException("No wild Pokémon found in the current location.");
            }
            Random random = new Random();
            return wildPokemons.get(random.nextInt(wildPokemons.size()));
        } else {
            List<Pokemon> gymLeaderPokemons = location.getGymLeaderPokemons();
            if (!gymLeaderPokemons.isEmpty()) {
                return gymLeaderPokemons;
            } else {
                throw new IllegalStateException("No gym leader Pokémon found in the current location.");
            }
        }
    }

    public void challengeGymLeader(Trainer trainer, Location location) {
        try {
            List<Pokemon> gymLeaderPokemons = location.getGymLeaderPokemons();
            if (!gymLeaderPokemons.isEmpty()) {
                for (Pokemon gymLeaderPokemon : gymLeaderPokemons) {
                    PokemonBattle gymLeaderBattle = new PokemonBattle(trainer, false, location);
                    gymLeaderBattle.battle();
                    console.append(gymLeaderBattle.console.toString());
                }
            } else {
                console.append("No gym leader Pokémon found in Cerulean City.\n");
            }
        } catch (IllegalStateException e) {
            console.append("No available Pokémon to battle.\n");
        }
    }

    public void fightWildPokemon(Trainer trainer, Location location) {
        try {
            List<Pokemon> wildPokemons = location.getWildPokemons();
            if (!wildPokemons.isEmpty()) {
                Pokemon wildPokemon = wildPokemons.get(0);
                PokemonBattle wildPokemonBattle = new PokemonBattle(trainer, true, location);
                wildPokemonBattle.battle();
                console.append(wildPokemonBattle.console.toString());
            } else {
                console.append("No wild Pokémon found in Cerulean City.\n");
            }
        } catch (IllegalStateException e) {
            console.append("No available Pokémon to battle.\n");
        }
    }

    // Trainer Pokemon methods
    public Pokemon getTrainerPokemon() {
        return trainer.getSelectedPokemon();
    }

    public String getTrainerPokemonName() {
        return getTrainerPokemon().getName();
    }

    public Map<String, Integer> getTrainerPokemonDamage() {
        return getTrainerPokemon().getMoveDamages();
    }

    public int getTrainerPokemonHP() {
        return getTrainerPokemon().getHP();
    }

    public int getTrainerPokemonLevel() {
        return getTrainerPokemon().getLevel();
    }

    public int getXp() {
        return getTrainerPokemon().getXP();
    }

    public List<String> getTrainerPokemonStrengths() {
        return getTrainerPokemon().getStrengths();
    }

    public List<String> getTrainerPokemonWeaknesses() {
        return getTrainerPokemon().getWeaknesses();
    }

    public List<String> getTrainerPokemonMoves() {
        return getTrainerPokemon().getMoves();
    }

    // Enemy Pokemon methods
    public Pokemon getEnemyPokemon() {
        return enemyPokemon;
    }

    public String getEnemyPokemonName() {
        return enemyPokemon.getName();
    }

    public int getEnemyPokemonHP() {
        return enemyPokemon.getHP();
    }

    public int getEnemyPokemonDamage(String move) {
        return enemyPokemon.getMoveDamage(move);
    }

    public List<String> getEnemyPokemonMoves() {
        return enemyPokemon.getMoves();
    }

    public int getEnemyPokemonLevel() {
        return enemyPokemon.getLevel();
    }

    public String getEnemyPokemonType() {
        return String.join(", ", enemyPokemon.getTypes());
    }

}
