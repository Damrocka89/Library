package app;

import java.util.Objects;

public class Author {

    private int authorsId;
    private String nameAndSurnameOfAuthor;
    private int authorsAge;

    public Author(int authorsId, String nameAndSurnameOfAuthor, int authorsAge) {
        this.authorsId = authorsId;
        this.nameAndSurnameOfAuthor = nameAndSurnameOfAuthor;
        this.authorsAge = authorsAge;
    }

    public int getAuthorsId() {
        return authorsId;
    }

    @Override
    public String toString() {
        return authorsId + " - " + nameAndSurnameOfAuthor;
    }

     String getNameAndSurnameOfAuthor() {
        return nameAndSurnameOfAuthor;
    }

    public String authorToCsv() {
        return authorsId + ";" + nameAndSurnameOfAuthor + ";" + authorsAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorsId == author.authorsId &&
                authorsAge == author.authorsAge &&
                Objects.equals(nameAndSurnameOfAuthor, author.nameAndSurnameOfAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorsId, nameAndSurnameOfAuthor, authorsAge);
    }
}
