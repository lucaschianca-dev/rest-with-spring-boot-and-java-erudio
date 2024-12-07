package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.controllers.v2.BookControllerV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.BookDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.Mapper;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServiceV2 {

    @Autowired
    BookRepository repository;

    public BookDTOV2 findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = Mapper.parseObject(entity, BookDTOV2.class);
        dto.add(linkTo(methodOn(BookControllerV2.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<BookDTOV2> findAll() {
        var booksListDto = Mapper.parseListObjects(repository.findAll(), BookDTOV2.class);
        booksListDto
                .stream()
                .forEach(b -> b.add(linkTo(methodOn(BookControllerV2.class).findById(b.getBookId())).withSelfRel()));
        return booksListDto;
    }

    public BookDTOV2 create(BookDTOV2 book) {
        var entity = Mapper.parseObject(book, Book.class);
        var dto = Mapper.parseObject(repository.save(entity), BookDTOV2.class);
        dto.add(linkTo(methodOn(BookControllerV2.class).findById(dto.getBookId())).withSelfRel());
        return dto;
    }

    public BookDTOV2 update(@RequestBody BookDTOV2 book) {
        var entity = repository.findById(book.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());

        var dto = Mapper.parseObject(repository.save(entity), BookDTOV2.class);
        dto.add(linkTo(methodOn(BookControllerV2.class).findById(dto.getBookId())).withSelfRel());
        return dto;
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
