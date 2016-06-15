package com.sebastian_daschner.jamazon.business.books.boundary;

import com.sebastian_daschner.jamazon.business.books.entity.Book;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BooksResource {

    @Inject
    BookStore bookStore;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray getBooks() {
        return bookStore.getBooks().stream().map(b -> Json.createObjectBuilder()
                .add("name", b.getName())
                .add("author", b.getName())
                .add("_links", Json.createObjectBuilder().add("self", createBookURI(b).toString())))
                .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
    }

    @GET
    @Path("{id}")
    public JsonObject getBook(@PathParam("id") final long id) {
        final Book book = bookStore.getBook(id);

        if (book == null)
            throw new NotFoundException();

        return Json.createObjectBuilder()
                .add("name", book.getName())
                .add("author", book.getName())
                .add("_links", Json.createObjectBuilder().add("self", createBookURI(book).toString()))
                .build();
    }

    @POST
    public Response save(@Valid final Book book) {
        final Book savedBook = bookStore.save(book);
        final URI bookUri = createBookURI(savedBook);
        return Response.created(bookUri).build();
    }

    private URI createBookURI(final Book book) {
        return uriInfo.getBaseUriBuilder().path(BooksResource.class).path(BooksResource.class, "getBook").build(book.getId());
    }

}
