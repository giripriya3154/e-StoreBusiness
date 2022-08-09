package cs.miu.edu.controller;

import cs.miu.edu.dto.CreditCardDto;
import cs.miu.edu.dto.ResponseStatus;
import cs.miu.edu.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<?> savePaypal(@RequestBody CreditCardDto paypalDto){
        ResponseEntity<?> response= new ResponseEntity<>(creditCardService.saveCreditCard(paypalDto), HttpStatus.OK);
        return response;
    }


@PostMapping("/verify-purchase")
public ResponseEntity<?> check(@RequestBody CreditCardDto creditCardDto) {
    ResponseStatus responseStatus =new ResponseStatus(creditCardService.verifyPurchase(creditCardDto));
    ResponseEntity<?> response = new ResponseEntity<>(responseStatus, HttpStatus.OK);

    return response;
}


}
