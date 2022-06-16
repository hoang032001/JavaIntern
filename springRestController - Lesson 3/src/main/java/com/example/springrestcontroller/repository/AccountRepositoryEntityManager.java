package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AccountRepositoryEntityManager {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    //named Query -> on Account (model)
    public Account findById(int id){
        Query query = entityManager.createNamedQuery("find account by id");
        query.setParameter("id", id);
        return (Account) query.getSingleResult();
    }


    public List<String> findListAccount(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select a from Account a");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    public void deleteById(int id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Account a where a.id="+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void updateById(int id, String name){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Account a set a.username='"+name+"' where a.id="+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}
