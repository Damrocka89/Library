package App;

public class Author {

    private int authorsId;
    private String nameAndSurnameOfAuthor;
    private int authorsAge;

    public Author(int authorsId,String nameAndSurnameOfAuthor, int authorsAge) {
        this.authorsId = authorsId;
        this.nameAndSurnameOfAuthor = nameAndSurnameOfAuthor;
        this.authorsAge = authorsAge;
    }

    public int getAuthorsId() {
        return authorsId;
    }
    public String toCsv() {
        return authorsId+";"+nameAndSurnameOfAuthor+";"+authorsAge;
    }
}
