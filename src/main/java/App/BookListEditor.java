package App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookListEditor {

    private List<Book> books;
    Scanner scanner = new Scanner(System.in);

    public BookListEditor(List<Book> books) {
        this.books = books;
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

    void addNewBook(List<Author> authorsList,List<Category> categoryList, LibraryApp libraryApp) {
        System.out.println("Podaj tytuł:");
        String title = scanner.nextLine().trim();
        System.out.println("Podaj ISBN książki:");
        String isbnNumber = scanner.nextLine();
        int year = getValidYearOfIssue();
        String typeOfBinding = getValidBindingType();
        Category category = getValidCathegory(categoryList,libraryApp);
        List<Author> authors = new ArrayList<>();
        authors = getValidAuthors(authorsList, libraryApp);
        int id = books.get(books.size() - 1).getBookId() + 1; //TODO maksyymalne id
        books.add(new Book(id, title, isbnNumber, year, typeOfBinding, authors, category));
        System.out.println("Dodano książkę.");
    }

    String getValidBindingType() {
        boolean valid = false;
        String binding = "";
        while (!valid) {
            System.out.println("Podaj rodzaj okładki (miękka - M, twarda - T):");
            binding = scanner.nextLine().trim();
            if (binding.equalsIgnoreCase("t") || binding.equalsIgnoreCase("m")) {
                valid = true;
            } else {
                System.out.println("Niepoprawny wybór, wybierz M lub T.");
            }
        }
        return binding; //TODO enum
    }

    List<Author> getValidAuthors(List<Author> authors, LibraryApp libraryApp) {
        boolean valid = false;
        String input = "";
        while (!valid) {
            for (Author author : authors) {
                System.out.println(author);
            }
            System.out.println("Podaj id autorów (w formacie np. 1, 2):");
            input = scanner.nextLine();
            try {
                Arrays.stream(input.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()); //TODO mozna bez collect
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Format id autorów jest niepoprawny.");
            }
        }
        return libraryApp.getAuthors(input);
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
            try {
                // checking valid integer using parseInt() method
                //TODO StringUtils apache commons lang isNumeric()
                Integer.parseInt(id);
                valid = true;

            } catch (NumberFormatException e) {
                System.out.println("Numer kategorii: " + id + " nie jest poprawny.");
            }
        }
        valid = false;
        while (!valid) { //TODO wyslij kod
            if (libraryApp.getCathegory(id) != null) {
                valid = true;
                return libraryApp.getCathegory(id);
            } else {
                System.out.println("Nie ma takiej kategorii");
            }
        }
        return null;
    }


    int getValidYearOfIssue() {
        boolean valid = false;
        String yearString = "";
        while (!valid) {
            System.out.println("Podaj rok wydania książki:");
            yearString = scanner.nextLine().trim();
            try {
                // checking valid integer using parseInt() method
                Integer.parseInt(yearString);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println(yearString + " nie jest poprawny.");
            }
        }
        return Integer.parseInt(yearString);
    }


}
