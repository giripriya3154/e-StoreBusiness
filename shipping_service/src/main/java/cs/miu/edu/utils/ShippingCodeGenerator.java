package cs.miu.edu.utils;

import cs.miu.edu.repository.ShippingRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Component
public class ShippingCodeGenerator {
    @Autowired
    private ShippingRepo shippingRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        Session session = entityManager.unwrap(Session.class);
        return session;
    }

    public String shippingCodeGenerator() {
        try{
            Query query = getSession().createQuery("select  s.shippingCode from Shipper  s order by s.shippingCode desc");
            query.setMaxResults(1);

            String transactionCode = (String) query.getSingleResult();
            System.out.println(transactionCode);
            String subString = transactionCode.substring(1);
            int integerValue = Integer.parseInt(subString);
            integerValue++;
            String newBadgeCode = "S" + integerValue;
            return newBadgeCode;
        }catch (NoResultException ex){
            return "S101";
        }

    }


}
