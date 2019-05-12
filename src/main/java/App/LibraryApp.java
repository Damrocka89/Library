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
    private List<Cathegory> cathegories = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private boolean closeApp = false;
    private Scanner scanner = new Scanner(System.in);

    private FileReaderToList fileReader = new FileReaderToList();//todo do klas
    private FileWriterFromList fileWriter = new FileWriterFromList();//todo do klas


    public LibraryApp() {
        fileReader.readCathegoriesFromFile(cathegories);
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
                    addNewBook();
                    break;
                case "3":
                    removeBookByTitle();
                    break;
                case "4":
                    editYearOfPrintingBook();
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



    private boolean editYearOfPrintingBook() {
        System.out.println("Podaj tytuł książki, której datę wydania chcesz edytować:");
        String title = scanner.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                int year = getValidYearOfIssue();
                book.setYear(year);
                System.out.println("Data wydania książki " + title + " została zaktualizowana.");
                accessToLibraryBooksListPreview();
                return true;
            }
        }
        System.out.println("Nie znaleziono ksiązki: " + title + " w bazie danych.");
        accessToLibraryBooksListPreview(); //todo
        return false;
    }

    private boolean removeBookByTitle() {
        System.out.println("Podaj tytuł książki, którą chcesz usunąć:");
        String title = scanner.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
                System.out.println("Książka " + title + " została usunięta.");
                accessToLibraryBooksListPreview();
                return true;
            }
        }
        System.out.println("Nie znaleziono ksiązki: " + title + " w bazie danych.");
        accessToLibraryBooksListPreview();
        return false;
    }

    private void addNewBook() {
        System.out.println("Podaj tytuł:");
        String title = scanner.nextLine().trim();
        System.out.println("Podaj ISBN książki:");
        String isbnNumber = scanner.nextLine();
        int year = getValidYearOfIssue();
        String typeOfBinding = getValidBindingType();
        Cathegory cathegory = getValidCathegory();
        List<Author> authors = new ArrayList<>();
        authors = getValidAuthors();
        int id = books.get(books.size() - 1).getBookId() + 1; //TODO maksyymalne id
        books.add(new Book(id, title, isbnNumber, year, typeOfBinding, authors, cathegory));
        System.out.println("Dodano książkę.");
        accessToLibraryBooksListPreview();
    }

    private String getValidBindingType() {
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

    private List<Author> getValidAuthors() {
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
        return getAuthors(input);
    }

    private Cathegory getValidCathegory() {
        boolean valid = false;
        String id = "";
        while (!valid) {
            for (Cathegory cathegory : cathegories) {
                System.out.println(cathegory);
            }
            System.out.println("Podaj id kategorii (1-" + cathegories.size() + "):");
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
            if (getCathegory(id) != null) {
                valid = true;
                return getCathegory(id);
            } else {
                System.out.println("Nie ma takiej kategorii");
            }
        }
        return null;
    }


    private int getValidYearOfIssue() {
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

    private boolean registerNewUser() {
        System.out.println("Podaj nazwę uzytkownika:");
        String userName = scanner.nextLine().trim();
        if (isOnTheListOfUsers(userName)) {
            System.out.println("Taki użytkownik już istnieje.");
            registerNewUser();
        }
        String password = addingPassword();
        usersList.add(new User(userName, password));
       return fileWriter.addUserToFile(userName, password,usersList);
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
//        usersList.stream().anyMatch(user -> user.isUsernameValid(username)); //TODO
        for (User user : usersList) {
            if (user.isUsernameValid(username)) {
                return true;
            }
        }
        return false;
    }


    Cathegory getCathegory(String idOfCathegory) {
        for (Cathegory cathegory : cathegories) {
            if (cathegory.getCathegoryId() == Integer.parseInt(idOfCathegory)) {
                return cathegory;
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
