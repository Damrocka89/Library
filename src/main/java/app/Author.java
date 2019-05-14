package app;

class Author {

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
}
