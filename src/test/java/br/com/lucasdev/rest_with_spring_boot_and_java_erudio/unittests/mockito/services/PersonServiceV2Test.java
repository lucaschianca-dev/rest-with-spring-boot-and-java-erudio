package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.unittests.mockito.services;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.PersonDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.mocks.MockBook;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.mocks.MockPerson;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2.BookServiceV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2.PersonServiceV2;
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
class PersonServiceV2Test {

    MockPerson personInput;

    @InjectMocks
    private PersonServiceV2 personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() {
        personInput = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    // PERSON

    @Test
    void findById() {
        Person entity = personInput.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());
        System.out.println(result);
        assertTrue(result.toString().contains("links: [</api/v2/person/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void findAll() {
        List<Person> entityList = personInput.mockEntityList();

        when(personRepository.findAll()).thenReturn(entityList);
        var people = personService.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var peopleOne = people.get(1);

        assertNotNull(peopleOne);
        assertNotNull(peopleOne.getPersonId());
        assertNotNull(peopleOne.getLinks());
        System.out.println(peopleOne);
        assertTrue(peopleOne.toString().contains("links: [</api/v2/person/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", peopleOne.getFirstName());
        assertEquals("Last Name Test1", peopleOne.getLastName());
        assertEquals("Addres Test1", peopleOne.getAddress());
        assertEquals("Female", peopleOne.getGender());

        var peopleFour = people.get(4);

        assertNotNull(peopleFour);
        assertNotNull(peopleFour.getPersonId());
        assertNotNull(peopleFour.getLinks());
        System.out.println(peopleFour);
        assertTrue(peopleFour.toString().contains("links: [</api/v2/person/4>;rel=\"self\"]"));
        assertEquals("First Name Test4", peopleFour.getFirstName());
        assertEquals("Last Name Test4", peopleFour.getLastName());
        assertEquals("Addres Test4", peopleFour.getAddress());
        assertEquals("Male", peopleFour.getGender());

    }

    @Test
    void create() {
        Person entity = personInput.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);
        PersonDTOV2 dto = personInput.mockDTO(1);
        dto.setPersonId(1L);

        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personService.create(dto);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());
        System.out.println(result);

        assertTrue(result.toString().contains("links: [</api/v2/person/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void update() {
        Person entity = personInput.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonDTOV2 dto = personInput.mockDTO(1);
        dto.setPersonId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());
        System.out.println(result);

        assertTrue(result.toString().contains("links: [</api/v2/person/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person entity = personInput.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        personService.delete(1L);
    }
}