package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static app.BookBindingType.M;
import static app.BookBindingType.T;
import static org.assertj.core.api.Assertions.assertThat;


class BookFunctionsTest {

    private static List<Book> books;
    private static BookFunctions bookFunctions;

    /*W klasie BookFunctionsTest tworzymy ręcznie 6 książek (bez autorów i kategorii) i dodajemy je do listy.
To nasze dane testowe, których będziemy używać do kolejnych funkcji. */


    @BeforeAll
    static void setup() {
        books = Arrays.asList(new Book(1, "Java", "123ert", 2010, M),
                new Book(2, "Exercises", "202020kdkdkd", 2000, T),
                new Book(3, "Just a title", "dldldldl", 1999, M),
                new Book(4, "Another book", "abc", 2003, T),
                new Book(5, "Creative ideas", "123456", 2019, M),
                new Book(6, "Innovations", "gkfks", 2018, T));
        bookFunctions = new BookFunctions();
    }


    @Test
    void testFindBookByIsbnWithLoopIfIsbnIsCorrect() {
        Assertions.assertEquals(books.get(0), bookFunctions.getBookFromTheListWithGivenIsbnLoop("123ert", books));
    }

    @Test
    void testFindBookByIsbnWithLoopIfBookNotExists() {
        Assertions.assertNull(bookFunctions.getBookFromTheListWithGivenIsbnLoop("bla", books));
    }

    @Test
    void testFindBookByIsbnWithStreamsIfIsbnIsCorrect() {
        Assertions.assertEquals(books.get(0), bookFunctions.getBookFromTheListWithGivenIsbnStream("123ert", books));
    }

    @Test
    void testFindBookByIsbnWithStreamsIfBookNotExists() {
        Assertions.assertNull(bookFunctions.getBookFromTheListWithGivenIsbnStream("bla", books));
    }

    @Test
    void testReceivingTwoLastBooksFromTheListWithLoop() {
        assertThat(bookFunctions.getTwoLastBooksStreams(books)).hasSize(2)
                .contains((books.get(books.size() - 1)), (books.get(books.size() - 2)));

    }

    @Test
    void testReceivingTwoLastBooksFromTheListWithStreams() {
        assertThat(bookFunctions.getTwoLastBooksStreams(books)).hasSize(2)
                .contains((books.get(books.size() - 1)), (books.get(books.size() - 2)));
    }

    @Test
    void testReceivingTwoLastBooksFromTheListIfListIsShorterThanTwoLoop() {
        List<Book> books1 = new ArrayList<>();
        books1.add(new Book(6, "Innovations", "gkfks", 2018, T));
        Assertions.assertEquals(books1, bookFunctions.getTwoLastBooksLoop(books1));
    }

    @Test
    void testReceivingTwoLastBooksFromTheListIfListIsEmptyLoop() {
        List<Book> books1 = new ArrayList<>();
        Assertions.assertEquals(books1, bookFunctions.getTwoLastBooksLoop(books1));
    }

    @Test
    void testReceivingTwoLastBooksFromTheListIfListIsShorterThanTwoStream() {
        List<Book> books1 = new ArrayList<>();
        books1.add(new Book(6, "Innovations", "gkfks", 2018, T));
        Assertions.assertEquals(books1, bookFunctions.getTwoLastBooksStreams(books1));
    }

    @Test
    void testReceivingTwoLastBooksFromTheListIfListIsEmptyStream() {
        List<Book> books1 = new ArrayList<>();
        Assertions.assertEquals(books1, bookFunctions.getTwoLastBooksStreams(books1));
    }

    @Test
    void testReceivingtheOldestBookUsingLoop() {
        Assertions.assertEquals(books.get(2), bookFunctions.getTheOldestBookLoop(books));
    }

    @Test
    void testReceivingtheOldestBookUsingStream() {
        Assertions.assertEquals(books.get(2), bookFunctions.getTheOldestBookStream(books));
    }

    @Test
    void testReceivingtheNewestBookUsingLoop() {
        Assertions.assertEquals(books.get(4), bookFunctions.getTheNewestBookLoop(books));
    }

    @Test
    void testReceivingtheNewestBookUsingStream() {
        Assertions.assertEquals(books.get(4), bookFunctions.getTheNewestBookStream(books));
    }

    @Test
    void testTheSummingOfPublicationYearsUsingLoop() {
        Assertions.assertEquals(12049, bookFunctions.getTheSumOfPublicationYearsLoop(books));
    }

    @Test
    void testTheSummingOfPublicationYearsUsingStreams() {
        Assertions.assertEquals(12049, bookFunctions.getTheSumOfPublicationYearsStreams(books));
    }

    @Test
    void testTheNumberOfBooksPublishedAfter2007UsingLoops() {
        Assertions.assertEquals(3, bookFunctions.getTheNumberOfBooksPublishedAfter2007Loop(books));
    }

    @Test
    void testTheNumberOfBooksPublishedAfter2007UsingStreams() {
        Assertions.assertEquals(3, bookFunctions.getTheNumberOfBooksPublishedAfter2007Streams(books));
    }

    @Test
    void testIfAllBooksArePublishedAfter2000UsingLoop() {
        Assertions.assertFalse(bookFunctions.areAllBooksPublishedAfter2000Loop(books));
    }

    @Test
    void testIfAllBooksArePublishedAfter2000UsingStreams() {
        Assertions.assertFalse(bookFunctions.areAllBooksPublishedAfter2000Streams(books));
    }

    @Test
    void testAverageYesrOfPublishingLoop() {
        Assertions.assertEquals(2008.16, bookFunctions.averageYearOfPublishingLoop(books), 0.01);
    }

    @Test
    void testAverageYesrOfPublishingStreams() {
        Assertions.assertEquals(2008.16, bookFunctions.averageYearOfPublishingStreams(books), 0.01);
    }

    @Test
    void testIfAnyBookHasBeenPublishedBefore2003Loop() {
        Assertions.assertTrue(bookFunctions.isAnyBookPublishedBefore2003Loop(books));
    }

    @Test
    void testIfAnyBookHasBeenPublishedBefore2003Stream() {
        Assertions.assertTrue(bookFunctions.isAnyBookPublishedBefore2003Streams(books));
    }

    @Test
    void testBooksWithTitleStartingWithCPublishedAfter2007() {
        List<Book> booksFiltered = new ArrayList<>();
        booksFiltered.add(books.get(4));
        Assertions.assertEquals(booksFiltered, bookFunctions.returnBooksWithTitleStartingWithCAndPublishedAfter2007(books));
    }


    @ParameterizedTest
    @CsvSource({
            "0", "1", "2", "3", "4", "5"
    })
    void testAddingHundredYearsToPublishYear(int index) {
        Assertions.assertEquals(books.get(index).getYear() + 100, bookFunctions.addHundredYearsToPublishYear(books).get(index).getYear());
    }

    @Test
    void testReceivingListOfBooksDividableByTwo() {
        assertThat(bookFunctions.returnBooksWhichYearOfPublishIdDividableByTwo(books)).hasSize(3)
                .contains(books.get(0), books.get(1), books.get(5));
    }

    @Test
    void testGettingMapFromBooksList() {
        Map<String, Book> booksMap = new HashMap<>();
        for (Book book : books) {
            booksMap.put(book.getIsbnNumber(), book);
        }
        assertThat(bookFunctions.returnMapOfBooksWithIsbnKey(books)).hasSize(6)
                .containsAllEntriesOf(booksMap);
    }

    @ParameterizedTest
    @CsvSource({
            "0,Just a title",
            "1,Exercises",
            "2,Another book",
            "3,Java",
            "4,Innovations",
            "5,Creative ideas"
    })
    void testSortingListByPublishYearAscending(int index, String title) {
        bookFunctions.sortBooksListByPublishYearAscending(books);
        Assertions.assertEquals(title, books.get(index).getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "0,Creative ideas",
            "1,Innovations",
            "2,Java",
            "3,Another book",
            "4,Exercises",
            "5,Just a title"
    })
    void testSortingListByPublishYearDescending(int index, String title) {
        bookFunctions.sortBooksListByPublishYearDescending(books);
        Assertions.assertEquals(title, books.get(index).getTitle());
    }

    @Test
    void testDividingListIntoListOfListsSize() {
        List<List<Book>> listOfBooks = bookFunctions.getThreeListsContainingEachTwoBooks(books);
        assertThat(listOfBooks).hasSize(3);
    }

    @Test
    void testDividingListIntoListOfListsSizeGuava() {
        List<List<Book>> listOfBooks = bookFunctions.getThreeListsContainingEachTwoBooks(books);
        assertThat(listOfBooks).hasSize(3);
    }

    @Test
    void testDividingListIntoListOfLists() {
        List<List<Book>> listOfBooksActual = bookFunctions.getThreeListsContainingEachTwoBooks(books);
        List<Book> list1 = books.subList(0, 2);
        List<Book> list2 = books.subList(2, 4);
        List<Book> list3 = books.subList(4, 6);
        List<List<Book>> listOfBooksExpected = Arrays.asList(list1, list2, list3);

        Assertions.assertEquals(listOfBooksExpected, listOfBooksActual);
    }

    //books = Arrays.asList(new Book(1, "Java", "123ert", 2010, M),
    //                new Book(2, "Exercises", "202020kdkdkd", 2000, T),
    //                new Book(3, "Just a title", "dldldldl", 1999, M),
    //                new Book(4, "Another book", "abc", 2003, T),
    //                new Book(5, "Creative ideas", "123456", 2019, M),
    //                new Book(6, "Innovations", "gkfks", 2018, T));
    @Test
    void testReceivingMapOfBooksListsGroupedByPublishYear() {
        Assertions.assertEquals("Java", bookFunctions.groupBooksByPublishYear(books).get(2010).get(0).getTitle());
    }

    @Test
    void testReceivingMapOfBooksListsGroupedByPublishYearSize() {
        assertThat(bookFunctions.groupBooksByPublishYear(books)).hasSize(6);
    }

    @Test
    void testReceivingMapOfBooksListsGroupedByPublishYearSizeOfOneList() {
        assertThat(bookFunctions.groupBooksByPublishYear(books).get(1999)).hasSize(1);
    }

    @Test
    void testGroupBooksPublishedAfter2009AndTheRestSize() {
        assertThat(bookFunctions.groupBooksPublishedAfter2009AndTheRest(books)).hasSize(2);
    }

    @Test
    void testGroupBooksPublishedAfter2009AndTheRestListAfter2009() {
        assertThat(bookFunctions.groupBooksPublishedAfter2009AndTheRest(books).get(Boolean.TRUE)).hasSize(3)
                .contains(books.get(0),books.get(4),books.get(5));
    }

    @Test
    void testGroupBooksPublishedAfter2009AndTheRestSizeOfOneListBefore2009() {
        assertThat(bookFunctions.groupBooksPublishedAfter2009AndTheRest(books).get(Boolean.FALSE)).hasSize(3);
    }


}