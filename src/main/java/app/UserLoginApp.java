package app;

import java.util.List;
import java.util.Scanner;

public class UserLoginApp {

    private List<User> userList;
    private Scanner scanner=new Scanner(System.in);

    public UserLoginApp(List<User> userList) {
        this.userList = userList;
    }

     boolean login(UserRegistrationApp userRegistrationApp) {
        System.out.println("Podaj nazwę użytkownika:");
        String username = scanner.nextLine().trim();
        if (userRegistrationApp.isOnTheListOfUsers(username)) {
          return checkIfPasswordIsCorrect(username);
        } else {
            System.out.println("Taki użytkownik nie istnieje.");
            return false;
        }
    }
    private boolean checkIfPasswordIsCorrect(String username) {
        boolean back = false;
        while (!back) {
            System.out.println("Podaj hasło lub cofnij (Q):");
            String password = scanner.nextLine().trim();
            if (password.equalsIgnoreCase("q")) {
                return false;
            } else if (!isPasswordCorrect(username, password)) {
                System.out.println("Nieprawidłowe hasło.");
            } else {
                back = true;
            }
        }
        return true;
    }

    private boolean isPasswordCorrect(String username, String password) {
        for (User user : userList) {
            if (user.checkPassword(username, password)) {
                return true;
            }
        }
        return false;
    }



}
