package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FileReaderFromFileToList {

    private List<Author> authors;
    private List<Category> categories;

    private static FileReaderFromFileToList instance;

    private FileReaderFromFileToList() {
        authors = readAuthorsFromFile();
        categories = readCathegoriesFromFile();
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    List<Author> readAuthorsFromFile() {
        List<Author> authors = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\authors.csv"))) {
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

    List<Category> readCathegoriesFromFile() {
        List<Category> cathegories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\categories.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(this::getCategoryFromDataArray)
                    .forEach(cathegories::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy kategorii.");
        }
        return cathegories;
    }

     Category getCategoryFromDataArray(String[] arrayOfCategoryData) {
        return new Category(Integer.parseInt(arrayOfCategoryData[0]), arrayOfCategoryData[1], Integer.parseInt(arrayOfCategoryData[2]));
    }

    List<User> readUsersAndPasswords() {
        List<User> usersList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\users.txt"))) {
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

    List<Book> readListOfBooksFromFile() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\books.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .map(arrayOfBooksData -> getBookFromDataArray(arrayOfBooksData))
                    .forEach(books::add);
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy książek.");
        }
        return books;
    }

    private Book getBookFromDataArray(String[] arrayOfBooksData) {
        return new Book(Integer.parseInt(arrayOfBooksData[0]), arrayOfBooksData[1], arrayOfBooksData[2], Integer.parseInt(arrayOfBooksData[3]),
                BookBindingType.valueOf(arrayOfBooksData[4].toUpperCase()), getAuthors(arrayOfBooksData[5]), getCathegory(arrayOfBooksData[6]));
    }

    Category getCathegory(String idOfCathegory) {
        for (Category category : categories) {
            if (category.getCategoryId() == Integer.parseInt(idOfCathegory)) {
                return category;
            }
        }
        return null;
    }

    List<Author> getAuthors(String authors) {
        return Arrays.stream(authors.split(","))
                .map(id -> getAuthorById(Integer.parseInt(id)))
                .collect(Collectors.toList());
    }

    private Author getAuthorById(int id) {
        for (Author author : authors) {
            if (author.getAuthorsId() == id) {
                return author;
            }
        }
        return null;
    }

    static FileReaderFromFileToList getInstance() {
        if (instance == null) {
            synchronized (FileReaderFromFileToList.class) {
                instance = new FileReaderFromFileToList();
            }
        }
        return instance;
    }
}
