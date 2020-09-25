package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.*;
import se325.assignment01.concert.service.domain.*;
import se325.assignment01.concert.service.mapper.ConcertMapper;


import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/concert-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConcertResource {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcertResource.class);

    /**
     * get the concert of the specified id
     *
     * @param id id of concert desired
     * @return the concert requested in a response, otherwise an error status if failed
     */
    @GET
    @Path("/concerts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcertById(@PathParam("id") long id) {

        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {
            em.getTransaction().begin();

            Concert concert = em.find(Concert.class, id);
            ResponseBuilder rb;

            //if no such concert exists
            if (concert == null) {
                rb = Response.status(Response.Status.NOT_FOUND);
            } else {
                rb = Response.ok(ConcertMapper.toDTO(concert));
            }

            em.getTransaction().commit();
            return rb.build();

        } finally {
            em.close();
        }
    }
}