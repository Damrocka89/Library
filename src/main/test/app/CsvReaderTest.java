package app;

import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static app.BookBindingType.T;
import static java.util.Arrays.asList;

class CsvReaderTest {

    private static List<Author> authors;
    private static List<Category> categories;
    private static List<Book> books;

    @BeforeAll
    static void setup() {
        //1;Clean Code;132350882;2008;T;1;3
        //2;Effective Java (3rd Edition);134685997;2018;M;2;0
        //3;Test Driven Development: By Example;321146530;2002;M;3;3
        //4;Patterns of Enterprise Application Architecture;321127420;2002;T;4;0
        //5;Head First Design Patterns;596007124;2004;M;4,5,7;0
        //6;Clean Architecture;134494164;2017;M;1;3

        //
        //2;Martin Fowler;50
        //3;Kent Beck;54
        //4;Joshua Bloch;34
        //5;Eric Freeman;41
        //6;Bert Bates;20
        //7;Kathy Sierra;67
        //8;Elisabeth Robson;42

        authors = asList(new Author(1, "Robert C. Martin", 32),
                new Author(2, "Martin Fowler", 50),
                new Author(3, "Kent Beck", 54));

        categories = asList(new Category(2, "Wzorce projektowe", 1),
                new Category(3, "Techniki programowania", 2),
                new Category(0, "Brak", 0));

        books = asList(new Book(1, "Clean Code", "132350882", 2008, T, asList(authors.get(0)), categories.get(1)));
    }

}
