package cs.miu.edu.service;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.dto.PaypalDto;
import cs.miu.edu.dto.ResponseStatus;
import cs.miu.edu.dto.Status;
import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.repository.PaypalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaypalService {

    @Autowired
    private PaypalRepo paypalRepo;
    @Autowired
    private Mapper mapper;

    public PaypalDto savePaypal(PaypalDto paypalDto) {
        Paypal paypal = mapper.mapToPaypal(paypalDto);
        paypalRepo.save(paypal);
        return paypalDto;
    }

    public ResponseStatus verifyPurchase(PaypalDto paypalDto) {
        Optional<Paypal> paypalOptional = paypalRepo.getPaypal(paypalDto.getEmailAddress(), paypalDto.getSecureKey());
        if (paypalOptional.isEmpty()) {
            System.out.println("Invalid account");
            return new ResponseStatus(Status.FAILURE);
        }
        Paypal paypal = paypalOptional.get();
        Double paypalBalanceAccount = paypal.getBalance() - paypalDto.getBalance();
        if (paypalBalanceAccount < 0) {
            System.out.println("Insufficient balance to purchase item");
            return new ResponseStatus(Status.FAILURE);
        }
        paypal.setBalance(paypalBalanceAccount);
        paypalRepo.save(paypal);
        return new ResponseStatus(Status.SUCCESS);
    }
}
