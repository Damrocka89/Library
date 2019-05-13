package app;

import java.util.List;
import java.util.Scanner;

public class UserRegistrationApp {

    private List<User> usersList;
    Scanner scanner=new Scanner(System.in);

     UserRegistrationApp(List<User> users) {
        this.usersList = users;
    }

     boolean registerNewUser(FileWriterFromList fileWriter) {
        String userName="";
        boolean isAlreadyExistingUser=true;
        while (isAlreadyExistingUser) {
            System.out.println("Podaj nazwę uzytkownika:");
             userName = scanner.nextLine().trim();
            if (isOnTheListOfUsers(userName)) {
                System.out.println("Taki użytkownik już istnieje.");
            }else{
                isAlreadyExistingUser=false;
            }
        }
        String password = addingPassword();
        usersList.add(new User(userName, password));
        return fileWriter.addUserToFile(userName, password, usersList);
    }

     boolean isOnTheListOfUsers(String username) {
        return usersList.stream()
                .anyMatch(user -> user.isUsernameValid(username));
    }



    private String addingPassword() {
        System.out.println("Podaj hasło (co najmniej 3 litery):");
        String password = scanner.nextLine().trim();
        if (password.length() < 3) {
            System.out.println("Hasło za krótkie.");
            addingPassword();
        }
        System.out.println("Powtórz hasło:");
        if (!scanner.nextLine().trim().equals(password)) {
            System.out.println("Niepoprawnie.");
            addingPassword();
        }
        return password;
    }
}
