package bookapp.beans;

public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private String publications;
    private Double price;

    public Book() {}

    public Book(int bookId, String title, String author, String publications, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publications = publications;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
