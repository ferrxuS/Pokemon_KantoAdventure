/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PokemonBattle_LevelUp;

import javax.swing.*;
import Trainer.Trainer;
import static Trainer.Trainer.*;
import java.util.*;
import pokemons.*;

/**
 *
 * @author raishahaque
 */
public class PokemonBattle {

    private Trainer trainer;
    private boolean isWildPokemon;
    private Pokemon trainerPokemon;
    private Pokemon enemyPokemon;
    private int xp;
    private Location location;
    private JTextArea console;
    private JTextField inputField;
    private boolean inputReceived;
    private Evolution evolution;
    private List<Pokemon> remainingGymLeaderPokemons;
    private int currentGymLeaderPokemonIndex;
    ArrayList<String> trainerPokemonList;
    String initialTrainerPokemonName;
    private int initialTrainerPokemonHP;

    // Constructor
    public PokemonBattle(Trainer trainer, boolean isWildPokemon, Location location, JTextArea console, JTextField inputField) {
        this.trainer = trainer;
        this.isWildPokemon = isWildPokemon;
        this.location = trainer.getCurrentLocation();
        this.console = console;
        this.inputField = inputField;
        this.trainerPokemon = trainer.getSelectedPokemon();
        this.evolution = new Evolution();
        remainingGymLeaderPokemons = new ArrayList<>(location.getGymLeaderPokemons());
        currentGymLeaderPokemonIndex = 0;
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
        this.inputReceived = false;
        initialTrainerPokemonName = trainerPokemon.getName();

        inputField.addActionListener(e -> {
            synchronized (inputField) {
                inputReceived = true;
                inputField.notify();
            }
        });

        System.out.println("Enemy Pokemon: " + this.enemyPokemon.getName()); // Debug statement
    }

    // Method for the battle function
    public void battle() {
        System.out.println("Battle started"); // Debug statement

        StringBuilder battleLog = new StringBuilder();

        System.out.println("checking hp1: " + initialTrainerPokemonHP); //debug
        initialTrainerPokemonHP = trainerPokemon.getMaxHP();
        trainerPokemon.setHP(initialTrainerPokemonHP);
        System.out.println("checking hp1: " + initialTrainerPokemonHP); //debug

        if (isWildPokemon) {
            initialWildPokemonBattleSetup();
        } else {
            initialGymLeaderBattleSetup();
        }

        new Thread(() -> {
            System.out.println("Battle loop thread started"); // Debug statement

            int roundCount = 1;
            try {
                while (trainerPokemon.getHP() > 0 && enemyPokemon.getHP() > 0) {
                    displayRoundInfo(roundCount, battleLog);
                    checkLevelUp(battleLog); // debug one
                    System.out.println("Level up 1");
                    SwingUtilities.invokeLater(() -> {
                        console.append(battleLog.toString());
                        console.setCaretPosition(console.getDocument().getLength());
                    });

                    waitForInput();

                    // Clear the battle log
                    battleLog.setLength(0);
                    String input = inputField.getText().toLowerCase();
                    inputField.setText("");
                    processMoveSelection(input, battleLog);

                    trainerPokemonAttack(trainerPokemon.getMoves().get(input.charAt(0) - 'a'), battleLog);

                    if (enemyPokemon.getHP() <= 0) {
                        handleEnemyPokemonFaint(battleLog);

                        if (!isWildPokemon && currentGymLeaderPokemonIndex < remainingGymLeaderPokemons.size() - 1) {
                            currentGymLeaderPokemonIndex++;
                            enemyPokemon = remainingGymLeaderPokemons.get(currentGymLeaderPokemonIndex);
                            battleLog.append("\n    The Gym Leader of ").append(enemyPokemon.getLocation()).append(" sent out ").append(enemyPokemon.getName()).append("! You have to defeat \n    all the Pokemons!\n");

                            System.out.println("checking hp2: " + initialTrainerPokemonHP); //debug
                            initialTrainerPokemonHP = trainerPokemon.getMaxHP();
                            trainerPokemon.setHP(initialTrainerPokemonHP);
                            System.out.println("checking hp2: " + initialTrainerPokemonHP); //debug

                            roundCount++;  // Increment roundCount here for new Pokemon
                            checkLevelUp(battleLog); // debug
                            System.out.println("Level up 2");
                            continue;  // Go to the next iteration of the while loop
                        } else {
                            if (!isWildPokemon) {
                                updateBadgesAndGymLeadersDefeated(enemyPokemon.getLocation());
                            }
                            checkLevelUp(battleLog); // debug
                            System.out.println("Level up 3");
                            break;
                        }
                    }

                    enemyPokemonAttack(battleLog);

                    if (trainerPokemon.getHP() <= 0) {
                        battleLog.append("    ").append(trainerPokemon.getName()).append(" faints!\n");
                        battleLog.append("\n  +---------------------------------------------------------------------+  \n");
                        break;
                    }

                    roundCount++;
                    checkLevelUp(battleLog); // debug
                    System.out.println("Level up 4");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a letter (a, b, c, d).\n");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("An unexpected error occurred: " + ex.getMessage());
            } finally {
                SwingUtilities.invokeLater(() -> {
                    console.append(battleLog.toString());
                    console.setCaretPosition(console.getDocument().getLength());
                    checkLevelUp(battleLog); // debug
                    System.out.println("Level up 5");
                });
            }
        }).start();
    }

    private void displayRoundInfo(int roundCount, StringBuilder battleLog) {
        battleLog.append("\n    Round ").append(roundCount).append(":\n");
        battleLog.append("\n    ").append(trainerPokemon.getName()).append("'s Moves:");
        List<String> moves = trainerPokemon.getMoves();
        for (int i = 0; i < moves.size(); i++) {
            String move = moves.get(i);
            int moveDamage = trainerPokemon.getMoveDamage(move);
            battleLog.append("\n    ").append((char) ('a' + i)).append(". ").append(move).append(" [Damage: ").append(moveDamage).append("]");
        }
        battleLog.append("\n");
        battleLog.append("\n    Which move will ").append(trainerPokemon.getName()).append(" use?\n");
        battleLog.append("\n  +---------------------------------------------------------------------+  \n");
    }

    // Process the move selection input from the user
    private void processMoveSelection(String input, StringBuilder battleLog) {
        int moveIndex = input.charAt(0) - 'a';
        if (moveIndex < 0 || moveIndex >= trainerPokemon.getMoves().size()) {
            throw new IllegalArgumentException("Invalid move selection!");
        }
        battleLog.append("  +---------------------------------------------------------------------+  \n");
        battleLog.append("    Your choice: ").append(input).append("\n  +---------------------------------------------------------------------+  \n");
    }

    private void handleEnemyPokemonFaint(StringBuilder battleLog) {
        battleLog.append("    ").append(enemyPokemon.getName()).append(" faints!\n");
        int xp = 5 * enemyPokemon.getLevel();
        trainerPokemon.setXP(trainerPokemon.getXP() + xp);
        battleLog.append("    ").append(trainerPokemon.getName()).append(" gained ").append(xp).append(" XP!");
        battleLog.append("    ").append(trainerPokemon.getName()).append(" [XP: ").append(trainerPokemon.getXP()).append("]\n");
        battleLog.append("\n  +---------------------------------------------------------------------+  \n");
        checkLevelUp(battleLog);
        System.out.println("Level up 5");
    }

    // Input handling logic
    private void waitForInput() {
        synchronized (inputField) {
            while (!inputReceived) {
                try {
                    inputField.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            inputReceived = false;
        }
    }

    public void initialGymLeaderBattleSetup() {
        StringBuilder battleLog = new StringBuilder();

        // Initial battle setup
        battleLog.append("  +---------------------------------------------------------------------+  \n");
        battleLog.append("    You are about to challenge Gym leader of ").append(location.getName()).append("\n");
        battleLog.append("    Prepare yourself for an insane battle! \n");
        battleLog.append("  +---------------------------------------------------------------------+  \n");

        // Check if the trainer's pokemon list contains more than one pokemon
        if (getPokemonList().size() > 1) {
            selectPokemonForBattle(battleLog);
        } else {
            battleLog.append("    Your pokemon: ").append(trainerPokemon.getName()).append(" [Level ").append(trainerPokemon.getLevel()).append("]\n");
            battleLog.append("  +---------------------------------------------------------------------+  \n");
            battleLog.append("    Battle Start: Trainer ").append(trainer.getTrainerName()).append(" vs. Gym Leader of ").append(enemyPokemon.getLocation());
            battleLog.append("\n    The Gym Leader sends out ").append(enemyPokemon.getName()).append(" [Level ").append(enemyPokemon.getLevel()).append("]\n");
            battleLog.append("\n");
            battleLog.append("    ").append(trainerPokemon.getName()).append(" is sent out! Its ").append(trainerPokemon.getTypes()).append(" type ").append(isStrongWeakAgainst()).append("\n    against ").append(enemyPokemon.getName()).append("'s ").append(enemyPokemon.getTypes()).append(" type!");
            battleLog.append("\n");
        }

        SwingUtilities.invokeLater(() -> {
            console.append(battleLog.toString());
            console.setCaretPosition(console.getDocument().getLength()); // Scroll to the bottom
        });
    }

    public void initialWildPokemonBattleSetup() {
        StringBuilder battleLog = new StringBuilder();

        // Initial battle setup
        battleLog.append("  +---------------------------------------------------------------------+  \n");
        battleLog.append("    You are about to Fight the Wild Pokemon of ").append(location.getName()).append("\n");
        battleLog.append("    Prepare yourself for an insane battle! \n");
        battleLog.append("  +---------------------------------------------------------------------+  \n");

        // Check if the trainer's pokemon list contains more than one pokemon
        if (getPokemonList().size() > 1) {
            selectPokemonForBattle(battleLog);
        } else {
            battleLog.append("    Your pokemon: ").append(trainerPokemon.getName()).append(" [Level ").append(trainerPokemon.getLevel()).append("]\n");
            battleLog.append("  +---------------------------------------------------------------------+  \n");
            battleLog.append("    Battle Start: Trainer ").append(trainer.getTrainerName()).append(" vs. Wild Pokemon of ").append(enemyPokemon.getLocation());
            battleLog.append("\n    ").append(enemyPokemon.getName()).append(" is sent out! [Level ").append(enemyPokemon.getLevel()).append("]\n");
            battleLog.append("\n");
            battleLog.append("    ").append(trainerPokemon.getName()).append(" is sent out! Its ").append(trainerPokemon.getTypes()).append(" type ").append(isStrongWeakAgainst()).append("\n    against ").append(enemyPokemon.getName()).append("'s ").append(enemyPokemon.getTypes()).append(" type!");
            battleLog.append("\n");
        }

        SwingUtilities.invokeLater(() -> {
            console.append(battleLog.toString());
            console.setCaretPosition(console.getDocument().getLength()); // Scroll to the bottom
        });
    }

    // To select the trainer pokemon for battle if there are more than one pokemon in trainer's team
    public void selectPokemonForBattle(StringBuilder battleLog) {

        battleLog.append("    Choose a pokemon for battle: \n");
        List<String> pokemonNames = trainer.getPokemonNames(); // Retrieve the list of Pokemon names
        for (String pokemonName : pokemonNames) {
            battleLog.append("    - ").append(pokemonName).append("\n"); // Append each Pokemon name individually
        }
        battleLog.append("\n    Enter the name of the Pokemon you want to select: ");
        battleLog.append("\n  +---------------------------------------------------------------------+  \n");

        SwingUtilities.invokeLater(() -> {
            console.append(battleLog.toString());
            console.setCaretPosition(console.getDocument().getLength());
        });

        waitForInput();

        // Clear the battle log
        battleLog.setLength(0);
        String selectedPokemonName = inputField.getText().trim().toLowerCase();
        inputField.setText("");

        List<String> ignoreCasePokemonNames = new ArrayList<>();
        for (String name : pokemonNames) {
            ignoreCasePokemonNames.add(name.toLowerCase());
        }

        // Validate the user input
        if (!ignoreCasePokemonNames.contains(selectedPokemonName)) {
            battleLog.append("    Invalid Pokemon name. Please enter a valid name from the list.\n");
            SwingUtilities.invokeLater(() -> {
                console.append(battleLog.toString());
                console.setCaretPosition(console.getDocument().getLength());
            });
            selectPokemonForBattle(battleLog); // Recursively prompt again for valid input
        } else {
            List<Pokemon> pokemonList = trainer.getPokemonList();
            for (Pokemon pokemon : pokemonList) {
                if (pokemon.getName().equalsIgnoreCase(selectedPokemonName)) {
                    trainer.setSelectedPokemon(pokemon);
                    this.trainerPokemon = pokemon;
                    break;
                }
            }

            SwingUtilities.invokeLater(() -> {
                console.append(battleLog.toString());
                console.setCaretPosition(console.getDocument().getLength());
            });
        }
    }

    // Trainer pokemon's attack logic
    public void trainerPokemonAttack(String chosenMove, StringBuilder battleLog) {
        if (!trainerPokemon.getMoves().contains(chosenMove)) {
            throw new IllegalArgumentException("Invalid move: " + chosenMove);
        }

        battleLog.append("\n    ").append(trainerPokemon.getName()).append(" uses ").append(chosenMove).append("!\n");
        double effectiveness = calculateEffectiveness(trainerPokemon, enemyPokemon);
        int damage = (int) (trainerPokemon.getMoveDamage(chosenMove) * effectiveness);
        enemyPokemon.setHP(enemyPokemon.getHP() - damage);
        battleLog.append("    ").append(enemyPokemon.getName()).append(" takes ").append(damage).append(" damage! [HP: ").append(enemyPokemon.getHP()).append("]\n");
    }

    // Enemy pokemon's attack logic
    public void enemyPokemonAttack(StringBuilder battleLog) {
        Random random = new Random();
        int moveIndex = random.nextInt(enemyPokemon.getMoves().size());
        String move = enemyPokemon.getMoves().get(moveIndex);
        int damage = enemyPokemon.getMoveDamage(move);
        trainerPokemon.setHP(trainerPokemon.getHP() - damage);
        battleLog.append("\n    ").append(enemyPokemon.getName()).append(" uses ").append(move).append("!\n");
        battleLog.append("    ").append(trainerPokemon.getName()).append(" takes ").append(damage).append(" damage! [HP: ").append(trainerPokemon.getHP()).append("]\n");
    }

    // Checks whether the trainer pokemon's type is strong or weak against the enemy pokemon's type
    public String isStrongWeakAgainst() {
        String trainerType = trainerPokemon.getTypes().get(0);
        List<String> enemyTypes = enemyPokemon.getTypes();

        switch (trainerType) {
            case "Grass":
                if (enemyTypes.contains("Water") || enemyTypes.contains("Rock") || enemyTypes.contains("Ground")) {
                    return "is Strong";
                } else if (enemyTypes.contains("Fire") || enemyTypes.contains("Poison") || enemyTypes.contains("Bug") || enemyTypes.contains("Ice")) {
                    return "is Weak";
                } else {
                    return "has no advantage";
                }
            case "Fire":
                if (enemyTypes.contains("Grass") || enemyTypes.contains("Bug") || enemyTypes.contains("Ice")) {
                    return "is Strong";
                } else if (enemyTypes.contains("Water") || enemyTypes.contains("Rock")) {
                    return "is Weak";
                } else {
                    return "has no advantage";
                }
            case "Water":
                if (enemyTypes.contains("Fire") || enemyTypes.contains("Rock")) {
                    return "is Strong";
                } else if (enemyTypes.contains("Electric") || enemyTypes.contains("Grass")) {
                    return "is Weak";
                } else {
                    return "has no advantage";
                }
            default:
                return "has no advantage";
        }
    }

    // Calculates attack effectiveness according to strenth and weakness
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

    // Method for levelling up using XP
    public void checkLevelUp(StringBuilder battleLog) {
        System.out.println("Level's Not Up Yet");
        int level = trainerPokemon.getLevel();
        boolean leveledUp = false;

        if (level >= 5 && level <= 9) {
            if (trainerPokemon.getXP() >= 100) {
                trainerPokemon.setLevel(level + 1);
                leveledUp = true;
                if (trainerPokemon.getXP() == 100) {
                    trainerPokemon.setXP(0);
                } else if (trainerPokemon.getXP() > 100) {
                    trainerPokemon.setXP(trainerPokemon.getXP() - 100);
                }
            }
        } else if (level >= 10 && level < 20) {
            if (trainerPokemon.getXP() >= 200) {
                trainerPokemon.setLevel(level + 1);
                leveledUp = true;
                if (trainerPokemon.getXP() == 200) {
                    trainerPokemon.setXP(0);
                } else if (trainerPokemon.getXP() > 200) {
                    trainerPokemon.setXP(trainerPokemon.getXP() - 200);
                }
            }
        } else if (level >= 20 && level < 30) { // Corrected this line to include level 20
            if (trainerPokemon.getXP() >= 300) {
                trainerPokemon.setLevel(level + 1);
                leveledUp = true;
                if (trainerPokemon.getXP() == 300) {
                    trainerPokemon.setXP(0);
                } else if (trainerPokemon.getXP() > 300) {
                    trainerPokemon.setXP(trainerPokemon.getXP() - 300);
                }
            }
        }

        if (leveledUp) {
            System.out.println("Levels up");
            trainerPokemon.updateMoveDamages();

            initialTrainerPokemonHP = trainerPokemon.getMaxHP();
            if (trainerPokemon.getLevel() >= 5 && trainerPokemon.getLevel() <= 9) {
                trainerPokemon.setHP(initialTrainerPokemonHP);
            } else if (trainerPokemon.getLevel() >= 10 && trainerPokemon.getLevel() < 20) {
                trainerPokemon.setHP(initialTrainerPokemonHP + 5);
            } else if (trainerPokemon.getLevel() >= 20) {
                trainerPokemon.setHP(initialTrainerPokemonHP + 10);
            }
            System.out.println("level hp: " + initialTrainerPokemonHP); //debug

            battleLog.append("    Congrats! ").append(trainerPokemon.getName()).append(" moves to level ").append(trainerPokemon.getLevel()).append(" !\n");
            battleLog.append("    ").append(trainerPokemon.getName()).append(" [XP: ").append(trainerPokemon.getXP()).append("]\n");
            battleLog.append("  +---------------------------------------------------------------------+  \n");

            // Check whether the trainer pokemon has obtained the certain level for evolution
            if (evolution.evolve(trainerPokemon)) {

                battleLog.append("    Congratulations! Your Pokemon has evolved to ").append(trainerPokemon.getName()).append("!!!");
                battleLog.append("\n  +---------------------------------------------------------------------+  \n");

                if (trainerPokemon.getLevel() == 30) {
                    battleLog.append("    Your team is complete!\n");
                } else {

                    addPokemonToTeam(battleLog);
                }
            }
        }
    }

    public void setTrainerPokemonList() {
        trainerPokemonList = new ArrayList<>();
        trainerPokemonList.add("Bulbasaur");
        trainerPokemonList.add("Squirtle");
        trainerPokemonList.add("Charmander");
    }

    // Unlock and add a new pokemon to the trainer's team after every evolution
    private void addPokemonToTeam(StringBuilder battleLog) {
        setTrainerPokemonList();
        List<String> availablePokemon = new ArrayList<>(trainerPokemonList);
        availablePokemon.remove(initialTrainerPokemonName);
        String chosenPokemon = availablePokemon.get(availablePokemon.size() - 1);
        trainer.addToList(chosenPokemon);
        battleLog.append("    You unlocked ").append(availablePokemon.remove(availablePokemon.size() - 1)).append("!!!\n    It is added to your team!!!");
        battleLog.append("\n  +---------------------------------------------------------------------+  \n");
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

    // Method to challenge the gym leader
    public void challengeGymLeader(Trainer trainer, Location location) {
        try {
            List<Pokemon> gymLeaderPokemons = location.getGymLeaderPokemons();
            if (!gymLeaderPokemons.isEmpty()) {
                for (Pokemon gymLeaderPokemon : gymLeaderPokemons) {
                    PokemonBattle gymLeaderBattle = new PokemonBattle(trainer, false, location, console, inputField);
                    gymLeaderBattle.battle();
                }
            } else {
                console.append("No gym leader Pokémon found in Cerulean City.\n");
            }
        } catch (IllegalStateException e) {
            console.append("No available Pokémon to battle.\n");
        }
    }

    // Method to initiate battle with the wild pokemon
    public void fightWildPokemon(Trainer trainer, Location location) {
        try {
            List<Pokemon> wildPokemons = location.getWildPokemons();
            if (!wildPokemons.isEmpty()) {
                Pokemon wildPokemon = wildPokemons.get(0);
                PokemonBattle wildPokemonBattle = new PokemonBattle(trainer, true, location, console, inputField);
                wildPokemonBattle.battle();
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
}
