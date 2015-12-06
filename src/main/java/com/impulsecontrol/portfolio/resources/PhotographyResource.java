package com.impulsecontrol.portfolio.resources;

import com.impulsecontrol.portfolio.core.Artwork;
import com.impulsecontrol.portfolio.db.ArtworkDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/photography")
@Produces(MediaType.APPLICATION_JSON)
public class PhotographyResource {

    private final ArtworkDAO artworkDAO;

    public PhotographyResource(ArtworkDAO artworkDAO) {
        this.artworkDAO = artworkDAO;
    }

    @GET
    @UnitOfWork
    public List<Artwork> getAllPhotographs() {
        return artworkDAO.findAll();
    }
}
