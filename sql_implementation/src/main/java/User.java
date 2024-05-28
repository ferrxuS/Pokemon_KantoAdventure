public class User {

    private int account_id;
    private String username;

    public User(int account_id, String username) {
        this.account_id = account_id;
        this.username = username;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getUsername() {
        return username;
    }
    
}
