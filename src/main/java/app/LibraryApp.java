package app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryApp {
    //TODO cala klasa do podzialu
    private List<Author> authors = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    private boolean closeApp = false;
    private Scanner scanner = new Scanner(System.in);

    private FileWriterFromList fileWriter = new FileWriterFromList();

    private UsersEditor usersEditor;
    private BookListEditor bookListEditor;



    public LibraryApp() {
        FileReaderToList fileReader = new FileReaderToList();

        fileReader.readCathegoriesFromFile(categories);
        fileReader.readAuthorsFromFile(authors);

        usersEditor=new UsersEditor(fileReader,fileWriter);
        bookListEditor = new BookListEditor(fileReader,this, fileWriter);
    }


    public void startApp() {

        System.out.println("Wybierz opcję: \n" +
                "1. Zaloguj. \n" +
                "2. Zarejestruj. \n" +
                "3. Wyjdź.");

        while (!closeApp) {
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

    private void accessToLibraryBooksListPreview() {
        while (!closeApp) {

            System.out.println("Wybierz polecenie: \n" +
                    "1. Wyświetl listę książek. \n" +
                    "2. Dodaj nową książkę. \n" +
                    "3. Usuń książkę po nazwie. \n" +
                    "4. Edytuj rok wydania książki. \n" +
                    "5. Zapisz zmiany. \n" +
                    "6. Cofnij do poprzedniego Menu. \n" +
                    "7. Wyjdź.");

            switch (scanner.nextLine().trim()) {
                case "1":
                    bookListEditor.printBooks();
                    break;
                case "2":
                    bookListEditor.addNewBook(authors, categories, this);
                    break;
                case "3":
                    bookListEditor.removeBookByTitle();
                    break;
                case "4":
                    bookListEditor.editYearOfPrintingBook();
                    break;
                case "5":
                    bookListEditor.saveChanges();
                    break;
                case "6":
                    startApp();
                    break;
                case "7":
                    closeApp = true;
                    break;
                default:
                    System.out.println("Nieprawidłowe polecenie.");
            }
        }
    }

    Category getCathegory(String idOfCathegory) {
        for (Category category : categories) {
            if (category.getCategoryId() == Integer.parseInt(idOfCathegory)) {
                return category;
            }
        }
        return null;
    }

    List<Author> getAuthors(String authors) {
        return Arrays.stream(authors.split(","))
                .map(id -> getAuthorById(Integer.parseInt(id)))
                .collect(Collectors.toList());
    }

    private Author getAuthorById(int id) {
        for (Author author : authors) {
            if (author.getAuthorsId() == id) {
                return author;
            }
        }
        return null;
    }

}
