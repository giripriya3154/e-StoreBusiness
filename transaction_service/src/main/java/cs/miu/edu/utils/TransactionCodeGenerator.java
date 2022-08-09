package cs.miu.edu.utils;

import cs.miu.edu.domain.Transaction;
import cs.miu.edu.repository.TransactionRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class TransactionCodeGenerator {
    @Autowired
    private TransactionRepo transactionRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        Session session = entityManager.unwrap(Session.class);
        return session;
    }

    public String transactionCodeGenerator() {
        try{
            Query query = getSession().createQuery("select  t.transactionCode from Transaction  t order by t.transactionCode desc");
            query.setMaxResults(1);

            String transactionCode = (String) query.getSingleResult();
            System.out.println(transactionCode);
            String subString = transactionCode.substring(1);
            int integerValue = Integer.parseInt(subString);
            integerValue++;
            String newBadgeCode = "T" + integerValue;
            return newBadgeCode;
        }catch (NoResultException ex){
            return "T101";
        }

    }


}
