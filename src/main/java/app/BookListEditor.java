package app;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static app.BookBindingType.M;
import static app.BookBindingType.T;

public class BookListEditor {

    private List<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private FileWriterFromList fileWriter;

    BookListEditor(FileReaderToList fileReader, LibraryApp libraryApp, FileWriterFromList fileWriter) {
        this.fileWriter = fileWriter;
        fileReader.readListOfBooksFromFile(books, libraryApp);
    }

    boolean editYearOfPrintingBook() {
        System.out.println("Podaj tytuł książki, której datę wydania chcesz edytować:");
        String title = scanner.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                int year = getValidYearOfIssue();
                book.setYear(year);
                System.out.println("Data wydania książki " + title + " została zaktualizowana.");
                return true;
            }
        }
        System.out.println("Nie znaleziono ksiązki: " + title + " w bazie danych.");
        return false;
    }

    boolean removeBookByTitle() {
        System.out.println("Podaj tytuł książki, którą chcesz usunąć:");
        String title = scanner.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
                System.out.println("Książka " + title + " została usunięta.");
                return true;
            }
        }
        System.out.println("Nie znaleziono ksiązki: " + title + " w bazie danych.");
        return false;
    }

    void addNewBook(List<Author> authorsList, List<Category> categoryList, LibraryApp libraryApp) {
        System.out.println("Podaj tytuł:");
        String title = scanner.nextLine().trim();

        System.out.println("Podaj ISBN książki:");
        String isbnNumber = scanner.nextLine();

        int year = getValidYearOfIssue();
        BookBindingType typeOfBinding = getValidBindingType();
        Category category = getValidCathegory(categoryList, libraryApp);
        List<Author> authors = getValidAuthorsIds(authorsList, libraryApp);

        int id = books.stream()
                .mapToInt(Book::getBookId)
                .max()
                .orElse(0);

        books.add(new Book(id, title, isbnNumber, year, typeOfBinding, authors, category));
        System.out.println("Dodano książkę.");
    }

    BookBindingType getValidBindingType() {
        boolean valid = false;
        BookBindingType bindingType = M;

        while (!valid) {
            System.out.println("Podaj rodzaj okładki (miękka - M, twarda - T):");
            String binding = scanner.nextLine().trim();
            if (binding.equalsIgnoreCase("T")) {
                bindingType = T;
                valid = true;
            } else if (binding.equalsIgnoreCase("M")) {
                valid = true;
            } else {
                System.out.println("Niepoprawny wybór, wybierz M lub T.");
            }
        }
        return bindingType;
    }

    List<Author> getValidAuthorsIds(List<Author> authors, LibraryApp libraryApp) {
        boolean valid = false;
        String input = "";

        while (!valid) {
            for (Author author : authors) {
                System.out.println(author);
            }
            System.out.println("Podaj id autorów (w formacie np. 1, 2):");
            input = scanner.nextLine();
            valid = Arrays.stream(input.split(","))
                    .allMatch(authorsId -> StringUtils.isNumeric(authorsId) && isThisAuthorIdExisting(authorsId, authors));
            if (!valid) {
                System.out.println("Format id autorów jest niepoprawny.");
            }
        }
        return libraryApp.getAuthors(input);
    }

    private boolean isThisAuthorIdExisting(String authorsId, List<Author> authors) {
        return authors.stream()
                .anyMatch(author -> String.valueOf(author.getAuthorsId()).equals(authorsId));
    }

    Category getValidCathegory(List<Category> categories, LibraryApp libraryApp) {
        boolean valid = false;
        String id = "";
        while (!valid) {
            for (Category category : categories) {
                System.out.println(category);
            }
            System.out.println("Podaj id kategorii (1-" + categories.size() + "):");
            id = scanner.nextLine();

            valid = StringUtils.isNumeric(id);

            if (libraryApp.getCathegory(id) == null) {
                valid = false;
            }
            if (!valid) {
                System.out.println("Numer kategorii: " + id + " nie jest poprawny.");
            }
        }
        return libraryApp.getCathegory(id);
    }


    int getValidYearOfIssue() {
        boolean valid = false;
        String yearString = "";
        while (!valid) {
            System.out.println("Podaj rok wydania książki:");
            yearString = scanner.nextLine().trim();
            valid = StringUtils.isNumeric(yearString);
            if (!valid) {
                System.out.println(yearString + " nie jest poprawny.");
            }
        }
        return Integer.parseInt(yearString);
    }


    void printBooks() {
        books.forEach(System.out::println);
    }

    void saveChanges() {
        fileWriter.saveChangesInBooksListToCsvFile(books);
    }
}
