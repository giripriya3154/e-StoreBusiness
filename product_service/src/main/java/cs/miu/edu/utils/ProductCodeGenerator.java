package cs.miu.edu.utils;

import cs.miu.edu.repository.ProductRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Component
public class ProductCodeGenerator {
    @Autowired
    private ProductRepo productRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        Session session = entityManager.unwrap(Session.class);
        return session;
    }

    public String productCodeGenerator() {
        try{
            Query query = getSession().createQuery("select  t.productCode from Product  t order by t.productCode desc");
            query.setMaxResults(1);

            String transactionCode = (String) query.getSingleResult();
            System.out.println(transactionCode);
            String subString = transactionCode.substring(1);
            int integerValue = Integer.parseInt(subString);
            integerValue++;
            String newBadgeCode = "P" + integerValue;
            return newBadgeCode;
        }catch (NoResultException ex){
            return "P101";
        }

    }


}
