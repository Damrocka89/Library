package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class FileReaderFromFileToList {

     FileReaderFromFileToList() {
    }

    void readAuthorsFromFile(List<Author> authors) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\authors.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::createAuthorFromDataArray)
                    .forEach(authors::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy autorów.");
        }
    }

    private Author createAuthorFromDataArray(String[] arrayOfAuthorsData) {
        return new Author(Integer.parseInt(arrayOfAuthorsData[0]), arrayOfAuthorsData[1], Integer.parseInt(arrayOfAuthorsData[2]));
    }

    void readCathegoriesFromFile(List<Category> cathegories) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\categories.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::getCategoryFromDataArray)
                    .forEach(cathegories::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy kategorii.");
        }
    }

    public Category getCategoryFromDataArray(String[] arrayOfCategoryData) {
        return new Category(Integer.parseInt(arrayOfCategoryData[0]), arrayOfCategoryData[1], Integer.parseInt(arrayOfCategoryData[2]));
    }

    void readUsersAndPasswords(List<User> usersList) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\users.txt"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::getUserFromDataArray)
                    .forEach(usersList::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować użytkowników.");
        }
    }

    private User getUserFromDataArray(String[] usersDataArray) {
        return new User(usersDataArray[0], usersDataArray[1]);
    }

    void readListOfBooksFromFile(List<Book> books, LibraryApp app) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\books.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(arrayOfBooksData-> getBookFromDataArray(app, arrayOfBooksData))
                    .forEach(books::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy książek.");
        }
    }

    private Book getBookFromDataArray(LibraryApp app, String[] arrayOfBooksData) {
        return new Book(Integer.parseInt(arrayOfBooksData[0]), arrayOfBooksData[1], arrayOfBooksData[2], Integer.parseInt(arrayOfBooksData[3]),
                        BookBindingType.valueOf(arrayOfBooksData[4].toUpperCase()), app.getAuthors(arrayOfBooksData[5]), app.getCathegory(arrayOfBooksData[6]));
    }

}
