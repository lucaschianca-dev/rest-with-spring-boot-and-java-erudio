package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.controllers.v2;

import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.BookDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2.PersonDTOV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.services.v2.BookServiceV2;
import br.com.lucasdev.rest_with_spring_boot_and_java_erudio.util.MediaTypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/book")
@Tag(name = "Books", description = "Endpoints for managing Books")
public class BookControllerV2 {

    @Autowired
    private BookServiceV2 service;

    @GetMapping(value = "/{id}", produces = {
            MediaTypeUtil.APPLICATION_JSON,
            MediaTypeUtil.APPLICATION_XML,
            MediaTypeUtil.APPLICATION_YML,
    })
    @Operation(
            summary = "Finds a Book",
            description = "Finds a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTOV2.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTOV2> findById(@PathVariable(value = "id") Long id) {
        var book = service.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping(
            produces = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML,
            })
    @Operation(
            summary = "Finds all Books",
            description = "Finds all Books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<BookDTOV2>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(
            consumes = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML
            },
            produces = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML
            }
    )
    @Operation(
            summary = "Adds a new Book",
            description = "Adds a new Book by passing in a JSON, XML or YML representation of a person!",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTOV2> create(@RequestBody BookDTOV2 book) {
        var newBook = service.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newBook));
    }

    @PutMapping(
            produces = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML
            },
            consumes = {
                    MediaTypeUtil.APPLICATION_JSON,
                    MediaTypeUtil.APPLICATION_XML,
                    MediaTypeUtil.APPLICATION_YML
            })

    @Operation(
            summary = "Updates a Book",
            description = "Updates a Book by passing in a JSON, XML or YML representation of a person!",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTOV2.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTOV2> update(@RequestBody BookDTOV2 book) {
        var updatedBook = service.update(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(value = "{id}")
    @Operation(
            summary = "Deletes a Book",
            description = "Deletes a Book by passing in a JSON, XML or YML representation of a person!",
            tags = {"Books"},
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