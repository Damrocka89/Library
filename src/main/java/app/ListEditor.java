package app;

import java.util.List;

public class ListEditor {

    List<Book> books;
    List<Author> authors;
    List<Category> categories;
    FileReaderFromFileToList fileReader=FileReaderFromFileToList.getInstance();

    public ListEditor() {
        authors = fileReader.readAuthorsFromFile();
        categories = fileReader.readCathegoriesFromFile();
    }



}
