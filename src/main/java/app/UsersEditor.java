package app;

import java.util.ArrayList;
import java.util.List;

class UsersEditor {

    private List<User> usersList = new ArrayList<>();
    private UserRegistrationApp userRegistrationApp;
    private UserLoginApp userLoginApp;

    private FileWriterToFileFromList fileWriter;

    public UsersEditor(FileReaderFromFileToList fileReader, FileWriterToFileFromList fileWriterFromList) {
        fileReader.readUsersAndPasswords(usersList);
        this.userRegistrationApp = new UserRegistrationApp(usersList);
        this.userLoginApp = new UserLoginApp(usersList);
        this.fileWriter=fileWriterFromList;
    }

    public boolean login() {
        return userLoginApp.login(userRegistrationApp);
    }

    public void registerNewUser() {
        userRegistrationApp.registerNewUser(fileWriter);
    }
}
