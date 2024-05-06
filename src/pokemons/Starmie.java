// Cerulean Gym Leader Pokemon 2

package pokemons;

import java.util.ArrayList;
import java.util.List;

public class Starmie extends pokemon {

    public Starmie() {
        super("Starmie", new ArrayList<>(List.of("Water", "Psychic")), 10,
                new ArrayList<>(List.of("Brine", "Cosmic Power")),
                new ArrayList<>(List.of("Fire", "Ground", "Rock")),
                new ArrayList<>(List.of("Electric", "Grass", "Bug")));
    }

}