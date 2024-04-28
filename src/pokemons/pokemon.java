/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemons;

/**
 *
 * @author ilsat
 */
public class pokemon {
    String name;
    String type;
    int level;
    int HP;
    int XP;
    String move1;
    String move2;
    String strong1;
    String strong2;
    String strong3;
    String weak1;
    String weak2;
    String weak3;
    
    public pokemon(String name, String type, int level, int HP, int XP,
                   String move1, String move2, String strong1, String strong2, String strong3,
                   String weak1, String weak2, String weak3) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.HP = HP;
        this.XP = XP;
        this.move1 = move1;
        this.move2 = move2;
        this.strong1 = strong1;
        this.strong2 = strong2;
        this.strong3 = strong3;
        this.weak1 = weak1;
        this.weak2 = weak2;
        this.weak3 = weak3;
    }
    
    @Override
    public String toString() {
        return "Your Pokemon: \n" + 
               name + " - Level: " + level +
               "\nType: " + type + "\n" +
               "HP: " + HP + "\n" +
               "XP: " + XP + "\n" +
               "Moves: \n" +
               "- " + move1 + "\n" +
               "- " + move2 + "\n" +
               "Strong against: \n" +
               "- " + strong1 + "\n" +
               "- " + strong2 + "\n" +
               "- " + strong3 + "\n" +
               "Weak against: \n" +
               "- " + weak1 + "\n" +
               "- " + weak2 + "\n" +
               "- " + weak3;
    }
}
