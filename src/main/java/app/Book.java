package app;


import java.util.List;
import java.util.stream.Collectors;

public class Book {

    private int bookId;
    private String title;
    private String isbnNumber;
    private int year;
    private BookBindingType typeOfBinding;
    private List<Author> authors;
    private Category category;

    Book(int bookId, String title, String isbnNumber, int year, BookBindingType typeOfBinding, List<Author> authors, Category category) {
        this.bookId = bookId;
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.year = year;
        this.typeOfBinding = typeOfBinding;
        this.authors = authors;
        this.category = category;
    }

    public Book(int bookId, String title, String isbnNumber, int year, BookBindingType typeOfBinding) {
        this.bookId = bookId;
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.year = year;
        this.typeOfBinding = typeOfBinding;
    }

    String bookToCsv() {
        return bookId + ";" + title + ";" + isbnNumber + ";" + year + ";" + typeOfBinding + ";" + authorsIdsToCSV() + ";" + category.getCategoryId();
    }

    public int getBookId() {
        return bookId;
    }

    private String authorsIdsToCSV() {
        return authors.stream()
                .map(Author::getAuthorsId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return title + " " +getAuthorsNames()+" "+ year + " " + isbnNumber + " ";
    }

    private String getAuthorsNames() {
        return authors.stream()
                .map(Author::getNameAndSurnameOfAuthor)
                .collect(Collectors.joining(","));
    }

    String getTitle() {
        return title;
    }

    void setYear(int year) {
        this.year = year;
    }


    public String getIsbnNumber() {
        return isbnNumber;
    }

    public BookBindingType getTypeOfBinding() {
        return typeOfBinding;
    }

    public int getYear() {
        return year;
    }
}
