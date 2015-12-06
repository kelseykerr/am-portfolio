package com.impulsecontrol.portfolio.db;

import com.impulsecontrol.portfolio.core.Artwork;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtworkDAO extends AbstractDAO<Artwork> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtworkDAO.class);

    public ArtworkDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Artwork> findAll() {
        LOGGER.debug("Fetching all artwork from the database.");
        Criteria c = currentSession().createCriteria(Artwork.class);
        LOGGER.debug("Found [" + c.list().size() + "] entries in the artwork database.");
        return c.list();
    }
}
