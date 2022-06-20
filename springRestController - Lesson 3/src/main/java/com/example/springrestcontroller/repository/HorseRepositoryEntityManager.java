package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.model.Trainer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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
    public List<Object> findFilter(Integer year, Integer id){
//        String jpql = "select h, t from Horse h inner join HorseAccount ha on ha.horse.id = h.id " +
//                        "inner join Trainer t on t.account_id = ha.account.id " +
//                        "where (case when ?1 > 0 then YEAR(h.foaled)=?1 and t.id=?2 else t.id=?2 end)";
//        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
//        query.setParameter(1, year);
//        query.setParameter(2, id);
//        return query.getResultList();

        String jpql = "select {h.*}, {t.*} from horse h inner join horse_account ha on ha.horse_id = h.id " +
                "inner join trainer t on t.account_id = ha.account_id " +
                "where (case when :year > 0 then YEAR(h.foaled)=:year and t.id=:id else t.id=:id end)";

        List<Object> query = session.createNativeQuery(jpql)
                .addEntity("h", Horse.class)
                .addEntity("t", Trainer.class)
                .setParameter("year", year)
                .setParameter("id", id)
                .list();
        return query;
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
