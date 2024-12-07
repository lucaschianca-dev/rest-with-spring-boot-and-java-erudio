package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.mocks;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.BookDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.PersonDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTOV2 mockDTO() {
        return mockDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> booksList = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            booksList.add(mockEntity(i));
        }
        return booksList;
    }

    public List<BookDTOV2> mockDTOList() {
        List<BookDTOV2> bookDTOV2List = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookDTOV2List.add(mockDTO(i));
        }
        return bookDTOV2List;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setPrice(100 + number);
        book.setLaunchDate(new GregorianCalendar(2025, Calendar.JANUARY, 1 + number).getTime());
        return book;
    }

    public BookDTOV2 mockDTO(Integer number) {
        BookDTOV2 book = new BookDTOV2();
        book.setBookId(number.longValue());
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setPrice(100 + number);
        book.setLaunchDate(new GregorianCalendar(2025, Calendar.JANUARY, 1 + number).getTime());
        return book;
    }
}
