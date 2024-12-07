package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.controllers.v2;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.PersonDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2.PersonServiceV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.util.MediaTypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/person")
@Tag(name = "People", description = "Endpoints for managing People")
public class PersonControllerV2 {

    @Autowired
    private PersonServiceV2 service;

    @GetMapping(value = "/{id}", produces = {
            MediaTypeUtil.APPLICATION_JSON,
            MediaTypeUtil.APPLICATION_XML,
            MediaTypeUtil.APPLICATION_YML,
    })
    @Operation(
            summary = "Finds a Person",
            description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV2.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTOV2 findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {
            MediaTypeUtil.APPLICATION_JSON,
            MediaTypeUtil.APPLICATION_XML,
            MediaTypeUtil.APPLICATION_YML,
    })
    @Operation(
            summary = "Finds all People",
            description = "Finds all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PersonDTOV2> findAll() throws Exception {
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
    @Operation(
            summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of a person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
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
    @Operation(
            summary = "Updates a Person",
            description = "Updates a Person by passing in a JSON, XML or YML representation of a person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTOV2 update(@RequestBody PersonDTOV2 person) {
        return service.update(person);
    }

    @DeleteMapping(value = "{id}")
    @Operation(
            summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML or YML representation of a person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
