package app;

class User {

    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }




    boolean checkPassword(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    boolean isUsernameValid(String username) {
       return this.username.equals(username);

    }
}
