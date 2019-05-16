package app;

import java.util.List;

class UsersEditor {

    private List<User> usersList;
    private UserRegistrationApp userRegistrationApp;
    private UserLoginApp userLoginApp;

    private FileWriterToFileFromList fileWriter = FileWriterToFileFromList.getInstance();
    private FileReaderFromFileToList fileReader = FileReaderFromFileToList.getInstance();

    public UsersEditor() {
        usersList = fileReader.readUsersAndPasswords();
        this.userRegistrationApp = new UserRegistrationApp(usersList);
        this.userLoginApp = new UserLoginApp(usersList);
    }

    public boolean login() {
        return userLoginApp.login(userRegistrationApp);
    }

    public void registerNewUser() {
        userRegistrationApp.registerNewUser(fileWriter);
    }
}
