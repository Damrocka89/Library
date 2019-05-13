package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterFromList {

     FileWriterFromList() {
    }

    boolean saveChangesInBooksListToCsvFile(List<Book> books) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\books.csv"))) {
            for (Book book : books) {
                bufferedWriter.write(book.bookToCsv() + "\n");
            }
            System.out.println("Poprawnie zapisano listę książek.");
            return true;
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać listy książek.");
            return false;
        }
    }

    boolean addUserToFile(String userName, String password, List<User> usersList) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\users.txt", true))) {
            if (usersList.size() <= 1) {
                bufferedWriter.write(userName + ";" + password);
            } else {
                bufferedWriter.write("\n" + userName + ";" + password);
            }
            System.out.println("Poprawnie zarejestrowano użytkownika");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać użytkownika");
            return true;
        }
        return false;
    }
}
