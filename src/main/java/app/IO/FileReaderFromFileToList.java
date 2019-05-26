package app.IO;

import app.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderFromFileToList {

    private static FileReaderFromFileToList instance;

    private FileReaderFromFileToList() {

    }


    public List<Author> readAuthorsFromFile(String pathToAuthorsFile) {
        List<Author> authors = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToAuthorsFile))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::createAuthorFromDataArray)
                    .forEach(authors::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy autorów.");
        }
        return authors;
    }

    private Author createAuthorFromDataArray(String[] arrayOfAuthorsData) {
        return new Author(Integer.parseInt(arrayOfAuthorsData[0]), arrayOfAuthorsData[1], Integer.parseInt(arrayOfAuthorsData[2]));
    }

    public List<Category> readCathegoriesFromFile(String pathToCategoriesFile) {
        List<Category> cathegories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToCategoriesFile))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::getCategoryFromDataArray)
                    .forEach(cathegories::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy kategorii.");
        }
        return cathegories;
    }

  private  Category getCategoryFromDataArray(String[] arrayOfCategoryData) {
        return new Category(Integer.parseInt(arrayOfCategoryData[0]), arrayOfCategoryData[1], Integer.parseInt(arrayOfCategoryData[2]));
    }

    public List<User> readUsersAndPasswords(String pathToUsersFile) {
        List<User> usersList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToUsersFile))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::getUserFromDataArray)
                    .forEach(usersList::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować użytkowników.");
        }
        return usersList;
    }

    private User getUserFromDataArray(String[] usersDataArray) {
        return new User(usersDataArray[0], usersDataArray[1]);
    }

    public List<Book> readListOfBooksFromFile(List<Category> categories, List<Author> authors, String pathToBooksFile) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToBooksFile))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(arrayOfBooksData -> getBookFromDataArray(arrayOfBooksData, categories, authors))
                    .forEach(books::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy książek.");
        }
        return books;
    }

    private Book getBookFromDataArray(String[] arrayOfBooksData, List<Category> categories, List<Author> authors) {
        return new Book(Integer.parseInt(arrayOfBooksData[0]), arrayOfBooksData[1], arrayOfBooksData[2], Integer.parseInt(arrayOfBooksData[3]),
                BookBindingType.valueOf(arrayOfBooksData[4].toUpperCase()), getAuthors(arrayOfBooksData[5], authors), getCathegory(arrayOfBooksData[6], categories));
    }

    public Category getCathegory(String idOfCathegory, List<Category> categories) {
        for (Category category : categories) {
            if (category.getCategoryId() == Integer.parseInt(idOfCathegory)) {
                return category;
            }
        }
        return null;
    }

  private  List<Author> getAuthors(String authors, List<Author> authorsList) {
        return Arrays.stream(authors.split(","))
                .map(id -> getAuthorById(Integer.parseInt(id), authorsList))
                .collect(Collectors.toList());
    }

    private Author getAuthorById(int id, List<Author> authors) {
        for (Author author : authors) {
            if (author.getAuthorsId() == id) {
                return author;
            }
        }
        return null;
    }

    public static FileReaderFromFileToList getInstance() {
        if (instance == null) {
            synchronized (FileReaderFromFileToList.class) {
                instance = new FileReaderFromFileToList();
            }
        }
        return instance;
    }
}
