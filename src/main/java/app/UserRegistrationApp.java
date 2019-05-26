package app;

import app.IO.FileWriterToFileFromList;

import java.util.List;
import java.util.Scanner;

class UserRegistrationApp {

    private List<User> usersList;
    private Scanner scanner=new Scanner(System.in);

     UserRegistrationApp(List<User> users) {
        this.usersList = users;
    }

     void registerNewUser(FileWriterToFileFromList fileWriter) {
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
         fileWriter.addUserToFile(userName, password, usersList);
     }

     boolean isOnTheListOfUsers(String username) {
        return usersList.stream()
                .anyMatch(user -> user.isUsernameValid(username));
    }



    private String addingPassword() {
         String password="";
         boolean isCorrectlyCreatedPassword=false;

         while (!isCorrectlyCreatedPassword) {

             password = getPasswordLongEnough();

             System.out.println("Powtórz hasło:");
             if (!scanner.nextLine().trim().equals(password)) {
                 System.out.println("Niepoprawnie.");
             }else{
                 isCorrectlyCreatedPassword=true;
             }
         }
        return password;
    }

    private String getPasswordLongEnough() {
        String password="";
        boolean isPasswordTooShort=true;

        while (isPasswordTooShort) {
            System.out.println("Podaj hasło (co najmniej 3 litery):");
            password = scanner.nextLine().trim();
            if (password.length() < 3) {
                System.out.println("Hasło za krótkie.");
            }else{
                isPasswordTooShort=false;
            }
        }
        return password;
    }
}
