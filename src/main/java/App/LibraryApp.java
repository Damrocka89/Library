package App;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryApp {

    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Cathegory> cathegories = new ArrayList<>();
    private List<User> usersList = new ArrayList<>();
    private boolean closeApp = false;
    private Scanner scanner = new Scanner(System.in);


    public LibraryApp() {
        readCathegoriesFromFile();
        readAuthorsFromFile();
        readListOfBooksFromFile();
        readUsersAndPasswords();
    }

    private void readAuthorsFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\authors.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            authors.add(new Author(Integer.parseInt(arrayOfStrings[0]), arrayOfStrings[1], Integer.parseInt(arrayOfStrings[2]))));
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy autorów.");
        }
    }

    private void readCathegoriesFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\categories.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            cathegories.add(new Cathegory(Integer.parseInt(arrayOfStrings[0]), arrayOfStrings[1], Integer.parseInt(arrayOfStrings[2]))));
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy kategorii.");
        }
    }

    private void readUsersAndPasswords() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\users.txt"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            usersList.add(new User(arrayOfStrings[0], arrayOfStrings[1])));
        } catch (IOException e) {
            System.out.println("Nie udało się załadować użytkowników.");
        }
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
                    saveChangesInBooksListToCsvFile();
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

    private void saveChangesInBooksListToCsvFile() {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\books.csv"))) {
            for (Book book : books) {
                bufferedWriter.write(book.bookToCsv() + "\n");
            }
            System.out.println("Poprawnie zapisano listę książek.");
            accessToLibraryBooksListPreview();
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać listy książek.");
            accessToLibraryBooksListPreview();
        }
    }

    private void editYearOfPrintingBook() {
        System.out.println("Podaj tytuł książki, której datę wydania chcesz edytować:");
        String title = scanner.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                int year = getValidYearOfIssue();
                book.setYear(year);
                System.out.println("Data wydania książki " + title + " została zaktualizowana.");
                accessToLibraryBooksListPreview();
            }
        }
        System.out.println("Nie znaleziono ksiązki: " + title + " w bazie danych.");
        accessToLibraryBooksListPreview();
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
        System.out.println("Podaj rodzaj okładki (miękka - M, twarda - T):");
        String typeOfBinding = scanner.nextLine().trim();
        Cathegory cathegory = getValidCathegory();
        List<Author> authors=new ArrayList<>();
        authors=getValidAuthors();
        int id = books.get(books.size()-1).getBookId()+1;
        books.add(new Book(id,title, isbnNumber, year, typeOfBinding, authors, cathegory));
        System.out.println("Dodano książkę.");
        accessToLibraryBooksListPreview();
    }

    private List<Author> getValidAuthors() {
        System.out.println("Podaj id autorów (w formacie np. 1, 2):");
        String input = scanner.nextLine();
        try {
        Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Format id autorów jest niepoprawny.");
            getValidAuthors();
        }
        return getAuthors(input);
    }

    private Cathegory getValidCathegory() {
        System.out.println("Podaj id kategorii (1-3):");
        String id = scanner.nextLine();
        try {
            // checking valid integer using parseInt() method
            Integer.parseInt(id);

        } catch (NumberFormatException e) {
            System.out.println("Numer kategorii: " + id + " nie jest poprawny.");
            getValidCathegory();
        }

        if (getCathegory(id)!=null){
            return getCathegory(id);
        }else{
            System.out.println("Nie ma takiej kategorii");
            getValidCathegory();
        }
        return null;
    }


    private int getValidYearOfIssue() {
        System.out.println("Podaj rok wydania książki:");
        String yearString = scanner.nextLine().trim();
        try {
            // checking valid integer using parseInt() method
            Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            System.out.println(yearString + " nie jest poprawny.");
            getValidYearOfIssue();
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
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\users.txt", true))) {
            if (usersList.size() <= 1) {
                bufferedWriter.write(userName + ";" + password);
            } else {
                bufferedWriter.write("\n" + userName + ";" + password);
            }
            System.out.println("Poprawnie zarejestrowano użytkownika");
        } catch (IOException e) {
            System.out.println("Nie dało się zapisać użytkownika");
            return false;
        }
        startApp();
        return true;
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
        for (User user : usersList) {
            if (user.isUsernameValid(username)) {
                return true;
            }
        }
        return false;
    }

    private void readListOfBooksFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Users\\ewkra\\IdeaProjects\\Library\\src\\main\\resources\\books.csv"))) {
            bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .forEach(arrayOfStrings ->
                            books.add(new Book(Integer.parseInt(arrayOfStrings[0]),arrayOfStrings[1], arrayOfStrings[2], Integer.parseInt(arrayOfStrings[3]),
                                    arrayOfStrings[4], getAuthors(arrayOfStrings[5]), getCathegory(arrayOfStrings[6]))));
        } catch (IOException e) {
            System.out.println("Nie udało się załadować listy książek.");
        }
    }

    private Cathegory getCathegory(String idOfCathegory) {
        for (Cathegory cathegory : cathegories) {
            if (cathegory.getCathegoryId() == Integer.parseInt(idOfCathegory)) {
                return cathegory;
            }
        }
        return null;
    }

    private List<Author> getAuthors(String authors) {
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
