package app;


import app.IO.FileReaderFromFileToList;
import app.displayBooksStrategy.BooksAfter2007Displayer;
import app.displayBooksStrategy.DefaultBooksDisplayer;
import app.displayBooksStrategy.SortingByYearAscendingDisplayer;
import app.displayBooksStrategy.SortingByYearDescendingDisplayer;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    private boolean closeApp = false;
    private Scanner scanner = new Scanner(System.in);

    private UsersEditor usersEditor;
    private BookListEditor bookListEditor;

    private List<Author> authors;
    private List<Category> categories;
    private FileReaderFromFileToList fileReader = FileReaderFromFileToList.getInstance();


    public LibraryApp() {
        authors = fileReader.readAuthorsFromFile(CsvFilesPathsTracker.pathToAuthorsFile);
        categories = fileReader.readCathegoriesFromFile(CsvFilesPathsTracker.pathToCategoriesFile);
        usersEditor = new UsersEditor();
        bookListEditor = new BookListEditor(authors, categories);
    }


    public void startApp() {
        while (!closeApp) {
            printLoginRegisterMenu();
            switch (scanner.nextLine().trim()) {
                case "1":
                    if (usersEditor.login()) {
                        accessToLibraryBooksListPreview();
                    }
                    break;
                case "2":
                    usersEditor.registerNewUser();
                    break;
                case "3":
                    closeApp = true;
                    break;
                default:
                    System.out.println("Nieprawidłowe polecenie.");
            }

        }
    }

    private void printLoginRegisterMenu() {
        System.out.println("Wybierz opcję: \n" +
                "1. Zaloguj. \n" +
                "2. Zarejestruj. \n" +
                "3. Wyjdź.");
    }

    private void accessToLibraryBooksListPreview() {
        while (!closeApp) {

            printLibraryMenu();

            switch (scanner.nextLine().trim()) {
                case "1":
                    displayBooks();
                    break;
                case "2":
                    bookListEditor.addNewBook();
                    break;
                case "3":
                    bookListEditor.removeBookByTitle();
                    break;
                case "4":
                    bookListEditor.editYearOfPrintingBook();
                    break;
                case "5":
                    bookListEditor.saveBooks();
                    break;
                case "6":
                    bookListEditor.printCategories();
                    break;
                case "7":
                    bookListEditor.removeCategory();
                    break;
                case "8":
                    bookListEditor.saveCategories();
                    break;
                case "9":
                    bookListEditor.printAuthors();
                    break;
                case "10":
                    bookListEditor.saveChanges();
                    break;
                case "11":
                    startApp();
                    break;
                case "12":
                    closeApp = true;
                    break;
                default:
                    System.out.println("Nieprawidłowe polecenie.");
            }
        }
    }

    private void displayBooks() {
        while (!closeApp) {

            printBookListDisplayMenu();

            switch (scanner.nextLine().trim()) {
                case "1":
                    bookListEditor.setBookListDisplayStrategy(new DefaultBooksDisplayer());
                    bookListEditor.printBooks();
                    break;
                case "2":
                    bookListEditor.setBookListDisplayStrategy(new SortingByYearAscendingDisplayer());
                    bookListEditor.printBooks();
                    break;
                case "3":
                    bookListEditor.setBookListDisplayStrategy(new SortingByYearDescendingDisplayer());
                    bookListEditor.printBooks();
                    break;
                case "4":
                    bookListEditor.setBookListDisplayStrategy(new BooksAfter2007Displayer());
                    bookListEditor.printBooks();
                    break;
                case "5":
                    accessToLibraryBooksListPreview();
                    break;
                default:
                    System.out.println("Nieprawidłowe polecenie.");
            }
        }
    }

    private void printLibraryMenu() {
        System.out.println("Wybierz polecenie: \n" +
                "1. Wyświetl listę książek. \n" +
                "2. Dodaj nową książkę. \n" +
                "3. Usuń książkę po nazwie. \n" +
                "4. Edytuj rok wydania książki. \n" +
                "5. Zapisz zmiany - książki. \n" +
                "6. Wyświetl wszystkie kategorie. \n" +
                "7. Usuń kategorię. \n" +
                "8. Zapisz zmiany - kategorie. \n" +
                "9. Wyświetl wszystkich autorów.\n" +
                "10. Zapisz zmiany - wszystkie. \n" +
                "11. Cofnij do poprzedniego Menu (wyloguj). \n" +
                "12. Wyjdź.");
    }

    private void printBookListDisplayMenu() {
        System.out.println("Wybierz polecenie: \n" +
                "1. Wyświetl domyślnie. \n" +
                "2. Sortowanie po roku wydania rosnąco. \n" +
                "3. Sortowanie po roku wydania malejąco. \n" +
                "4. Książki wydane po 2007 roku.\n" +
                "5. Cofnij do poprzedniego Menu. ");
    }

}
