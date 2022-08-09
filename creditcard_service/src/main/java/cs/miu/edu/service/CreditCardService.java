package cs.miu.edu.service;

import cs.miu.edu.domain.CreditCard;
import cs.miu.edu.dto.CreditCardDto;
import cs.miu.edu.dto.ResponseStatus;
import cs.miu.edu.dto.Status;
//import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.repository.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class CreditCardService {

 @Autowired
    private CreditCardRepo creditCardRepo;



    public CreditCardDto saveCreditCard(CreditCardDto creditCardDto) {
       CreditCard creditCard= CreditCard.builder()
               .balance(0.0)
               .cardNumber("1234")
               .ccv("123")
               .firstName("Priya")
               .lastName("giri").build();
        creditCard.setExpiryDate(LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        creditCard.setCardLimit(2000.0);
        creditCardRepo.save(creditCard);
        return creditCardDto;
    }

    public Status verifyPurchase(CreditCardDto creditCardDto) {
      //  CreditCard creditCard = mapper.mapToTransaction(Cre)
       Optional<CreditCard> creditCard= creditCardRepo.getCreditCardByCardNoCardExpiryDateCcvCardLimit(creditCardDto.getCardNumber(), creditCardDto.getExpiryDate(), creditCardDto.getCcv());
        if(creditCard.isEmpty()) {
            System.out.println("Invalid card");
            return Status.FAILURE;
        }
        LocalDate cardExpiryDate= creditCard.get().getExpiryDate();
        LocalDate todayDate= LocalDate.now();
        if(!cardExpiryDate.isAfter(todayDate)) {
            System.out.println("Card has expired");
            return  Status.FAILURE;
        }
        Double currentPurchaseBalalnce= creditCardDto.getBalance();
         Double totalNewBalance=  currentPurchaseBalalnce+ creditCard.get().getBalance();
        if(creditCard.get().getCardLimit()< totalNewBalance) {
            System.out.println("Limitation of card has been exceed");
            return  Status.FAILURE;
        }
         creditCard.get().setBalance(totalNewBalance);
         creditCardRepo.save(creditCard.get());
        return  Status.SUCCESS;

    }



//    public CreditCardDto getCreditCardById(Integer id) {
//        Optional<CreditCard> creditCardOptional= creditCardRepo.getCreditCardByAccountId(id);
//        if(creditCardOptional.isEmpty()) return  null;
//        return  mapper.mapToDto(creditCardOptional.get());
//    }

}
