package app;

import app.IO.FileReaderFromFileToList;
import app.IO.FileWriterToFileFromList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static app.BookBindingType.T;
import static app.BookBindingType.M;
import static java.util.Arrays.asList;

class CsvReaderAndWriterTest {

    private static List<Author> authors;
    private static List<Category> categories;
    private static List<Book> books;
    private static List<User> users;
    private FileReaderFromFileToList fileReaderFromFileToList=FileReaderFromFileToList.getInstance();
    private FileWriterToFileFromList fileWriterToFileFromList=FileWriterToFileFromList.getInstance();

    public static final String pathAuthorsTest = "D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\testResources\\authorsReaderTest.csv";
    public static final String pathCategoriesTest = "D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\testResources\\categoriesReaderTest.csv";
    public static final String pathBooksTest = "D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\testResources\\booksReaderTest.csv";
    public static final String pathUsersTest = "D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\testResources\\usersReaderTest.txt";

    @BeforeAll
    static void setup() {

        users = asList(new User("ee", "eee"),
                new User("tt", "ttt"));

        authors = asList(new Author(1, "Robert C. Martin", 32),
                new Author(2, "Martin Fowler", 50),
                new Author(3, "Kent Beck", 54));

        categories = asList(new Category(2, "Wzorce projektowe", 1),
                new Category(3, "Techniki programowania", 2),
                new Category(0, "Brak", 0));

        books = asList(new Book(1, "Clean Code", "132350882", 2008, T, asList(authors.get(0)), categories.get(1)),
                new Book(2, "Effective Java (3rd Edition)", "134685997", 2018, M, asList(authors.get(1)), categories.get(2)));
    }

    @Test
    void testreadListOfBooksFromFile() {
        Assertions.assertEquals(books,fileReaderFromFileToList.readListOfBooksFromFile(categories,authors,pathBooksTest));
    }

    @Test
    void testreadAuthorsFromFile(){
        Assertions.assertEquals(authors,fileReaderFromFileToList.readAuthorsFromFile(pathAuthorsTest));
    }

    @Test
    void testreadCathegoriesFromFile(){
        Assertions.assertEquals(categories,fileReaderFromFileToList.readCathegoriesFromFile(pathCategoriesTest));
    }

    @Test
    void testreadUsersAndPasswords(){
        Assertions.assertEquals(users,fileReaderFromFileToList.readUsersAndPasswords(pathUsersTest));
    }

    @Test
    void testsaveChangesInBooksListToCsvFile(){
        
    }

}
