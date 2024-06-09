package sql_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameSaveManager {

    // Method to save a game (create new or overwrite)
    public boolean saveGame(Save save) {

        // Query to insert or update a game save
        String query = "INSERT INTO Game_Save (save_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated, badges, last_saved) VALUES (?, ?, ?, ?, ?, ?, datetime('now')) ON CONFLICT(save_id) DO UPDATE SET trainer_name = excluded.trainer_name, current_location = excluded.current_location, pokemon_team = excluded.pokemon_team, gym_leaders_defeated = excluded.gym_leaders_defeated, badges = excluded.badges, last_saved = datetime('now')";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, save.getSave_id());
            pstmt.setString(2, save.getTrainer_name());
            pstmt.setString(3, save.getCurrent_location());
            String JSON_pokemon_team = listArrayToJsonObject(new ArrayList<>(save.getPokemon_team()));
            System.out.println(JSON_pokemon_team);
            pstmt.setString(4, JSON_pokemon_team);
            String JSON_gym_leaders_defeated = listStringToJsonArray(new ArrayList<>(save.getGym_leaders_defeated()));
            pstmt.setString(5, JSON_gym_leaders_defeated);
            String JSON_badges = listStringToJsonArray(new ArrayList<>(save.getBadges()));
            pstmt.setString(6, JSON_badges);

            int rowsAffected = pstmt.executeUpdate();

            return (rowsAffected > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Method to load a game save
    public Save loadSave(int save_id) {
        // Query to select a game save by save_id
        String query = "SELECT * FROM Game_Save WHERE save_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, save_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Save found, extract data from the result set
                String trainer_name = rs.getString("trainer_name");
                String current_location = rs.getString("current_location");
                ArrayList<String[]> pokemon_team = jsonObjectToListArray(rs.getString("pokemon_team"));
                ArrayList<String> gym_leaders_defeated = jsonArrayToListString(rs.getString("gym_leaders_defeated"));
                ArrayList<String> badges = jsonArrayToListString(rs.getString("badges"));
                String last_saved = rs.getString("last_saved");
                Save save = new Save(save_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated,
                        badges, last_saved);
                return save;
            } else {
                // Save not found
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Method to delete a game save
    public boolean deleteSave(int save_id) {
        // Query to delete a game save by save_id
        String query = "DELETE FROM Game_Save WHERE save_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, save_id);

            int rowsAffected = pstmt.executeUpdate();

            return (rowsAffected > 0);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if a game save exists
    public boolean saveExists(int save_id) {
        // Query to count the number of game saves with a specific save_id
        String query = "SELECT COUNT(*) as count FROM Game_Save WHERE save_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, save_id);

            ResultSet rs = pstmt.executeQuery();
            int saveCount = rs.getInt("count");
            if (saveCount > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper methods:

    // Method to convert JSON string to list of string arrays
    public static ArrayList<String[]> jsonObjectToListArray(String json) {
        ArrayList<String[]> listArray = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String level = String.valueOf(jsonObject.getInt("level"));
                String hp = String.valueOf(jsonObject.getInt("hp"));
                String xp = String.valueOf(jsonObject.getInt("xp"));

                // Extract moves array
                JSONArray movesArray = jsonObject.getJSONArray("moves");
                JSONObject move1 = movesArray.getJSONObject(0);
                JSONObject move2 = movesArray.getJSONObject(1);
                String move1Name = move1.getString("name");
                String move1Damage = String.valueOf(move1.getInt("damage"));
                String move2Name = move2.getString("name");
                String move2Damage = String.valueOf(move2.getInt("damage"));

                String[] object = new String[] { name, level, hp, xp, move1Name, move1Damage, move2Name, move2Damage };

                listArray.add(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listArray;
    }

    // Method to convert JSON string to list of strings
    public static ArrayList<String> jsonArrayToListString(String json) {
        ArrayList<String> listString = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                listString.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listString;
    }

    // Method to convert list of string arrays to JSON string
    public static String listArrayToJsonObject(ArrayList<String[]> listArray) {
        JSONArray jsonArray = new JSONArray();

        for (String[] object : listArray) {

            JSONObject jsonObject = new JSONObject();

            try {

                jsonObject.put("name", object[0]);
                jsonObject.put("level", Integer.parseInt(object[1]));
                jsonObject.put("hp", Integer.parseInt(object[2]));
                jsonObject.put("xp", Integer.parseInt(object[3]));
    
                JSONArray movesArray = new JSONArray();
                JSONObject move1 = new JSONObject();
                move1.put("name", object[4]);
                move1.put("damage", Integer.parseInt(object[5]));
                movesArray.put(move1);
    
                JSONObject move2 = new JSONObject();
                move2.put("name", object[6]);
                move2.put("damage", Integer.parseInt(object[7]));
                movesArray.put(move2);
    
                jsonObject.put("moves", movesArray);
    
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonArray.toString();
    }

    // Method to convert list of strings to JSON string
    public static String listStringToJsonArray(ArrayList<String> listString) {
        JSONArray jsonArray = new JSONArray();

        for (String str : listString) {
            jsonArray.put(str);
        }

        return jsonArray.toString();
    }

}
