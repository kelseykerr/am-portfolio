package com.impulsecontrol.portfolio.db;

import com.impulsecontrol.portfolio.core.Artwork;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtworkDAO extends AbstractDAO<Artwork> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtworkDAO.class);

    public ArtworkDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Artwork> findAll() {
        Criteria c = currentSession().createCriteria(Artwork.class);
        return c.list();
    }

    public List<Artwork> findArtworkByCategoryId(List<Long> categoryIds) {
        Criteria c = currentSession().createCriteria(Artwork.class)
                .add(Restrictions.in("category.id", categoryIds));
        return c.list();
    }

    public List<Artwork> getArtworkByCategoryAndQuery(Long categoryId, String queryTerm) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.* from artwork a where a.category = :categoryId and a.description like :queryTerm ");
        SQLQuery query = this.currentSession().createSQLQuery(sb.toString()).addEntity(Artwork.class);
        query.setParameter("categoryId", categoryId);
        query.setParameter("queryTerm", "%" + queryTerm.toLowerCase() + "%");
        List<Artwork> artwork = query.list();
        return artwork;

    }
}
