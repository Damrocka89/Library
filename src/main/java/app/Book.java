package app;

//
//Identyfikator
//Tytuł
//Numer isbn
//Rok wydania
//Rodzaj oprawy (twarda/miękka)
//Id autorów książki
//Id kategorii książki

//1
//Clean Code
//132350882
//2008
//T
//1,2,3
//3


import java.util.List;
import java.util.stream.Collectors;

public class Book {

    private int bookId;
    private String title;
    private String isbnNumber;
    private int year;
    private String typeOfBinding;
    private List<Author> authors;
    private Category category;

    StringBuilder sb = new StringBuilder();

    Book(int bookId, String title, String isbnNumber, int year, String typeOfBinding, List<Author> authors, Category category) {
        this.bookId = bookId;
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.year = year;
        this.typeOfBinding = typeOfBinding;
        this.authors = authors;
        this.category = category;
    }

    String bookToCsv() {
        return bookId + ";" + title + ";" + isbnNumber + ";" + year + ";" + typeOfBinding + ";" +authorsIdsToCSV()+";"+ category.getCategoryId();
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
        return title + " " + year + " " + isbnNumber+" ";
    }

//    private String getAuthorsNames(){
//
//    }

    protected String getTitle() {
        return title;
    }

    protected void setYear(int year) {
        this.year = year;
    }

    protected String getIsbnNumber() {
        return isbnNumber;
    }

    protected int getYear() {
        return year;
    }


}
