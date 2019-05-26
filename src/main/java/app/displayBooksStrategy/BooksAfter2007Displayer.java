package app.displayBooksStrategy;

import app.Book;
import app.BookFunctions;

import java.util.List;

public class BooksAfter2007Displayer implements BookListDisplayStrategy {

    private BookFunctions bookFunctions = new BookFunctions();


    @Override
    public void displayBooks(List<Book> books) {
        bookFunctions.getBooksPublishedAfter2007Streams(books).forEach(System.out::println);
    }
}
