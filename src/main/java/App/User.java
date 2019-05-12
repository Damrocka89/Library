package App;

public class User {

    private String username;
    private String password;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }




    protected boolean checkPassword(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    protected boolean isUsernameValid(String username) {
       return this.username.equals(username);

    }
}
