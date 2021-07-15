package com.aldrich_ryan.dao;

import com.aldrich_ryan.entity.Product;
import com.aldrich_ryan.util.DaoService;
import com.aldrich_ryan.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductDaoImpl implements DaoService<Product> {
    @Override
    public List<Product> fetchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriabuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriabuilder.createQuery(Product.class);
        criteriaQuery.from(Product.class);
        List<Product> products = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return products;
    }

    @Override
    public int addData(Product object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(object);
            transaction.commit();
            result = 1;
        }
        catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int udpateData(Product object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(object);
            transaction.commit();
            result = 1;
        }
        catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(Product object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(object);
            transaction.commit();
            result = 1;
        }
        catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }

//    public Product getImage(long idproduct)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            Product product = (Product) session.get(Product.class, idproduct);
//            return product;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }
}
