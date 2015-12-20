package com.impulsecontrol.portfolio.resources;

import com.impulsecontrol.portfolio.core.Artwork;
import com.impulsecontrol.portfolio.db.ArtworkDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/portfolio")
@Produces(MediaType.APPLICATION_JSON)
public class PortfolioResource {

    private final ArtworkDAO artworkDAO;

    public PortfolioResource(ArtworkDAO artworkDAO) {
        this.artworkDAO = artworkDAO;
    }

    @GET
    @UnitOfWork
    @Path("/photography")
    public List<Artwork> getAllPhotographs(@QueryParam("queryTerm") String queryTerm) {
        if (queryTerm != null) {
            return artworkDAO.getArtworkByCategoryAndQuery(1L, queryTerm);
        }
        return artworkDAO.findArtworkByCategoryId(Collections.singletonList(1L));
    }

    @GET
    @UnitOfWork
    @Path("/artwork")
    public List<Artwork> getAllArtwork() {
        List<Long> artworkList = new ArrayList<>();
        artworkList.add(2L);
        artworkList.add(3L);
        return artworkDAO.findArtworkByCategoryId(artworkList);
    }
}
