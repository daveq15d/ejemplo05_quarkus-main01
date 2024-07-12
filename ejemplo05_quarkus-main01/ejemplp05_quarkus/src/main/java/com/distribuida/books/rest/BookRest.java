package com.distribuida.books.rest;

import com.distribuida.books.clients.AuthorRestClient;
import com.distribuida.books.db.Book;
import com.distribuida.books.dtos.BookDto;
import com.distribuida.books.repository.BooksRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URL;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {
    @Inject
    BooksRepository br;

    @Inject
    @RestClient
    AuthorRestClient authorRestClient;

    @GET
    public List<BookDto> findAll() {
        System.out.println("findAll books");
        var books = br.listAll();

        //Metodo API
        //RestClientBuilder.newBuilder().
                //baseUrl(new URL("http://localhost:9090")).
                //build(AuthorRestClient.class);


        return books.stream().
                map(book -> {
                    var author = authorRestClient.findById(book.getAuthorId());
                    BookDto dto = new BookDto();

                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setIsbn(book.getIsbn());
                    dto.setPrice(book.getPrice());

                    dto.setAuthorName(author.getFirstName());
                    return dto;
                }).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        System.out.println("findID books");
        var op = br.findByIdOptional(id);
        if (op.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Book book) {
        book.setId(null);
        br.persist(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Book book, @PathParam("id") Integer id) {
        Book obj = br.findById(id);

        obj.setIsbn(book.getIsbn());
        obj.setTitle(book.getTitle());
        obj.setPrice(book.getPrice());
        obj.setAuthorId(book.getAuthorId());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        br.deleteById(id);
        return Response.ok().build();
    }

}
