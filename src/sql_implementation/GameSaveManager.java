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
    public void saveGame(int account_id, int save_id, String trainerName, String current_location, String pokemon_team, String gym_leaders_defeated) {

        String query = "INSERT INTO Game_Save (save_id, account_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated, last_saved) VALUES (?, ?, ?, ?, ?, ?, datetime('now')) ON CONFLICT(save_id) DO UPDATE SET current_location = excluded.current_location, pokemon_team = excluded.pokemon_team, gym_leaders_defeated = excluded.gym_leaders_defeated, last_saved = datetime('now')";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, save_id);
            pstmt.setInt(2, account_id);
            pstmt.setString(3, trainerName);
            pstmt.setString(4, current_location);
            pstmt.setString(5, pokemon_team);
            pstmt.setString(6, gym_leaders_defeated);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    // Method to load a game save
    public Save loadSave(int save_id) {
        
        String query = "SELECT * FROM Game_Save WHERE save_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, save_id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Save found
                String trainer_name = rs.getString("trainer_name");
                String current_location = rs.getString("current_location");
                ArrayList<String[]> pokemon_team = jsonObjectToListArray(rs.getString("pokemon_team")); 
                ArrayList<String> gym_leaders_defeated = jsonArrayToListString(rs.getString("gym_leaders_defeated"));
                ArrayList<String> badges = jsonArrayToListString(rs.getString("badges"));
                String last_saved = rs.getString("last_saved");
                Save save = new Save(save_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated, badges, last_saved);
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

    public boolean saveExists(int save_id) {

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

    public static ArrayList<String[]> jsonObjectToListArray (String json) {
        ArrayList<String[]> listArray = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String[] object = new String[2];
                object[0] = jsonObject.getString("name");
                object[1] = String.valueOf(jsonObject.getInt("level"));
                listArray.add(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listArray;
    }

    public static ArrayList<String> jsonArrayToListString(String json) {
        ArrayList<String> listString = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                listString.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listString;
    }

}
