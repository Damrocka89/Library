package App;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryApp {
    //TODO cala klasa do podzialu
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private boolean closeApp = false;
    private Scanner scanner = new Scanner(System.in);

    private FileReaderToList fileReader = new FileReaderToList();
    private FileWriterFromList fileWriter = new FileWriterFromList();

    private BookListEditor bookListEditor = new BookListEditor(books);


    public LibraryApp() {
        fileReader.readCathegoriesFromFile(categories);
        fileReader.readAuthorsFromFile(authors);
        fileReader.readListOfBooksFromFile(books, this);
        fileReader.readUsersAndPasswords(usersList);
    }


    public void startApp() {

        System.out.println("Wybierz opcję: \n" +
                "1. Zaloguj. \n" +
                "2. Zarejestruj. \n" +
                "3. Wyjdź.");

        while (!closeApp) {
            switch (scanner.nextLine().trim()) {
                case "1":
                    if (login()) {
                        accessToLibraryBooksListPreview();
                    }
                    break;
                case "2":
                    registerNewUser();
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
                    books.forEach(System.out::println);
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
                    fileWriter.saveChangesInBooksListToCsvFile(books);
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

    private boolean registerNewUser() {
        System.out.println("Podaj nazwę uzytkownika:");
        String userName = scanner.nextLine().trim();
        if (isOnTheListOfUsers(userName)) {
            System.out.println("Taki użytkownik już istnieje.");
            registerNewUser();
        }
        String password = addingPassword();
        usersList.add(new User(userName, password));
        return fileWriter.addUserToFile(userName, password, usersList);
    }


    private String addingPassword() {
        System.out.println("Podaj hasło (co najmniej 3 litery):");
        String password = scanner.nextLine().trim();
        if (password.length() < 3) {
            System.out.println("Hasło za krótkie.");
            addingPassword();
        }
        System.out.println("Powtórz hasło:");
        if (!scanner.nextLine().trim().equals(password)) {
            System.out.println("Niepoprawnie.");
            addingPassword();
        }
        return password;
    }

    private boolean login() {
        System.out.println("Podaj nazwę użytkownika:");
        String username = scanner.nextLine().trim();
        if (isOnTheListOfUsers(username)) {
            checkIfPasswordIsCorrect(username);
            return true;
        } else {
            System.out.println("Taki użytkownik nie istnieje.");
            startApp();
            return false;
        }
    }

    private boolean isOnTheListOfUsers(String username) {
        return usersList.stream()
                .anyMatch(user -> user.isUsernameValid(username));
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

    Author getAuthorById(int id) {
        for (Author author : authors) {
            if (author.getAuthorsId() == id) {
                return author;
            }
        }
        return null;
    }

    private void checkIfPasswordIsCorrect(String username) {
        boolean back = false;
        while (!back) {
            System.out.println("Podaj hasło lub cofnij (Q):");
            String password = scanner.nextLine().trim();
            if (password.equalsIgnoreCase("q")) {
                startApp();
            } else if (!isPasswordCorrect(username, password)) {
                System.out.println("Nieprawidłowe hasło.");
                checkIfPasswordIsCorrect(username);
            } else {
                back = true;
            }
        }
    }

    private boolean isPasswordCorrect(String username, String password) {
        for (User user : usersList) {
            if (user.checkPassword(username, password)) {
                return true;
            }
        }
        return false;
    }
}
