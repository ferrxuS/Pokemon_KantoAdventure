package sql_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameSaveManager {

    // Method to save a game (create or overwrite)
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
    public boolean loadSave(int save_id) {
        
        String query = "SELECT * FROM Game_Save WHERE save_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, save_id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Save loaded
                return true;
            } else {
                // Save not found
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
