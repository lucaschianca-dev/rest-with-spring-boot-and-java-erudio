package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.controllers.v2.PersonControllerV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.PersonDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.Mapper;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServiceV2 {

    @Autowired
    PersonRepository repository;

    private final Logger logger = Logger.getLogger(PersonServiceV2.class.getName());

    public PersonDTOV2 findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var dto = Mapper.parseObject(entity, PersonDTOV2.class);
        dto.add(linkTo(methodOn(PersonControllerV2.class).findById(id)).withSelfRel());

        return dto;
    }

    public List<PersonDTOV2> findAll() {
        var persons = Mapper.parseListObjects(repository.findAll(), PersonDTOV2.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonControllerV2.class).findById(p.getPersonId())).withSelfRel()));

        return persons;
    }

    public PersonDTOV2 create(PersonDTOV2 person) {
        var entity = Mapper.parseObject(person, Person.class);
        var dto = Mapper.parseObject(repository.save(entity), PersonDTOV2.class);
        dto.add(linkTo(methodOn(PersonControllerV2.class).findById(dto.getPersonId())).withSelfRel());

        return dto;
    }

    public PersonDTOV2 update(PersonDTOV2 person) {
        var entity = repository.findById(person.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = Mapper.parseObject(repository.save(entity), PersonDTOV2.class);
        dto.add(linkTo(methodOn(PersonControllerV2.class).findById(dto.getPersonId())).withSelfRel());

        return dto;
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
