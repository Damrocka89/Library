package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileReaderToList {

     FileReaderToList() {
    }

    boolean readAuthorsFromFile(List<Author> authors) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\authors.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings -> //TODO nazwz zmennej
                            authors.add(new Author(Integer.parseInt(arrayOfStrings[0]), arrayOfStrings[1], Integer.parseInt(arrayOfStrings[2])))); //TODO map i wyniesienie do metody
            return true;
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy autorów.");
            return false; //TODO do przemyslenia
        }
    }

     boolean readCathegoriesFromFile(List<Category> cathegories) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\categories.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            cathegories.add(new Category(Integer.parseInt(arrayOfStrings[0]), arrayOfStrings[1], Integer.parseInt(arrayOfStrings[2]))));
            return true;
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy kategorii.");
            return false;
        }
    }

     boolean readUsersAndPasswords(List<User> usersList) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\users.txt"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            usersList.add(new User(arrayOfStrings[0], arrayOfStrings[1])));
            return true;
        } catch (IOException e) {
            System.out.println("Nie udało się załadować użytkowników.");
            return false;
        }
    }
    void readListOfBooksFromFile(List<Book> books, LibraryApp app) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\books.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            books.add(new Book(Integer.parseInt(arrayOfStrings[0]), arrayOfStrings[1], arrayOfStrings[2], Integer.parseInt(arrayOfStrings[3]),
                                    BookBindingType.valueOf(arrayOfStrings[4].toUpperCase()), app.getAuthors(arrayOfStrings[5]), app.getCathegory(arrayOfStrings[6]))));
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy książek.");
        }
    }

}
