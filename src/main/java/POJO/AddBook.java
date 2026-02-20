package POJO;

public class AddBook {

    /* PAYLOAD TO PASS as body:
    {
            "book_name": "Learn Playwright Automation",
            "isbn": "bcdaaa99",
            "aisle": "1112327",
            "author": "Jack Foe Jr"
    }*/
    private String book_name;
    private String isbn;
    private String aisle;
    private String author;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
}
