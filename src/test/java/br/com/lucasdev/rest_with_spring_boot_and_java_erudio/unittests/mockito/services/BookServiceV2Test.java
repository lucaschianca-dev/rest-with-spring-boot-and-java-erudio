package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.unittests.mockito.services;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.BookDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.mocks.MockBook;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2.BookServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceV2Test {

    MockBook bookInput;

    @InjectMocks
    private BookServiceV2 bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUpMocks() {
        bookInput = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book entity = bookInput.mockEntity(1);
        entity.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = bookService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getBookId());
        assertNotNull(result.getLinks());
        System.out.println(result);
        assertTrue(result.toString().contains("links: [</api/v2/book/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertEquals(101, result.getPrice());
        Date expectedDate = new GregorianCalendar(2025, Calendar.JANUARY, 2).getTime();
        assertEquals(expectedDate, result.getLaunchDate());
    }

    @Test
    void findAll() {
        List<Book> entityList = bookInput.mockEntityList();

        when(bookRepository.findAll()).thenReturn(entityList);
        var books = bookService.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getBookId());
        assertNotNull(bookOne.getLinks());
        System.out.println(bookOne);
        assertTrue(bookOne.toString().contains("links: [</api/v2/book/1>;rel=\"self\"]"));
        assertEquals("Author Test1", bookOne.getAuthor());
        assertEquals("Title Test1", bookOne.getTitle());
        assertEquals(101, bookOne.getPrice());
        Date expectedDateOne = new GregorianCalendar(2025, Calendar.JANUARY, 2).getTime();
        assertEquals(expectedDateOne, bookOne.getLaunchDate());

        var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getBookId());
        assertNotNull(bookFour.getLinks());
        System.out.println(bookFour);
        assertTrue(bookOne.toString().contains("links: [</api/v2/book/1>;rel=\"self\"]"));
        assertEquals("Author Test4", bookFour.getAuthor());
        assertEquals("Title Test4", bookFour.getTitle());
        assertEquals(104, bookFour.getPrice());
        Date expectedDateFour = new GregorianCalendar(2025, Calendar.JANUARY, 5).getTime();
        assertEquals(expectedDateFour, bookFour.getLaunchDate());

    }

    @Test
    void create() {
        Book entity = bookInput.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1L);
        BookDTOV2 dto = bookInput.mockDTO(1);
        dto.setBookId(1L);

        when(bookRepository.save(entity)).thenReturn(persisted);

        var result = bookService.create(dto);

        assertNotNull(result);
        assertNotNull(result.getBookId());
        assertNotNull(result.getLinks());
        System.out.println(result);

        assertTrue(result.toString().contains("links: [</api/v2/book/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertEquals(101, result.getPrice());
        Date expectedDate = new GregorianCalendar(2025, Calendar.JANUARY, 2).getTime();
        assertEquals(expectedDate, result.getLaunchDate());
    }

    @Test
    void update() {
        Book entity = bookInput.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookDTOV2 dto = bookInput.mockDTO(1);
        dto.setBookId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(entity)).thenReturn(persisted);

        var result = bookService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getBookId());
        assertNotNull(result.getLinks());
        System.out.println(result);

        assertTrue(result.toString().contains("links: [</api/v2/book/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertEquals(101, result.getPrice());
        Date expectedDate = new GregorianCalendar(2025, Calendar.JANUARY, 2).getTime();
        assertEquals(expectedDate, result.getLaunchDate());
    }

    @Test
    void delete() {
        Book entity = bookInput.mockEntity(1);
        entity.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        bookService.delete(1L);
    }
}
