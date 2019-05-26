package app.IO;

import app.Author;
import app.Book;
import app.Category;
import app.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterToFileFromList {

    private static FileWriterToFileFromList instance;

    private FileWriterToFileFromList() {
    }

    public void saveChangesInBooksListToCsvFile(List<Book> books, String pathToBooksFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(pathToBooksFile))) {
            for (Book book : books) {
                bufferedWriter.write(book.bookToCsv() + "\n");
            }
            System.out.println("Poprawnie zapisano listę książek.");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać listy książek.");
        }
    }

    public void saveChangesInAuthorsListToCsvFile(List<Author> authors, String pathToAuthorsFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(pathToAuthorsFile))) {
            for (Author author : authors) {
                bufferedWriter.write(author.authorToCsv() + "\n");
            }
            System.out.println("Poprawnie zapisano listę książek.");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać listy książek.");
        }
    }

    public void saveChangesInCategoriesListToCsvFile(List<Category> categories, String pathToCategoriesFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(pathToCategoriesFile))) {
            for (Category category : categories) {
                bufferedWriter.write(category.categoryToCsv() + "\n");
            }
            System.out.println("Poprawnie zapisano listę kategorii.");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać listy kategorii.");
        }
    }

    public void addUserToFile(String userName, String password, List<User> usersList, String pathToUsersFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(pathToUsersFile, true))) {
            if (usersList.size() <= 1) {
                bufferedWriter.write(userName + ";" + password);
            } else {
                bufferedWriter.write("\n" + userName + ";" + password);
            }
            System.out.println("Poprawnie zarejestrowano użytkownika");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać użytkownika");
        }
    }

    public static FileWriterToFileFromList getInstance() {
        if (instance == null) {
            synchronized (FileWriterToFileFromList.class) {
                instance = new FileWriterToFileFromList();
            }
        }
        return instance;
    }
}
