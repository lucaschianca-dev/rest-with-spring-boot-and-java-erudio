package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.common;


import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v1.PersonDTO;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.mapper.Mapper;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonCommonService {

    @Autowired
    PersonRepository repository;

    private final AtomicLong counter = new AtomicLong();
//    private final Logger logger = Logger.getLogger(PersonCommonService.class.getName());

    public PersonDTO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return Mapper.parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        return Mapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        var entity = Mapper.parseObject(person, Person.class);
        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
