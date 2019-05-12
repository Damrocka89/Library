package App;

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


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Book {

    private int bookId;
    private String title;
    private String isbnNumber;
    private int year;
    private String typeOfBinding;
    private List<Author> authors = new ArrayList<>();
    private Cathegory cathegory;

    StringBuilder sb = new StringBuilder();

    protected Book(int bookId, String title, String isbnNumber, int year, String typeOfBinding, List<Author> authors, Cathegory cathegory) {
        this.bookId = bookId;
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.year = year;
        this.typeOfBinding = typeOfBinding;
        this.authors = authors;
        this.cathegory = cathegory;
    }

    protected String bookToCsv() {
        return bookId + ";" + title + ";" + isbnNumber + ";" + year + ";" + typeOfBinding + ";" +authorsIdsToCSV()+";"+cathegory.getCathegoryId();
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
        return title + " " + year + " " + isbnNumber;
    }

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
