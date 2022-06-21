package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Horse;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HorseRepositoryEntityManager {
    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceContext
    private Session session;

    private EntityManagerFactory emf;

    public HorseRepositoryEntityManager() {
    }

    //filter
    public List<Horse> findFilter(Integer year, Integer id){
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from Horse h where 0 = 0");
        TypedQuery<Horse> query = null;
        if(year != null){
            jpql.append("and year = :year ");
            query.setParameter("year", year);
        }
        if(id != null){
            jpql.append("and id = :id");
            query.setParameter("id", id);
        }
        query = entityManager.createQuery(jpql.toString(), Horse.class);
        return query.getResultList();
    }

    //create
//    @Transactional
//    public void save(Contact contact) {
//        entityManager.persist(contact);
//    }

    //update
//    @Transactional
//    public Contact update(Contact contact) {
//        return entityManager.merge(contact);
//    }

    //findById
//    public Contact findById(Integer id) {
//        return entityManager.find(Contact.class, id);
//    }

    //delete
//    @Transactional
//    public void delete(Integer contactId) {
//        Contact contact = entityManager.find(Contact.class, contactId);
//        entityManager.remove(contact);
//    }
}
