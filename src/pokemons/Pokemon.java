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
    int baseHP;

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
        this.HP = 0;
        this.moveDamages = moveDamages;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.moves = new ArrayList<>(moveDamages.keySet());
        this.XP = 0;
        this.isWildPokemon = false;
        this.isGymLeaderPokemon = false;
        this.location = null;
        setBaseHP(40);
        getMaxHP();
    }

    // Update the damage count of the moves after levelling up
    public void updateMoveDamages() {
        for (String move : moveDamages.keySet()) {
            int baseDamage = moveDamages.get(move);
            int adjustedDamage = baseDamage;
            if (level >= 5 && level<= 9) {
                adjustedDamage += 2;
            }
            if (level >= 10 && level < 20) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXP() {
        return XP;
    }
    
    public int getMaxHP(){
        return baseHP + level * 5;
    }
    
    public void setBaseHP(int baseHP){
        this.baseHP = baseHP;
    }
    public int getBaseHP(){
        return baseHP;
    }

    public void setXP(int xp) {
        this.XP = xp;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public ArrayList<String> getMoves() {
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
        ret += "\n    HP: " + getMaxHP() + "\n"
                + "    XP: " + XP + "\n"
                + "    Move Damages: \n";
        for (Map.Entry<String, Integer> entry : moveDamages.entrySet()) {
            ret += "    - " + entry.getKey() + " [Damage " + entry.getValue() + "]\n";
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
