package sql_implementation;

import java.util.List;

public class Save {
    
    private int save_id;
    private String trainer_name;
    private String current_location;
    private List<String[]> pokemon_team;
    private List<String> gym_leaders_defeated;
    private List<String> badges;
    private String last_saved;
    
    public Save(int save_id, String trainer_name, String current_location, List<String[]> pokemon_team,
            List<String> gym_leaders_defeated, List<String> badges, String last_saved) {
        this.save_id = save_id;
        this.trainer_name = trainer_name;
        this.current_location = current_location;
        this.pokemon_team = pokemon_team;
        this.gym_leaders_defeated = gym_leaders_defeated;
        this.badges = badges;
        this.last_saved = last_saved;
    }

    public int getSave_id() {
        return save_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public List<String[]> getPokemon_team() {
        return pokemon_team;
    }

    public List<String> getGym_leaders_defeated() {
        return gym_leaders_defeated;
    }

    public List<String> getBadges() {
        return badges;
    }

    public String getLast_saved() {
        return last_saved;
    }

    public void printPokemonTeam() {
        for (String[] pokemon : pokemon_team) {
            System.out.println("Pokemon name: " + pokemon[0]);
            System.out.println("Pokemon level: " + pokemon[1]);
            System.out.println();
        }
    }

}
