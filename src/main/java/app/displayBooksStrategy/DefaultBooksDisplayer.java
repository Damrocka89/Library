package app.displayBooksStrategy;

import app.Book;
import java.util.List;

public class DefaultBooksDisplayer implements BookListDisplayStrategy {


    @Override
    public void displayBooks(List<Book> books) {
        books.forEach(System.out::println);
    }

}
