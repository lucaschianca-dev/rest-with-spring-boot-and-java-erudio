package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.controllers;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v1.PersonDTO;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.common.PersonCommonService;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.util.MediaTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    @Autowired
    private PersonCommonService service;

    @GetMapping(value = "/{id}", produces = {
            MediaTypeUtil.APPLICATION_JSON,
            MediaTypeUtil.APPLICATION_XML,
            MediaTypeUtil.APPLICATION_YML,
    })
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {
            MediaTypeUtil.APPLICATION_JSON,
            MediaTypeUtil.APPLICATION_XML,
            MediaTypeUtil.APPLICATION_YML,
    })
    public List<PersonDTO> findAll() throws Exception {
        return service.findAll();
    }

    @PostMapping(
            consumes = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML,
            },
            produces = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML,
            })
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    @PutMapping(
            consumes = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML,
            },
            produces = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML,
            })
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
