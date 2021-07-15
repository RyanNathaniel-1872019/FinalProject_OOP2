package com.aldrich_ryan.dao;

import com.aldrich_ryan.entity.User;
import com.aldrich_ryan.util.DaoService;
import com.aldrich_ryan.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoImpl implements DaoService<User> {
    @Override
    public List<User> fetchAll() {
        return null;
    }

    public List<User> login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        Predicate p1 = builder.equal(root.get("username"), username);
        Predicate p2 = builder.equal(root.get("password"), password);

        List<User> users = session.createQuery(query.where(p1, p2)).getResultList();
        session.close();
        return users;
    }

    @Override
    public int addData(User object) {
        return 0;
    }

    @Override
    public int udpateData(User object) {
        return 0;
    }

    @Override
    public int deleteData(User object) {
        return 0;
    }
}
