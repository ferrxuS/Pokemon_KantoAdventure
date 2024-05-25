package sql_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {

    // Method to log into existing account
    public User loginUser (String username, String password) {

        String query = "SELECT * FROM Account WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Username found
                String storedHash = rs.getString("password_hash");
                String salt = rs.getString("salt");
                String hashedPassword = PasswordUtils.hashPassword(password, salt);

                if (storedHash.equals(hashedPassword)) {
                    // Password matches
                    int account_id = rs.getInt("account_id");
                    User loggedInUser = new User(account_id, username);
                    return loggedInUser;
                } else {
                    // Password does not match
                    System.out.println("Incorrect password!");
                    return null;
                }
            } else {
                // Username not found
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Method to create a new account
    public boolean registerUser (String username, String password) {

        String salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        String query = "INSERT INTO Account (username, password_hash, salt) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, salt);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
