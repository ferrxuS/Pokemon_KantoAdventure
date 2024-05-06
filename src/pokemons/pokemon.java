/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemons;

import java.util.ArrayList;

/**
 *
 * @author ilsat (Noob)
 */
public class pokemon {
    
    String name;
    ArrayList<String> types = new ArrayList<>(); 
    int level;
    int HP;
    int XP;
    ArrayList<String> moves = new ArrayList<>();
    ArrayList<String> strengths = new ArrayList<>();
    ArrayList<String> weaknesses = new ArrayList<>();

    public pokemon(String name, ArrayList<String> types, int level, ArrayList<String> moves, ArrayList<String> strengths, ArrayList<String> weaknesses
    ) {
        this.name = name;
        this.types = types;
        this.level = level;
        this.HP = 45 + (7 * (level / 10)) + (5 * (level - 5 - (level / 10)));
        this.XP = 0;
        this.moves = moves;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
    }

    @Override
    public String toString() {
        String ret = "Your Pokémon: \n" +
        name + " - Level: " + level +
        "\nType: ";
        for (String type : types) {
            ret += type + "/";
        }
        ret = ret.substring(0, ret.length() - 1);
        ret += "\nHP: " + HP + "\n" +
        "XP: " + XP + "/100\n" +
        "Moves: \n";
        for (String move : moves) {
            ret += "- " + move + "\n";
        }
        ret += "Strong against: \n";
        for (String strength : strengths) {
            ret += "- " + strength + "\n";
        }
        ret += "Weak against: \n";
        for (String weakness : weaknesses) {
            ret += "- " + weakness + "\n";
        }
        return ret;
    }
}
