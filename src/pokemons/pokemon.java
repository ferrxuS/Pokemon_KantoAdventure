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

public class Pokemon {

    String name;
    ArrayList<String> types = new ArrayList<>();
    int level;
    int HP;
    int XP;
    ArrayList<String> moves = new ArrayList<>();
    private Map<String, Integer> moveDamages = new HashMap<>();
    ArrayList<String> strengths = new ArrayList<>();
    ArrayList<String> weaknesses = new ArrayList<>();
    boolean isGymLeaderPokemon;
    boolean isWildPokemon;
    String location;

    // Constructor for wild or gym leader pokemon
    public Pokemon(String name, ArrayList<String> types, int level, int HP, Map<String, Integer> moveDamages, boolean isGymLeaderPokemon, boolean isWildPokemon, String location) {
        this.name = name;
        this.types = types;
        this.level = level;
        this.HP = HP;
        this.moveDamages = moveDamages;
        this.moves = new ArrayList<>(moveDamages.keySet());
        this.XP = 0;
        this.isGymLeaderPokemon = isGymLeaderPokemon;
        this.isWildPokemon = isWildPokemon;
        this.location = location;
    }

    // Constructor for trainer pokemon
    public Pokemon(String name, ArrayList<String> types, int level, Map<String, Integer> moveDamages, ArrayList<String> strengths, ArrayList<String> weaknesses) {
        this.name = name;
        this.types = types;
        this.level = level;
        this.moveDamages = moveDamages;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.moves = new ArrayList<>(moveDamages.keySet());
        this.XP = 0;
        this.isWildPokemon = false;
        this.isGymLeaderPokemon = false;
        this.location = null;
        calculateHP();
        updateMoveDamages();
    }

    public void calculateHP() {
        this.HP = 45 + 5 * level;
        if (level >= 10) {
            this.HP += 10;
        }
        if (level >= 20) {
            this.HP += 15;
        }
    }

    public void updateMoveDamages() {
        for (String move : moveDamages.keySet()) {
            int baseDamage = moveDamages.get(move);
            int adjustedDamage = baseDamage + 2 * level;
            if (level >= 10) {
                adjustedDamage += 3;
            }
            if (level >= 20) {
                adjustedDamage += 5;
            }
            moveDamages.put(move, adjustedDamage);
        }
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int xp) {
        this.XP = xp;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public List<String> getMoves() {
        return moves;
    }

    public Map<String, Integer> getMoveDamages() {
        return moveDamages;
    }

    public int getMoveDamage(String move) {
        return moveDamages.getOrDefault(move, 0);
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public List<String> getTypes() {
        return types;
    }

    public boolean isGymLeaderPokemon() {
        return isGymLeaderPokemon;
    }

    public boolean isWildPokemon() {
        return isWildPokemon;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String ret = "  +---------------------------------------------------------------------+  \n"
                + "    Your Pokémon: \n"
                + "    " + name + " - Level: " + level
                + "\n    Type: ";
        for (String type : types) {
            ret += type + "/";
        }
        ret = ret.substring(0, ret.length() - 1);
        ret += "\n    HP: " + HP + "\n"
                + "    XP: " + XP + "/100\n"
                + "    Moves: \n";
        for (String move : moves) {
            ret += "    - " + move + "\n";
        }
        ret += "    Strong against: \n";
        for (String strength : strengths) {
            ret += "    - " + strength + "\n";
        }
        ret += "    Weak against: \n";
        for (String weakness : weaknesses) {
            ret += "    - " + weakness + "\n";
        }
        ret += "  +---------------------------------------------------------------------+";
        return ret;
    }
}
