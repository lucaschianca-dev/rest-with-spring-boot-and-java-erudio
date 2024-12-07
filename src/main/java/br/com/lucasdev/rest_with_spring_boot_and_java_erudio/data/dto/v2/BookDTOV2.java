package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookDTOV2 extends RepresentationModel<BookDTOV2> {

    @JsonProperty("id")
    private long bookId;

    private String author;

    private Date launchDate;

    private double price;

    private String title;

    public BookDTOV2() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDTOV2 bookDTOV2 = (BookDTOV2) o;
        return bookId == bookDTOV2.bookId && Double.compare(price, bookDTOV2.price) == 0 && Objects.equals(author, bookDTOV2.author) && Objects.equals(launchDate, bookDTOV2.launchDate) && Objects.equals(title, bookDTOV2.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, author, launchDate, price, title);
    }
}
