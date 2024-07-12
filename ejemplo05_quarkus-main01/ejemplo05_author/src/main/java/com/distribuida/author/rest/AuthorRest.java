package com.distribuida.author.rest;

import com.distribuida.author.db.Author;
import com.distribuida.author.repository.AuthorsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Access;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthorRest {

    @Inject
    AuthorsRepository repo;

    @GET
    public List<Author> findAll(){
        System.out.println("findByAll");

        return repo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        System.out.println("findById");
        var author = repo.findByIdOptional(id);
        if(author.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(author.get()).build();
    }

    @POST
    public Response create(Author author){
        author.setId(null);
        repo.persist(author);
        return Response.status(Response.Status.CREATED).build();
    }



    @PUT
    @Path("/{id}")
    public Response update(Author author, @PathParam("id") Integer id){

        Author aut= repo.findById(id);
        aut.setFirstName(author.getFirstName());
        aut.setLastName(author.getLastName());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        repo.deleteById(id);
        return Response.ok().build();
    }


}
