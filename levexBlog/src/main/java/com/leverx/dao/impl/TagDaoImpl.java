package com.leverx.dao.impl;

import com.leverx.dao.TagDao;
import com.leverx.entity.Post;
import com.leverx.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tag> getAllTags() {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery("from Tag", Tag.class);
        List<Tag> tagList = query.getResultList();
        return tagList;
    }
}
