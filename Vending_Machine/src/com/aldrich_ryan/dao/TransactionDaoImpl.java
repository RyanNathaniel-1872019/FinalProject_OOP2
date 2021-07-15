package com.aldrich_ryan.dao;

import com.aldrich_ryan.entity.Transaction;
import com.aldrich_ryan.util.DaoService;
import com.aldrich_ryan.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TransactionDaoImpl implements DaoService<Transaction> {
    @Override
    public List<Transaction> fetchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriabuilder = session.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriabuilder.createQuery(Transaction.class);
        criteriaQuery.from(Transaction.class);
        List<Transaction> transactions = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return transactions;
    }

    @Override
    public int addData(Transaction object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
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
    public int udpateData(Transaction object) {
        return 0;
    }

    @Override
    public int deleteData(Transaction object) {
        return 0;
    }
}
