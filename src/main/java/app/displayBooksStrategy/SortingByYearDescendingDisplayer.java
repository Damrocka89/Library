package app.displayBooksStrategy;

import app.Book;
import app.BookFunctions;

import java.util.List;

public class SortingByYearDescendingDisplayer implements BookListDisplayStrategy {

    private BookFunctions bookFunctions = new BookFunctions();


    @Override
    public void displayBooks(List<Book> books) {
        bookFunctions.sortBooksListByPublishYearDescending(books);
        books.forEach(System.out::println);
    }
}
