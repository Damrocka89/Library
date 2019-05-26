package app;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class BookFunctions {


    //Znajdź książkę o podanym ISBN. Metoda przyjmuje 2 parametry (isbn, lista wszystkich książek) i zwraca podaną książkę.
    public Book getBookFromTheListWithGivenIsbnLoop(String isbn, List<Book> books) {
        for (Book book : books) {
            if (book.getIsbnNumber().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        System.out.println("Brak książki o podanym ISBN.");
        return null;
    }

    public Book getBookFromTheListWithGivenIsbnStream(final String isbn, List<Book> books) {
        Optional<Book> any = books.stream()
                .filter(book1 -> book1.getIsbnNumber().equalsIgnoreCase(isbn))
                .findAny();

        if (!any.isPresent()) {
            System.out.println("Brak książki o podanym ISBN.");
            return null;
        } else {
            return any.get();
        }
    }
    //Zwróć dwie ostatnie książki.

    public List<Book> getTwoLastBooksLoop(List<Book> books) {
        List<Book> booksResult = new ArrayList<>();
        if (books.size() >= 2) {
            for (int i = books.size() - 2; i < books.size(); i++) {
                booksResult.add(books.get(i));
            }
        } else {
            return books;
        }
        return booksResult;
    }

    public List<Book> getTwoLastBooksStreams(List<Book> books) {
        if (books.size() >= 2) {
            return books.stream()
                    .skip(books.size() - 2)
                    .collect(Collectors.toList());
        } else {
            return books;
        }
    }
    //Zwróć najwcześniej wydana książkę.

    public Book getTheOldestBookLoop(List<Book> books) {
        Book oldest = books.get(0);
        for (Book book : books) {
            if (book.getYear() < oldest.getYear()) {
                oldest = book;
            }
        }
        return oldest;
    }

    public Book getTheOldestBookStream(List<Book> books) {
        return books.stream()
                .min(Comparator.comparingInt(Book::getYear))
                .orElse(null);
    }

    //Zwróć najpóźniej wydana książkę.
    public Book getTheNewestBookLoop(List<Book> books) {
        Book newest = books.get(0);
        for (Book book : books) {
            if (book.getYear() > newest.getYear()) {
                newest = book;
            }
        }
        return newest;
    }

    public Book getTheNewestBookStream(List<Book> books) {
        return books.stream()
                .max(Comparator.comparingInt(Book::getYear))
                .orElse(null);
    }

    //Zwróć sumę lat wydania wszystkich książek.

    public int getTheSumOfPublicationYearsLoop(List<Book> books) {
        int sum = 0;
        for (Book book : books) {
            sum += book.getYear();
        }
        return sum;
    }

    public int getTheSumOfPublicationYearsStreams(List<Book> books) {
        return books.stream()
                .mapToInt(Book::getYear)
                .sum();
    }

    //Zwróć liczbę książek wydanych po 2007 roku.

    public int getTheNumberOfBooksPublishedAfter2007Loop(List<Book> books) {
        int count = 0;
        for (Book book : books) {
            if (book.getYear() > 2007) {
                count++;
            }
        }
        return count;
    }

    public int getTheNumberOfBooksPublishedAfter2007Streams(List<Book> books) {
        return (int) books.stream()
                .filter(book -> book.getYear() > 2007)
                .count();
    }

    public List<Book> getBooksPublishedAfter2007Streams(List<Book> books) {
        return books.stream()
                .filter(book -> book.getYear() > 2007)
                .collect(Collectors.toList());
    }

    //Zwróć informacje o tym czy wszystkie książki w naszym katalogu są wydane po 2000 roku.

    public boolean areAllBooksPublishedAfter2000Loop(List<Book> books) {
        for (Book book : books) {
            if (book.getYear() <= 2000) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllBooksPublishedAfter2000Streams(List<Book> books) {
        return books.stream()
                .allMatch(book -> book.getYear() > 2000);
    }
    //Zwróć średni rok wydania książki w naszym katalogu.

    public double averageYearOfPublishingLoop(List<Book> books) {
        return (double) getTheSumOfPublicationYearsLoop(books) / books.size();
    }

    public double averageYearOfPublishingStreams(List<Book> books) {
        return books.stream()
                .mapToDouble(Book::getYear)
                .average()
                .orElse(0);
    }
    //Zwróć informacje o tym czy jakakolwiek książka w naszym katalogu jest wydana przed  2003 rokiem.

    public boolean isAnyBookPublishedBefore2003Loop(List<Book> books) {
        for (Book book : books) {
            if (book.getYear() < 2003) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnyBookPublishedBefore2003Streams(List<Book> books) {
        return books.stream()
                .anyMatch(book -> book.getYear() < 2000);
    }

    //10. Zwróć wszystkie książki, których tytuł zaczyna się od litery “C” i były one wydane po 2007 roku.
    public List<Book> returnBooksWithTitleStartingWithCAndPublishedAfter2007(List<Book> books) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().startsWith("c"))
                .filter(book -> book.getYear() > 2007)
                .collect(Collectors.toList());
    }

    //11. Dodajmy 100 lat do daty wydania każdej książki (tak, wiem że to nie ma sensu).
    public List<Book> addHundredYearsToPublishYear(List<Book> books) {
        return books.stream()
                .map(book -> new Book(book.getBookId(), book.getTitle(), book.getIsbnNumber(), book.getYear() + 100, book.getTypeOfBinding()))
                .collect(Collectors.toList());
    }
//12 .Zwróć tytuły wszystkich książek, których rok jest podzielny przez 2.

    public List<Book> returnBooksWhichYearOfPublishIdDividableByTwo(List<Book> books) {
        return books.stream()
                .filter(book -> book.getYear() % 2 == 0)
                .collect(Collectors.toList());
    }
//13. Zwróć mapę, która będzie miała klucz isbn i wartość obiekt Book (Map<String, Book>).

    public Map<String, Book> returnMapOfBooksWithIsbnKey(List<Book> books) {
        return books.stream()
                .collect(Collectors.toMap(Book::getIsbnNumber, book -> book));
    }

//14. Posortuj książki po roku wydania zaczynając od wydanej najpóźniej.

    public void sortBooksListByPublishYearAscending(List<Book> books) {
        books.sort(Comparator.comparingInt(Book::getYear));
    }

    //15. Posortuj książki po roku wydania zaczynając od wydanej najwcześniej.
    public void sortBooksListByPublishYearDescending(List<Book> books) {
        books.sort(Comparator.comparingInt(Book::getYear).reversed());
    }

//16. Podziel listę książek na 3 listy po 2 książki i zwróć z metody.

    public List<List<Book>> getThreeListsContainingEachTwoBooks(List<Book> books) {
        return new ArrayList<>(books.stream()
                .collect(Collectors.groupingBy(book -> (book.getBookId() - 1) / 2))
                .values());
    }

    public List<List<Book>> getThreeListsContainingEachTwoBooksGuava(List<Book> books) {
        return Lists.partition(books, 2);
    }

    //17. Pogrupuj książki po roku wydania. Metoda powinna zwrócić Map<Integer,
//      List<Book>> gdzie kluczem jest rok wydania a wartością lista książek wydana w tym roku.

    public Map<Integer, List<Book>> groupBooksByPublishYear(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getYear, Collectors.toList()));
    }

//18. Podziel książki na te wydane po 2009 roku i pozostałe.
//      Metoda powinna zwrócić Map<Boolean, List<Book>> gdzie kluczem jest boolean oznaczający czy została wydana po 2009
//      a wartością będą listy książek.

    public Map<Boolean, List<Book>> groupBooksPublishedAfter2009AndTheRest(List<Book> books) {
        return books.stream()
                .collect(Collectors.partitioningBy(book -> book.getYear() > 2009, Collectors.toList()));
    }
}
