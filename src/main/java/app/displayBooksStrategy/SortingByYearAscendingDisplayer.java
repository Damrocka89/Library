package app.displayBooksStrategy;

import app.Book;
import app.BookFunctions;

import java.util.List;

public class SortingByYearAscendingDisplayer implements BookListDisplayStrategy {

    private BookFunctions bookFunctions = new BookFunctions();

    @Override
    public void displayBooks(List<Book> books) {
        bookFunctions.sortBooksListByPublishYearAscending(books);
        books.forEach(System.out::println);
    }
}
